package app.b4a.gtp3speaker.Thread;

import android.util.Log;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GoogleConnectionThread extends Thread{

    private String openApiURL;
    private String accessKey;
    private Gson gson;
    private Map<String, Object> request = new HashMap<>();

    public GoogleConnectionThread() {
    }

    public void setOpenApiURL(String openApiURL) {
        this.openApiURL = openApiURL;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public void run() {


        URL url;
        Integer responseCode = null;
        String responBody = null;

        try {
            Log.d("EtriThread","try1");
            url = new URL(openApiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            //con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", accessKey);
            Log.d("EtriThread","try2");

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(gson.toJson(request).getBytes("UTF-8"));
            wr.flush();
            wr.close();

            Log.d("EtriThread","try3");

            responseCode = con.getResponseCode();
            Log.d("EtriThread",responseCode.toString());

            Log.d("EtriThread","try3-1");
            InputStream is = con.getInputStream();
            Log.d("EtriThread","try3-2");
            byte[] buffer = new byte[is.available()];
            Log.d("EtriThread","try3-3");
            int byteRead = is.read(buffer);
            Log.d("EtriThread","try3-4");
            responBody = new String(buffer);
            Log.d("EtriThread","try4");
            Log.d("EtriThread","[responseCode] " + responseCode);
            Log.d("EtriThread","[responBody]");
            Log.d("EtriThread",responBody.toString());
            Log.d("EtriThread","try5");

        } catch (MalformedURLException e) {
            Log.d("EtriThread","error1");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("EtriThread","error2");
            e.printStackTrace();
        }

    }
}
