package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.spinner_metodo();
    }

    public void spinner_metodo()
    {

        String[] arraySpinner = new String[]{
                "item 1", "item 2", "item 3"
        };
        final Spinner s = (Spinner) findViewById(R.id.spinner_boton);
        
        // s.setOnItemSelectedListener(this);

        /*
        Dicho de otra forma, todos los controles de selección accederán a los datos que contienen
         a través de un adaptador.
        Además de proveer de datos a los controles visuales, el adaptador también será responsable
         de generar a partir de estos datos las vistas específicas que se mostrarán dentro del
          control de selección.
        Es el más sencillo de todos los adaptadores, y provee de datos a un control
        de selección a partir de un array de objetos de cualquier tipo.
         */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

    }


}
