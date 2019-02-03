package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app){
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build(); // Редирект

    }

    public boolean login(String user, String pass) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.url") + "/login.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", user));
        params.add(new BasicNameValuePair("password", pass));
        params.add(new BasicNameValuePair("secure_session", "on" ));
        params.add(new BasicNameValuePair("return", "index.php" ));
        post.setEntity(new UrlEncodedFormEntity(params));
        System.out.println("******************************************************************************************");
        System.out.println(post);
        System.out.println(params);
        System.out.println("******************************************************************************************");
        CloseableHttpResponse response = httpClient.execute(post);
        String body = getTextFrom(response);
        System.out.println(body);
        return body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>", user));  // Actual for mantisbt-2.19.0
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();

        }
    }

    public boolean isLoggedInAs(String user) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.url"));
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>" , user));  // Actual for mantisbt-2.19.0
    }
}