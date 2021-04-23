package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    /*
    El método comienza indicando el Layout de la actividad y obteniendo
    el TextView salida, donde mostraremos los resultados. A continuación
     vamos a vamos a utilizar el método getSystemService para solicitar
     al sistema servicios específicos. Este método pertenece a la clase
     Context (Como somos Activity también somos Context) y será muy
     utilizados para acceder a gran cantidad de servicios del sistema.
     Al indicar como parámetro SENSOR_SERVICE, indicamos que queremos
     utilizar los sensores. Lo haremos a través del objeto sensorManager.
      En primer lugar llamamos al método getSensorList() del objeto para
      que nos de listaSensores, una lista de objetos Sensor. La siguiente
       línea recorre todos los elementos de esta lista parar llamar a su
        método getName() para mostrar el nombre de sensor.
     */

    private TextView salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salida = (TextView) findViewById(R.id.salida);
        SensorManager sensorManager = (SensorManager)
                getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: listaSensores) {
            log(sensor.getName());
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (!listaSensores.isEmpty()) {
            Sensor orientationSensor = listaSensores.get(0);
            sensorManager.registerListener(
                    (SensorEventListener) this, orientationSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (!listaSensores.isEmpty()) {
            Sensor acelerometerSensor = listaSensores.get(0);
            sensorManager.registerListener(
                    (SensorEventListener) this, acelerometerSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);

        if (!listaSensores.isEmpty()) {
            Sensor magneticSensor = listaSensores.get(0);
            sensorManager.registerListener((SensorEventListener) this, magneticSensor,
                    SensorManager.SENSOR_DELAY_UI);}
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);

        if (!listaSensores.isEmpty()) {
            Sensor temperatureSensor = listaSensores.get(0);
            sensorManager.registerListener((SensorEventListener) this, temperatureSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }
        /*
        Comenzamos consultando si disponemos de un sensor de orientación. Para ello
        preguntamos al sistema que nos de todos los sensores de este tipo llamando
        a getSensorList(). Si la lista no está vacía obtenemos el primer elemento
        (el 0). Es necesario registrar cada tipo de sensor por separado para poder
        obtener información de él. El método registerListener() toma como primer
        parámetro un objeto que implemente el interface SensorEventListener,
        veremos a continuación cómo se implementa esta interfaz (se indica this
        porque la clase que estamos definiendo implementará este interfaz para
        recoger eventos de sensores). El segundo parámetro es el sensor que
        estamos registrando. Y el tercero indica al sistema con qué frecuencia
        nos gustaría recibir actualizaciones del sensor. Acepta cuatro posibles
         valores, de menor a mayor frecuencia tenemos: SENSOR_DELAY_NORMAL,
         SENSOR_DELAY_UI, SENSOR_DELAY_GAME y SENSOR_DELAY_FASTEST.

        Esta indicación sirve para que el sistema estime cuánta atención necesitan
         los sensores, pero no garantiza una frecuencia concreta.

        2. Para que nuestra clase implemente el interface que hemos comentado
        añade a la declaración de la clase:
        implements SensorEventListener

         */
    }

    private void log(String string) {
        salida.append(string + "\n");
    }

    @Override
    public void onSensorChanged(SensorEvent evento) {
        //Cada sensor puede provocar que un thread principal pase por aquí
        //así que sincronizamos el acceso (se verá más adelante)
        synchronized (this) {
            switch (evento.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:
                    for (int i = 0; i < 3; i++) {
                        log("Orientación " + i + ": " + evento.values[i]);
                    }
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    for (int i = 0; i < 3; i++) {
                        log("Acelerómetro " + i + ": " + evento.values[i]);
                    }
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    for (int i = 0; i < 3; i++) {
                        log("Magnetismo " + i + ": " + evento.values[i]);
                    }
                    break;
                default:
                    for (int i = 0; i < evento.values.length; i++) {
                        log("Temperatura " + i + ": " + evento.values[i]);
                    }//fin de for
            }//fin de switch case
        }//fin de synchronized(this)
    }//fin de metodo onSensorChanged

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
