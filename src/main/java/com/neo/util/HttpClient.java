package com.neo.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 使用OkHttp发送请求
 */
public class HttpClient {

    private final static Logger log = LoggerFactory.getLogger(HttpClient.class);

    private HttpClient() {
        throw new IllegalAccessError("Utility class");
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient client;

    //TODO 需要加上代理或更换IP
    static {
        client = (new OkHttpClient.Builder()).
                connectTimeout(30L, TimeUnit.SECONDS).
                readTimeout(60L, TimeUnit.SECONDS).
                build();
    }

    /**
     * get请求
     *
     * @param url url
     * @param headers 请求头部
     * @throws IOException
     * @return 请求结果，json格式
     */
    public static String get(String url, Map<String, String> headers) throws IOException {
        OkHttpClient client = null;
        Response response = null;
        try {
            client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();
            Request.Builder requestBuilder = new Request.Builder()
                    .url(url);
            // 填充header
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    requestBuilder.header(header.getKey(), header.getValue());
                }
            }
            // 请求
            Request request = requestBuilder.build();
            // 结果
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
            return null;
        } finally {
            // 关闭response、关闭连接池
            if (response != null) {
                response.close();
            }
            if (client != null) {
                // 如果不在这里关闭，OkHttpClient如果长时间处于空闲状态，也会自动关闭
                client.dispatcher().executorService().shutdown();
                client.connectionPool().evictAll();
            }
        }
    }

    /**
     * get请求
     *
     * @param url url
     * @return 请求结果，json格式
     * @throws IOException
     */
    public static String get(String url){
        Response response = null;
        try {
            try {
                response = get(client, url);
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                log.error("请求出错,url:{}", url);
            }

            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * POST表单
     * 
     * @param url url
     * @param params 参数
     * @return 请求结果，json格式
     * @throws IOException
     */
    public static String post(String url,Map<String,String> params) throws IOException {
        Response response = null;
        try {
            response = postParam(client, url, params);
            if (response.isSuccessful()) {
                return response.body().string();
            }
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * JSON post请求
     * @param url url
     * @param jsonStr json
     * @return 请求结果，json格式
     * @throws IOException
     */
    public static String postJson(String url,String jsonStr) throws IOException {
        Response response = null;
        try {
            response = postJson(client, url, jsonStr);
            if (response.isSuccessful()) {
                String result = response.body().string();
                response.close();
                return result;
            }
            response.close();
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private static Response postJson(OkHttpClient httpClient, String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = (new okhttp3.Request.Builder()).url(url).post(body).build();
        return httpClient.newCall(request).execute();
    }

    private static Response postParam(OkHttpClient httpClient, String url, Map<String, String> params) throws IOException {
        okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
        params.forEach((k, v) -> {
            builder.add(k, v);
        });
        FormBody body = builder.build();
        Request request = (new okhttp3.Request.Builder()).url(url).post(body).build();
        return httpClient.newCall(request).execute();
    }

    private static Response get(OkHttpClient client, String url) throws IOException {
        Request request = (new okhttp3.Request.Builder()).url(url).build();
        Response response = client.newCall(request).execute();
        return response;
    }
    
}
