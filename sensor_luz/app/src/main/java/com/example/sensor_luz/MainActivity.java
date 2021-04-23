package com.example.sensor_luz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.lang.Math;

import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.util.Half.EPSILON;

/*
En este ejemplo, el retraso de los datos predeterminado (SENSOR_DELAY_NORMAL)
se especifica cuando se invoca el método registerListener(). El retraso de los
 datos (o la tasa de muestreo) controla el intervalo en el que los eventos
 del sensor se envían a la aplicación a través del método de devolución de
 llamada onSensorChanged(). El retraso de datos predeterminado es adecuado
 para supervisar los cambios típicos de orientación de la pantalla y utiliza
 un retraso de 200,000 microsegundos. Puedes especificar otros retrasos de
 datos, como SENSOR_DELAY_GAME (retraso de 20,000 microsegundos), SENSOR_DELAY_UI
 (retraso de 60,000 microsegundos) o SENSOR_DELAY_FASTEST (retraso de 0
 microsegundos). A partir de Android 3.0 (API nivel 11), también puedes
 especificar el retraso como un valor absoluto (en microsegundos).
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private Sensor mLight;
    private Sensor mSensor;

    // tutorial
    // https://www.youtube.com/watch?v=wEuzdgKDngk

    //objetos de los sensores
    private Sensor lightSensor;
    private Sensor giroscopio;

    private SensorEventListener LightEventListener;
    private View root;
    private float maxValueLuz;

    private float gravedad[] = new float[4];
    private float acceleracion_lineal[] = new float[4];

    // Create a constant to convert nanoseconds to seconds.
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensor = null;
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        Context contexto = getApplicationContext();

        root = findViewById(R.id.root);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
        giroscopio = sensorManager.getDefaultSensor(TYPE_GYROSCOPE);

        // si no hay sensor de luz, raro en los telefonos de hoy
        if(lightSensor == null){
            Toast.makeText(this, "este dispositivo no tiene sensor de luz... acaso estamos en el 2008 otra vez?"
            , Toast.LENGTH_SHORT).show();
            finish();
        }

        maxValueLuz = lightSensor.getMaximumRange();

        LightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // sensor de luz
                float valorLuz = sensorEvent.values[0];
                getSupportActionBar().setTitle("Luminosity: "+valorLuz+" LX");
                int newValue = (int) (255f *  valorLuz / maxValueLuz);
                root.setBackgroundColor(Color.rgb(newValue, newValue, newValue));

                // sensor giroscopio
                // aisla la fuerza de gravedad con un low-pass filter
                final float alpha = (float) 0.8;
                for(int i =0; i < 3; i++){
                    gravedad[i] = (alpha * gravedad[i]) + ((1 - alpha) * sensorEvent.values[i]);
                }

                float valorGiroscopio = sensorEvent.values[0];
                getSupportActionBar().setTitle("Luminosity: "+valorGiroscopio+" LX");
                // elimina la contrubcion de la gravedad con un high-pass filter
                for(int i =0; i < 3; i++){
                    acceleracion_lineal[i] = sensorEvent.values[i] - gravedad[i];
                }

                if (timestamp != 0) {
                    final float dT = (sensorEvent.timestamp - timestamp) * NS2S;
                    // Axis of the rotation sample, not normalized yet.
                    float axisX = sensorEvent.values[0];
                    float axisY = sensorEvent.values[1];
                    float axisZ = sensorEvent.values[2];

                    // Calculate the angular speed of the sample
                    float omegaMagnitude = (float) Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

                    // Normalize the rotation vector if it's big enough to get the axis
                    // (that is, EPSILON should represent your maximum allowable margin of error)
                    if (omegaMagnitude > EPSILON) {
                        axisX /= omegaMagnitude;
                        axisY /= omegaMagnitude;
                        axisZ /= omegaMagnitude;
                    }

                    // Integrate around this axis with the angular speed by the timestep
                    // in order to get a delta rotation from this sample over the timestep
                    // We will convert this axis-angle representation of the delta rotation
                    // into a quaternion before turning it into the rotation matrix.
                    float thetaOverTwo = omegaMagnitude * dT / 2.0f;
                    float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
                    float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
                    deltaRotationVector[0] = sinThetaOverTwo * axisX;
                    deltaRotationVector[1] = sinThetaOverTwo * axisY;
                    deltaRotationVector[2] = sinThetaOverTwo * axisZ;
                    deltaRotationVector[3] = cosThetaOverTwo;

                }
                timestamp = sensorEvent.timestamp;
                float[] deltaRotationMatrix = new float[9];
                SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
                // User code should concatenate the delta rotation we computed with the current rotation
                // in order to get the updated rotation.
                // rotationCurrent = rotationCurrent * deltaRotationMatrix;

            }// fin de metodo onSensorChanged

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
            Toast msg = Toast.makeText(contexto, "si funciona el sensor!", Toast.LENGTH_LONG);
        } else {
            Toast msg = Toast.makeText(contexto, "no funciona el sensor!", Toast.LENGTH_LONG);

        }

        mLight = sensorManager.getDefaultSensor(TYPE_LIGHT);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            List<Sensor> gravSensors = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);
            for(int i=0; i<gravSensors.size(); i++) {
                if ((gravSensors.get(i).getVendor().contains("Google LLC")) &&
                        (gravSensors.get(i).getVersion() == 3)){
                    // Use the version 3 gravity sensor.
                    mSensor = gravSensors.get(i);
                }
            }
        }
        if (mSensor == null){
            // Use the accelerometer.
            if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
                mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            } else{
                // Sorry, there are no accelerometers on your device.
                // You can't play this game.
                Toast msg = Toast.makeText(contexto, "no funciona el sensor!", Toast.LENGTH_LONG);

            }
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
        float lux = event.values[0];
        // Do something with this sensor value.

    }

    protected void onResume() {
        super.onResume();
        //sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(LightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(LightEventListener);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
