package unicap.es.easystreets.rest

import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonRequest
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import kotlin.reflect.KClass


class JsonRequest<T : Any> @Throws(JsonProcessingException::class)
constructor(private val objectMapper: ObjectMapper, method: Int, url: String, dto: Any?,
            private val resultDtoType: KClass<T>?, listener: Response.Listener<T>,
            errorListener: Response.ErrorListener, private val resultDtoDefaultCallback: () -> T?)
    : JsonRequest<T>(
        method,
        url,
        if (dto != null) {
            val inputData = objectMapper.writeValueAsString(dto)
            Log.i("dao - body", inputData)
            inputData
        }
        else null,
        listener,
        errorListener) {

    var authorization: String? = null

    override fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()

        headers.put("Content-Type", "application/json")

        if (authorization != null) {
            headers.put("Authorization", authorization!!)
        }

        return headers
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        try {
            val data = String(response.data, Charset.forName(HttpHeaderParser.parseCharset(response.headers, JsonRequest.PROTOCOL_CHARSET)))

            Log.i("dao - response", if(data.isNotEmpty()) data else "-")

            if (response.statusCode != 200) {
                return Response.error<T>(JsonError(objectMapper.readValue<ErrorData>(data)))
            }

            var result = resultDtoDefaultCallback()

            if (resultDtoType != null && response.data.isNotEmpty()) {
                result = objectMapper.readValue(data, resultDtoType.java)
            }

            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            return Response.error<T>(ParseError(e))
        } catch (e: JsonMappingException) {
            return Response.error<T>(ParseError(e))
        } catch (e: JsonParseException) {
            return Response.error<T>(ParseError(e))
        } catch (e: IOException) {
            return Response.error<T>(ParseError(e))
        }
    }
}
