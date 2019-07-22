package com.example.platform.module.common.extend.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HttpClient {

    public static final String DEFAULT_CHARSET = "UTF-8";
    private final static Logger LOG = LoggerFactory.getLogger(HttpClient.class);
    private final static int connectTimeout = 1000; // ms
    private final static int socketTimeout = 5000; // ms


    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static DevHttpResponse httpGet(String url) throws Exception {
        Request req = Request.Get(url).connectTimeout(connectTimeout).socketTimeout(socketTimeout);
        try {
            HttpResponse resp = req.execute().returnResponse();
            return buildResponse(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("restriction")
    public static DevHttpResponse httpGetByAuth(String url, String user, String pass) throws Exception {
        Request req = Request.Get(url).connectTimeout(connectTimeout).socketTimeout(socketTimeout);
        String nameAndPass = user + ":" + pass;
        req.addHeader("Authorization", "Basic " + new sun.misc.BASE64Encoder().encode(nameAndPass.getBytes()));
        try {
            HttpResponse resp = req.execute().returnResponse();
            return buildResponse(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    public static DevHttpResponse httpPost(String url, String data) throws Exception {
        Request req = Request.Post(url).connectTimeout(connectTimeout).socketTimeout(socketTimeout);
        req.bodyString(data, ContentType.create("application/json", Consts.UTF_8));
        try {
            HttpResponse resp = req.execute().returnResponse();
            return buildResponse(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    public static DevHttpResponse httpPostBodyParam(String url, List<NameValuePair> params) throws Exception {
        Request req = Request.Post(url).connectTimeout(connectTimeout).socketTimeout(socketTimeout);
        req.bodyForm(params, Consts.UTF_8);
        try {
            HttpResponse resp = req.execute().returnResponse();
            return buildResponse(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    public static DevHttpResponse httpPost(String url) throws Exception {
        return httpPost(url, "");
    }

    public static DevHttpResponse httpPut(String url, String data) throws Exception {
        Request req = Request.Put(url).connectTimeout(connectTimeout).socketTimeout(socketTimeout);
        req.bodyString(data, ContentType.create("application/json", Consts.UTF_8));
        try {
            HttpResponse resp = req.execute().returnResponse();
            return buildResponse(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    public static DevHttpResponse httpDelete(String url) throws Exception {
        Request req = Request.Delete(url).connectTimeout(connectTimeout).socketTimeout(socketTimeout);
        try {
            HttpResponse resp = req.execute().returnResponse();
            return buildResponse(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    public static DevHttpResponse httpPut(String url) throws Exception {
        return httpPut(url, "");
    }

    private static DevHttpResponse buildResponse(HttpResponse resp) throws Exception {
        HttpEntity entity = resp.getEntity();
        String result = entity != null ? EntityUtils.toString(entity, DEFAULT_CHARSET) : null;
        return new DevHttpResponse(resp.getStatusLine().getStatusCode(), result);
    }


    public static class DevHttpResponse {

        private int status;
        private String data;

        public DevHttpResponse(int status) {
            this.status = status;
        }

        public DevHttpResponse(int status, String data) {
            this.status = status;
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DevHttpResponse [status=" + status + ", data=" + data + "]";
        }

    }


}
