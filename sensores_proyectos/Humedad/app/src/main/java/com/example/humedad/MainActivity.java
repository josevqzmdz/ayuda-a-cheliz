package com.example.humedad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // https://www.youtube.com/watch?v=n6cMao3vVRE

    private static final String TAG = "mainactivity";
    private SensorManager sensorManager;
    private Sensor accelerometer, mPressure, mGyro, mMagno, mLight, mTemp, mHumi;
    TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue, xMagnoValue, yMagnoValue,
        zMagnoValue, light, pressure, temp, humi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView) findViewById(R.id.xMagnoValue);
        yMagnoValue = (TextView) findViewById(R.id.yMagnoValue);
        zMagnoValue = (TextView) findViewById(R.id.zMagnoValue);

        light = (TextView) findViewById(R.id.light);
        pressure = (TextView) findViewById(R.id.pressure);
        temp = (TextView) findViewById(R.id.temp);
        humi = (TextView) findViewById(R.id.humi);

        Log.d(TAG, "onCreate: Inicializando los sensores");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer != null){
            sensorManager.registerListener((SensorEventListener) MainActivity.this,
                    accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: registrando el listener del acelerador");
        }else{
            xValue.setText("Acelerometro no disponible en este celular");
            yValue.setText("Acelerometro no disponible en este celular");
            zValue.setText("Acelerometro no disponible en este celular");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mGyro != null){
            sensorManager.registerListener((SensorEventListener) MainActivity.this,
                    mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: registrando el listener del giroscopio");
        }else{
            xGyroValue.setText("giroscopio no disponible en este celular");
            yGyroValue.setText("giroscopio no disponible en este celular");
            zGyroValue.setText("giroscopio no disponible en este celular");

        }

        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mMagno != null){
            sensorManager.registerListener((SensorEventListener) MainActivity.this,
                    mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: registrando el listener del iman");
        }else{
            xMagnoValue.setText("iman no disponible en este celular");
            yMagnoValue.setText("iman no disponible en este celular");
            zMagnoValue.setText("iman no disponible en este celular");
        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(mLight != null){
            sensorManager.registerListener((SensorEventListener) MainActivity.this,
                    mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: registrando el listener de luz");
        }else{
            light.setText("sensor luz no disponible en este celular");
        }

        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mTemp != null){
            sensorManager.registerListener((SensorEventListener) MainActivity.this,
                    mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: registrando el listener de temperatura");
        }else{
            temp.setText("temperatura no disponible en este celular");
        }

        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(mPressure != null){
            sensorManager.registerListener((SensorEventListener) MainActivity.this,
                    mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: registrando el listener de presion");
        }else{
            pressure.setText("presion no disponible en este celular");
        }

        mHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(mHumi != null){
            sensorManager.registerListener((SensorEventListener) MainActivity.this,
                    mHumi, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: registrando el listener de humedad");
        }else{
            humi.setText("humedad no disponible en este celular");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged: X: " + event.values[0]
                + "Y: " + event.values[1] + "Z: " + event.values[2]);

        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xValue.setText("Acelerometro xValue: " + event.values[0]);
            yValue.setText("Acelerometro yValue: " + event.values[1]);
            zValue.setText("Acelerometro zValue: " + event.values[2]);

        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            xGyroValue.setText("Giroscopio xValue: " + event.values[0]);
            yGyroValue.setText("Giroscopio yValue: " + event.values[1]);
            zGyroValue.setText("Giroscopio zValue: " + event.values[2]);

        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            xMagnoValue.setText("Campo magnetico xValue: " + event.values[0]);
            yMagnoValue.setText("Campo magnetico yValue: " + event.values[1]);
            zMagnoValue.setText("Campo magnetico zValue: " + event.values[2]);

        } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
            light.setText("luz: " + event.values[0]);

        } else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            pressure.setText("luz: " + event.values[0]);

        } else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humi.setText("humedad: " + event.values[0]);

        } else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp.setText("temperatura: " + event.values[0]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
