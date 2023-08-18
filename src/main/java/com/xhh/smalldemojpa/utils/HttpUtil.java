package com.xhh.smalldemojpa.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {
    
    private static PoolingHttpClientConnectionManager pool;
    
    private static RequestConfig requestConfig;
    
    static {
        try {
            // 信任全部主机名
            HostnameVerifier hostnameVerifier = new HostnameVerifier(){

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            };
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(createIgnoreVerifySSL(), hostnameVerifier);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory).build();

            pool = new PoolingHttpClientConnectionManager(registry);

            // 连接池最大连接数
            pool.setMaxTotal(200);
            // 每个路由的最大并发数（即允许同一个host:port最多有几个活跃连接）
            pool.setDefaultMaxPerRoute(200);

            requestConfig = RequestConfig.custom()
                    .setConnectTimeout(Timeout.ofSeconds(5))
                    .setConnectionRequestTimeout(Timeout.ofSeconds(5))
                    .build();
        } catch (Exception e) {
            log.error("HttpClientUtil static error:", e);
        }
    }

    /**   使用了 Apache HttpComponents 库中的 SSLContextBuilder，通过调用 .loadTrustMaterial(null, new TrustSelfSignedStrategy()) 方法，
     *  将信任策略设置为信任自签名的证书，也就是忽略了对服务器证书的验证
     * 
     *            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,  
     *                             new TrustSelfSignedStrategy())  
     *                     .build(); 
     *
     */


    /**
     * 使用一个匿名的 X509TrustManager 来实现空的验证方法，从而实现对服务器证书的忽略验证
     * @return
     * @throws Exception
     */
    private static SSLContext createIgnoreVerifySSL() throws Exception{
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        
        sslContext.init(null, new TrustManager[]{x509TrustManager}, null);
        return sslContext;
    }
    
    
    private static CloseableHttpClient getHttpClient() {

        return HttpClients.custom()
                .setConnectionManager(pool)
                .setDefaultRequestConfig(requestConfig)
                .setRetryStrategy(new DefaultHttpRequestRetryStrategy(0, TimeValue.of(0, TimeUnit.SECONDS)))
                .build();
    }
    
    
    private static String sendHttp(ClassicHttpRequest httpRequest) {
        CloseableHttpResponse response = null;
        
        String result = "";
        
        try {
            CloseableHttpClient httpClient = getHttpClient();
            response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            if (response.getCode() != HttpStatus.SC_OK) {
                throw new Exception("HTTP Send is not success, Response code " + response.getCode() + "Reason : " + response.getReasonPhrase());
            }

            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            log.error("HttpClient sendHttp error:", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                log.error("HTTP close response error: ", e);
            }
        }
        return result;
    }
    
    public static String doGet(String url, JSONObject jsonObject) {
        
        String result = null;
        try {
            ClassicHttpRequest get = ClassicRequestBuilder.get(url).build();
            get.setEntity(new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON));
            result = sendHttp(get);
        } catch (Exception exception) {
            log.error("HTTP send get error, e", exception);
        }
        log.info("HTTP send get success, result:{}", result);
        return result;
    }
    
    public static String doPost(String url, JSONObject jsonObject, Header... headers) {
        String result = null;
        try {
            ClassicHttpRequest post = ClassicRequestBuilder.post(url).build();
            if (headers != null) {
                post.setHeaders(headers);
            }
            post.setEntity(new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON));
            result = sendHttp(post);
        } catch (Exception exception) {
            log.error("HTTP send post error, e", exception);
        }
        log.info("HTTP send post success, result:{}", result);
        return result;
    }
    
}
