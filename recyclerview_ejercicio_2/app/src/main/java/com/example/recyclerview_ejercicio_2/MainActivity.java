package com.example.recyclerview_ejercicio_2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private ArrayList<Titular> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializacion de datos
        datos = new ArrayList<Titular>();
        for (int i=0; i < 50; i++){
            datos.add(new Titular("Titulo" + i, "Subtitulo item "+i));
        }
        recView = (RecyclerView) findViewById(R.id.Recycler_view);
        recView.setHasFixedSize(true);

        final AdaptadorTitulares adaptador = new AdaptadorTitulares(datos);
        recView.setAdapter(adaptador);

        /*
        queremos mostrar los datos en forma de lista con desplazamiento vertical.
        Para ello tenemos disponible la clase LinearLayoutManager, por lo que
        tan sólo tendremos que instanciar un objeto de dicha clase indicando
        en el constructor la orientación del desplazamiento
        (LinearLayoutManager.VERTICAL o LinearLayoutManager.HORIZONTAL) y lo
        asignaremos al RecyclerView mediante el método setLayoutManager(). Esto lo
        haremos justo después del código anterior
         */

        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

    }
}
