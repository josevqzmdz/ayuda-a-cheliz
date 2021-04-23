package com.example.reddit_app3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import model.Feed;
import model.FeedAPI;
import model.entry.entry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    // TAG para encontrar la informacion en el logcat
    private static final String TAG = "MainActivity";
    // URL base para conectar a los subreddits
    private static final String BASE_URL = "https://www.reddit.com/r/";

    // una lista para almacenar todos los datos del feed
    List<Feed> feedList = new ArrayList<>();

    // variables de RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // https://developer.android.com/guide/topics/ui/layout/recyclerview#java
        // https://medium.com/@nhkarthick/recyclerview-and-cardview-implementation-in-android-kotlin-f5a0d0a437c7
        //        initialize the recyclerView from the XML
        // RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        // recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        // layoutManager = new LinearLayoutManager(this);
        // recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        // TODO: agregar informacion a la dataset
        // TODO: encapsular todo el XMLParser / retrofit en una clase particular, para facilitar su uso
        // mAdapter = new MyAdapter(myDataset);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedAPI feedAPI = retrofit.create(FeedAPI.class);

        Call<Feed> call = feedAPI.getFeed();
        // cual es la diferencia entre Call y call?
        // call es el objeto creado y Call es el constructor / metodo base
        call.enqueue(new Callback<Feed>(){
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response){
                Log.d(TAG, "onResponse: feed: "+ response.body().getEntrys());

                List<entry> entrys = response.body().getEntrys();
                // imprime el estado/mensaje de HTTP
                //estos metodos funcionan solo para ver la solicitud XML
                Log.d(TAG, "onResponse: Server respnse: "+ response.body().getEntrys());
                Log.d(TAG, "onResponse: entrys: "+response.body().getEntrys());
                Log.d(TAG, "onResponse: author: " + entrys.get(0).getUpdated());
                Log.d(TAG, "onResponse: title: " + entrys.get(0).getTitle());

                //https://www.youtube.com/watch?v=4QMeqJToj0M&t=1153s 21:39
                //vamos a revisar todos los posts de la pagina principal de r/earthporn
                //y extraer la informacion

                ArrayList<Post> posts = new ArrayList<Post>();

                // for LOOP para obtener el feed RSS de reddit
                for(int i = 0; i < entrys.size(); i++) {
                    //queremos pasar como string los tags de html (ejemplo: <a href>)
                    extractXML exXML1 = new extractXML(entrys.get(0).getContent(), "<a href=");
                    // usamos una lista para guardar los resultados
                    List<String> postContent = exXML1.start();

                    extractXML exXML2 = new extractXML(entrys.get(0).getContent(), "<img src=");
                    try{
                        postContent.add(exXML2.start().get(0));
                    }catch(NullPointerException e){
                        // en caso de que no tenga un thumbnail
                        postContent.add(null);
                        Log.e(TAG, "onResponse: NullPointerException(thumbnail): "+e.getMessage());
                    }catch(IndexOutOfBoundsException e){
                        // en caso de que la posicion/indice no exista
                        postContent.add(null);
                        Log.e(TAG, "onResponse: IndexOutOfBoundsException(thumbnail): "+e.getMessage());
                    }
                    int lastPosition = postContent.size() - 1;

                    // aqui se guarda la informacion obtenida del feed RSS de reddit
                    posts.add(new Post(
                            entrys.get(i).getTitle(),
                            entrys.get(i).getAuthor().getName(),
                            entrys.get(i).getUpdated(),
                            postContent.get(0),
                            postContent.get(lastPosition)
                    ));
                    exXML2.start();
                }//fin de for loop

                // https://www.youtube.com/watch?v=QctjPJhNU-c 7:03
                for(int j = 0; j < posts.size(); j++){
                    // logs del logcat para observar que esta sucediendo
                    Log.d(TAG, "onResponse: \n" +
                            "PostURL: " + posts.get(j).getPostURL() + "\n" +
                            "ThumbnailURL: " + posts.get(j).getThumbnailURL() + "\n" +
                            "Title: "+ posts.get(j).getTitle() + "\n" +
                            "Author: " + posts.get(j).getAuthor() + "\n" +
                            "Updated: " + posts.get(j).getDate_updated() + "\n");

                }// fin de for
                // aqui se llena la listView
                //ListView listView = (ListView) findViewById(R.id.listView);
                //CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, R.layout.card_layout_main, posts);
                //listView.setAdapter(customListAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t){
                Log.e(TAG, "onFailure: Unable to retrieve RSS: "+ t.getMessage());
                Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });

        // XML_holder xmlholder = new XML_holder(this, postContent);
    }
}
