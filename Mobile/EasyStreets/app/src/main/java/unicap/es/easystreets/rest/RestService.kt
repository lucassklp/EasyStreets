package unicap.es.easystreets.rest

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.android.volley.*
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Observable
import javax.inject.Inject
import kotlin.reflect.KClass

class RestService @Inject
constructor(private val requestQueue: RequestQueue, private val sharedPreferences: SharedPreferences, private val objectMapper: ObjectMapper, private val application: Application) {

    private val restServer: String = "http://localhost:9000/"

    private fun <T : Any> request(method: Int, url: String, queryString: QueryString?, dto: Any?, resultDtoType: KClass<T>?,
                            resultDtoDefaultCallback: () -> T?): Observable<T> {
        return Observable.create<T> { subscriber ->
            try {
                val stringBuffer = StringBuffer("${this.restServer}/$url")

                if (queryString != null) {
                    stringBuffer.append(queryString.value)
                }

                val url = stringBuffer.toString()

                Log.i("dao - url", url)

                val daoJsonRequest = JsonRequest(objectMapper, method, url, dto, resultDtoType,
                Response.Listener {
                    subscriber.onNext(it)
                    subscriber.onComplete()
                },
                Response.ErrorListener {
                    when (it) {
                        is NetworkError -> subscriber.onError(ConnectionError(it))
                        is AuthFailureError -> Log.e("AuthFailureError", it.toString(), it) //subscriber.onError(ConnectionError(it))
                        is NoConnectionError -> subscriber.onError(ConnectionError(it))
                        is TimeoutError ->  Log.e("TimeoutError", it.toString(), it) //subscriber.onError(ConnectionError(it))
                        else -> subscriber.onError(it)

                    }
                }, resultDtoDefaultCallback)

                daoJsonRequest.authorization = sharedPreferences.getString("authorization", null)

                this.requestQueue.add(daoJsonRequest)
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }

    fun <T : Any> request(method: Int, url: String, queryString: QueryString?, dto: Any?, resultDtoType: KClass<T>?): Observable<T> =
            request(method, url, queryString, dto, resultDtoType, { null })

    fun request(method: Int, url: String, queryString: QueryString?, dto: Any?): Observable<Unit> =
            request(method, url, queryString, dto, null, { Unit })

    fun getUnit(url: String, queryString: QueryString?): Observable<Unit> =
            request(Request.Method.GET, url, queryString, null)

    inline operator fun <reified T : Any> get(url: String): Observable<T> =
            request(Request.Method.GET, url, null, null, T::class)

    inline operator fun <reified T : Any> get(url: String, queryString: QueryString?): Observable<T> =
            request(Request.Method.GET, url, queryString, null, T::class)

    fun delete(url: String, queryString: QueryString?): Observable<Unit> =
            request(Request.Method.DELETE, url, queryString, null)

    fun postUnit(url: String, dto: Any): Observable<Unit> =
            request(Request.Method.POST, url, null, dto)

    fun postUnit(url: String, queryString: QueryString?, dto: Any): Observable<Unit> =
            request(Request.Method.POST, url, queryString, dto)

    inline fun <reified T : Any> post(url: String, dto: Any): Observable<T> =
            request(Request.Method.POST, url, null, dto, T::class)

    inline fun <reified T : Any> post(url: String, queryString: QueryString?, dto: Any): Observable<T> =
            request(Request.Method.POST, url, queryString, dto, T::class)

    fun putUnit(url: String, dto: Any): Observable<Unit> =
            request(Request.Method.PUT, url, null, dto)

    fun putUnit(url: String, queryString: QueryString?, dto: Any): Observable<Unit> =
            request(Request.Method.PUT, url, queryString, dto)

    inline fun <reified T : Any> put(url: String, dto: Any): Observable<T> =
            request(Request.Method.PUT, url, null, dto, T::class)

    inline fun <reified T : Any> put(url: String, queryString: QueryString?, dto: Any): Observable<T> =
            request(Request.Method.PUT, url, queryString, dto, T::class)
}
