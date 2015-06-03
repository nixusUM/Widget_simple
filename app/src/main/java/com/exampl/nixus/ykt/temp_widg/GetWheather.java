package com.exampl.nixus.ykt.temp_widg;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetWheather {
    public String USER_URL = "http://yabesco.ru/getTemp1.php";
    public String getInet()
    {
        String result = "";
        String url = USER_URL;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String line;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    String numberOnly= line.replaceAll("[^0-9-+]", "");
                    sb.append(numberOnly);
                }
                result = sb.toString();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}