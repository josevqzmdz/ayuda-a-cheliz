package com.example.crud_4;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// https://www.tutorialspoint.com/android/android_php_mysql.htm
public class SiginActivity extends AsyncTask {
    private TextView statusField, roleField;
    private Context context;
    private int byGetOrPost = 0;

    public SiginActivity(Context context, TextView statusField, TextView roleField, int flag){
        this.context = context;
        this.statusField = statusField;
        this.roleField = roleField;
        byGetOrPost = flag;
    }

    protected void onPreExecute(){

    }

    protected void onPostExecute(String result){
        this.statusField.setText("Login Successful");
        this.roleField.setText(result);
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        // httprequestclient esta obsoleto, la comunidad recomienda okhttp
        // https://square.github.io/okhttp/
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        if(byGetOrPost == 0){
            // significa get method
            try{
                String usuario = (String)objects[0];
                String contrasena = (String)objects[1];
                String url_txt = "http://localhost/proyecto_android/post_metodo.php?nombre=1234?contrasena=1234";
                URL url = new URL(url_txt);

                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                // https://www.youtube.com/watch?v=oGWJ8xD2W6k
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback(){
                    @Override
                    public void onFailure(Call call, IOException e){
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException{
                        if (response.isSuccessful()){
                            String respuesta = response.body().string();


                        }
                    }
                 });

                // toasts para el manejo de excepciones
                // https://developer.android.com/guide/topics/ui/notifiers/toasts#java
            }catch(Exception e){
                Toast.makeText(context, "excepcion: "+e, Toast.LENGTH_LONG);
            }// fin dell if
        }else{
            try{
                String usuario = (String)objects[0];
                String contrasena = (String)objects[1];
                String url = "http://localhost/proyecto_android/post_metodo.php?nombre=1234?contrasena=1234";
                // URL url = new URL(link);

                // https://www.youtube.com/watch?v=oGWJ8xD2W6k
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback(){
                    @Override
                    public void onFailure(Call call, IOException e){
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException{
                        if (response.isSuccessful()){
                            String respuesta = response.body().string();


                        }
                    }
                });

            }catch(Exception e ){
                Toast.makeText(context, "excepcion: "+e, Toast.LENGTH_LONG);

            }
        }
        return null;
    }
}
