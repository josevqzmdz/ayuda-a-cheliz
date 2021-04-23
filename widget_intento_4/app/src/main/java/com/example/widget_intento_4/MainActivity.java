package com.example.widget_intento_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    // https://www.youtube.com/watch?v=I6lXx-VgaYs
    Button btnSave;
    EditText editTitulo, editContenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init paper
        Paper.init(this);

        btnSave = (Button)findViewById(R.id.boton);
        editTitulo = (EditText)findViewById(R.id.edit_titulo);
        editContenido = (EditText)findViewById(R.id.edit_contenido);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Paper.book().write("titulo", editTitulo.getText().toString());
                Paper.book().write("contenido", editContenido.getText().toString());

                Toast.makeText(MainActivity.this, "guardado!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
