package com.example.campo_magnetico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // fuente https://www.youtube.com/watch?v=DNl9Bbx7318
    TextView textView;
    private static SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(sensor != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else{
            Toast.makeText(this, "Not supported", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float azimuth = Math.round(sensorEvent.values[0]);
        float pitch = Math.round(sensorEvent.values[1]);
        float roll = Math.round(sensorEvent.values[2]);

        double tesla = Math.sqrt((azimuth * azimuth) + (pitch * pitch) + (roll * roll));

        String texto = String.format("0.0f", tesla);
        textView.setText(texto + "uT");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
