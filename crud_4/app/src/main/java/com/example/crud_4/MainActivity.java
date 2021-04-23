package com.example.crud_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // https://www.tutorialspoint.com/android/android_php_mysql.htm
    private EditText nombre, contrasena;
    private TextView status, role, method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.nombre);
        contrasena = (EditText) findViewById(R.id.contrasena);

        status = (TextView)findViewById(R.id.textView5);
        method = (TextView)findViewById(R.id.textView3);
    }

    public void login(View view){
        String nombre_txt = nombre.getText().toString();
        String contrasena_txt = contrasena.getText().toString();
        method.setText("get method");
        new SiginActivity(this,status,role,0).execute(nombre_txt, contrasena_txt);
    }

    public void loginPost(View view){
        String nombre2 = nombre.getText().toString();
        String contrasena2 = contrasena.getText().toString();

        method.setText("Post Method");
        new SiginActivity(this,status,role,1).execute(nombre2,contrasena2);
    }
}
