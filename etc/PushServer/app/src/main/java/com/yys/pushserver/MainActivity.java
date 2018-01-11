package com.yys.pushserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {

    private static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String FCM_SERVER_KEY = "AAAAQC2viiA:APA91bFhnRL_SYqEl1FuIZ74hPqBbw4rAnGFs1jeg3kTdtPSKq3YCWLZNECAnGIxrNQiawpul37DQD9XyCqIEOI2fZaT7j1Bh6FMExR-v-7fcpPT3XAnCOTOy0B5o-HlqaNw83Hkbqto";

    private EditText mTitle;
    private EditText mMessage;
    private Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = (EditText)findViewById(R.id.title);
        mMessage = (EditText)findViewById(R.id.message);
        mSend = (Button)findViewById(R.id.send);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPushMessage(mTitle.getText().toString(), mMessage.getText().toString());
            }
        });
    }

    private void sendPushMessage(final String title, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String parameters = "{"+
                            "\"data\": {"+
                                "\"title\": \"" + title + "\","+
                                "\"msg\": \"" + message + "\""+
                            "},"+
                            "\"to\": \"/topics/notice\""+
                            "}";

                    String result = sendPost(FCM_URL, parameters);
                    Log.d("yys", "send result : " + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String sendPost(String url, String parameters) throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public X509Certificate[] getAcceptedIssuers(){return new X509Certificate[0];}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "key=" + FCM_SERVER_KEY);
        String urlParameters = parameters;

        //post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(urlParameters.getBytes("UTF-8"));
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        StringBuffer response = new StringBuffer();

        if(responseCode == 200){
            BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }
        //result
        System.out.println(response.toString());
        return response.toString();
    }
}
