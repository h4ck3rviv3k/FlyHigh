package com.pkg.flyhigh;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class LoginActivity extends ActionBarActivity {

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.btn_twitter_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });


    }

    public void signin() {
        ParseTwitterUtils.logIn(LoginActivity.this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    showMessage("Cancelled by user");
                } else if (user.isNew()) {
//                    showMessage("User signed up and logged in through Twitter!");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    new GetFeeds().execute();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
//                    showMessage("User logged in through Twitter!");
//                    new GetFeeds().execute();
                }
            }
        });
    }


    private static String hmacSha1(String value, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException {
        String type = "HmacSHA1";
        SecretKeySpec secret = new SecretKeySpec(key.getBytes(), type);
        Mac mac = Mac.getInstance(type);
        mac.init(secret);
        byte[] bytes = mac.doFinal(value.getBytes());
        return bytesToHex(bytes);
    }

    private final static char[] hexArray = "0123456789abcdef".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private void getPostsFromTwitter(String sessionToken) {
        HttpClient httpClient = new DefaultHttpClient();
        /*HttpPost httppost = new HttpPost("https://api.twitter.com/1/statuses/update.json" +
                "?include_entities=true");*/
        HttpPost httppost = new HttpPost("https://api.twitter.com/1.1/search/tweets.json?q=%40samantha");

        String args = "oauth_consumer_key=" + "9ITsqv72M4JYWVH7T0q4O4aFO" +
                "&oauth_nonce=" + String.valueOf(System.currentTimeMillis()) +
                "&oauth_signature_method=HMAC-SHA1" +
                "&oauth_timestamp=" + String.valueOf(System.currentTimeMillis() / 1000) +
                "&oauth_token=" + "137500520-xbcfKfwaybjXMWE6myyE9nSas4fLX9bzdQM4lvPD" +
                "&oauth_version=1.0";
        String base = "POST&" + "https%3A%2F%2Fapi.twitter.com%2F1%2Fstatuses%2Fupdate.json"
                + "%26" + URLEncoder.encode(args);

        try {
            String header = "OAuth " +
                    "oauth_consumer_key=\"" + "9ITsqv72M4JYWVH7T0q4O4aFO" + "\"," +
                    "oauth_nonce=\"" + String.valueOf(System.currentTimeMillis()) + "\"," +
                    "oauth_signature_method=\"HMAC-SHA1\"," +
                    "oauth_timestamp=\"" + String.valueOf(System.currentTimeMillis() / 1000) + "\"," +
                    "oauth_token=\"" + "137500520-xbcfKfwaybjXMWE6myyE9nSas4fLX9bzdQM4lvPD" + "\"," +
                    "oauth_signature=\"" + URLEncoder.encode(hmacSha1(base, "n3ofza8FpKONaaQxMycD8gkAM20vAcMczy6n2f" +
                    "yYlWNzv2ysmf" + "&" + "njH4Sh5BAQGuHD1W4J9ObDIOsZmgCvWkIvuGjyIpb9nyq")) + "\"," +
                    "oauth_version=\"1.0\"";
            httppost.setHeader("Authorization", header);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(httppost);
            int i = 0;
            i++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private class GetFeeds extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            getPostsFromTwitter("");
            return null;
        }
    }
}
