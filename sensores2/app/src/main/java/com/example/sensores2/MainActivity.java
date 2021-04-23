package com.example.sensores2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    /*
    Como puedes observar esta actividad utiliza el Layout creado por defecto
     que básicamente es un LinearLayot (en el código corresponde a la variable
      raiz) con un TextView en su interior (“Hello Word …”). A raiz se le va
      a ir añadiendo una serie de vistas adicionales según los sensores
      encontrados en el dispositivo. Por cada sensor se añade: un TextView
        con el nombre del sensor, un LinearLayot de tipo horizontal [2]
        para contener a su vez un TextView  con “ X: “,un TextView  con el
         valor del sensor en el eje X, un TextView  con “ Y “,un TextView
         con el valor del sensor en el eje Y, un TextView  con “ Z: “ y un
         TextView  con el valor del sensor en el eje Z. Las referencias a
         los TextView  donde se visualizará los valores de los sensores se
          almacenan en el array aTextView[][]  donde el primer índice
          identifica el número de sensor y el segundo la dimensión X,Y o Z.

    En el método onSensorChanged() se hace un bucle para localizar el índice
    del sensor que ha cambiado y se modifican los TextView correspondiente
    al sensor con los valores leidos.

    NOTA: No todos los sensores tienen tres dimensiones. Por ejemplo,
    en el caso del sensor de temperatura solo se cambiará en el valor de X.
4.     Verifica sobre un dispositivo real que el programa funciona correctamente.

[2] En un LinearLayout si no se indica nada, la orientación por defecto es horizontal.
http://www.androidcurso.com/index.php/recursos/36-unidad-5-entradas-en-android-teclado-pantalla-tactil-y-sensores/276-utilizacion-de-los-sensores-en-asteroides


     */

    private List<Sensor> listaSensores;
    private TextView aTextView[][] = new TextView[20][3];

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout raiz = (LinearLayout) findViewById(R.id.raiz);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        listaSensores = sm.getSensorList(Sensor.TYPE_ALL);
        int n = 0;
        for (Sensor sensor : listaSensores) {
            TextView mTextView = new TextView(this);
            mTextView.setText(sensor.getName());
            raiz.addView(mTextView);
            LinearLayout nLinearLayout = new LinearLayout(this);
            raiz.addView(nLinearLayout);
            for (int i = 0; i < 3; i++) {
                aTextView[n][i] = new TextView(this);
                aTextView[n][i].setText("?");
                aTextView[n][i].setWidth(87);
            }
            TextView xTextView = new TextView(this);
            xTextView.setText("  X: ");
            nLinearLayout.addView(xTextView);
            nLinearLayout.addView(aTextView[n][0]);
            TextView yTextView = new TextView(this);
            yTextView.setText("  Y: ");
            nLinearLayout.addView(yTextView);
            nLinearLayout.addView(aTextView[n][1]);
            TextView zTextView = new TextView(this);
            zTextView.setText("  Z: ");
            nLinearLayout.addView(zTextView);
            nLinearLayout.addView(aTextView[n][2]);
            sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
            n++;
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            int n = 0;
            for (Sensor sensor: listaSensores) {
                if (event.sensor == sensor) {
                    for (int i=0; i<event.values.length; i++) {
                        aTextView[n][i].setText(Float.toString(event.values[i]));
                    }
                }
                n++;
            }
        }
    }

}
