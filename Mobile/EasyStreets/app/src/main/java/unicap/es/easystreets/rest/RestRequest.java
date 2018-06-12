package unicap.es.easystreets.rest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import unicap.es.easystreets.LoginActivity;

public class RestRequest<TParam, TResult> {


    public final static String BASE_URL = "http://lucassklp.a2hosted.com/api/";
    //public final static String BASE_URL = "http://192.168.1.2:5000/api/";

    private Class<TParam> typeParam;
    private Type typeResult;
    private Map<String, String> headers;
    private String url;
    private int method;
    private Gson gson;
    private Consumer<TResult> onSuccess;
    private Consumer<VolleyError> onError;
    private TParam object;
    Context ctx;
    public RestRequest(Class<TParam> typeParam, Type typeResult){
        this.typeParam = typeParam;
        this.typeResult = typeResult;
        this.headers = new HashMap<>();
        this.gson = new Gson();
    }

    public void putHeader(String key, String value){
        this.headers.put(key, value);
    }

    public void setMethod(int method){
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void execute(Context ctx, TParam param, final Consumer<TResult> onSuccess, final Consumer<VolleyError> onError) {
        SharedPreferences sharedPreferences =
                ctx.getSharedPreferences("token", Context.MODE_PRIVATE);

        String token = sharedPreferences.getString("token", "");
        putHeader("Authorization", "Bearer " + token);

        this.ctx = ctx;
        this.onSuccess = onSuccess;
        this.onError = onError;
        this.object = param;
        RequestQueueSingleton.getInstance(ctx).addToRequestQueue(this.buildRequest(this.method, this.url, this.buildErrorListener()));
    }

    public void execute(Context ctx, final Consumer<TResult> onSuccess, final Consumer<VolleyError> onError) {
        this.execute(ctx, null, onSuccess, onError);
    }


    private Response.ErrorListener buildErrorListener() {
        return error -> {
            if(error.networkResponse.statusCode == 401){
                Intent intent = new Intent(this.ctx, LoginActivity.class);
                ctx.startActivity(intent);
                ((Activity)ctx).finish();
            }
            onError.accept(error);
        };
    }

    private Request<TResult> buildRequest(int method, String url, Response.ErrorListener errorListener) {
        return new Request<TResult>(method, url, errorListener) {
            @Override
            protected Response<TResult> parseNetworkResponse(NetworkResponse response) {
                try {
                    String json = new String(
                            response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    Log.d("HTTP RESPONSE", json);
                    return Response.success(
                            gson.fromJson(json, typeResult),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JsonSyntaxException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(TResult response) {
                Response.Listener<TResult> listener = res -> onSuccess.accept(res);
                listener.onResponse(response);
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                Log.d("INFO", " getBody() executado");
                try {
                    String encoded = object == null ? null : gson.toJson(object).toString();
                    Log.d("BODY ENCODED", encoded == null ? "null" : encoded);
                    return encoded == null ? null : encoded.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

}
