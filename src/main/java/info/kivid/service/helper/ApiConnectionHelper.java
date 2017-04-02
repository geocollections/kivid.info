package info.kivid.service.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by Maarja on 31.03.2017.
 */
public class ApiConnectionHelper {

    private final String addressBase = "https://api.arendus.geokogud.info/";

    //https://api.arendus.geokogud.info/taxon/4085?format=json
    public String makeRequest(String requestString)
            throws Exception {
        HttpClient httpClient = getHttpClient();
        String getString = addressBase + requestString;
        HttpGet get = new HttpGet(getString);

        HttpResponse httpResponse = httpClient.execute(get);
        System.out.println(httpResponse.getStatusLine());

        HttpEntity responseEntity = httpResponse.getEntity();
        String fromResponse = new BufferedReader(
                new InputStreamReader(responseEntity.getContent())).lines()
                .collect(Collectors.joining("\n"));
        System.out.println(fromResponse);
        return fromResponse;
    }

    //http://stackoverflow.com/a/24879462
    private HttpClient getHttpClient() {

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[] { new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {

                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs,
                                               String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs,
                                               String authType) {

                }
            } }, new SecureRandom());

            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpClient httpClient = HttpClientBuilder.create()
                    .setSSLSocketFactory(socketFactory).build();

            return httpClient;

        } catch (Exception e) {
            e.printStackTrace();
            return HttpClientBuilder.create().build();
        }
    }
}
