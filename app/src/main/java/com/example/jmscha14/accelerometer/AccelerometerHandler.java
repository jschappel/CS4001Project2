package com.example.jmscha14.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Observable;

/**
 * Created by jmscha14 on 9/6/2016.
 */
public class AccelerometerHandler extends Observable implements SensorEventListener{

    SensorManager sensorManager = null;
    Sensor accelerometer = null;



    public AccelerometerHandler(Activity act) {

        //Every activity has a context in is higharcy
        sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_UI);

    }

    //this method is only needed if you have more then one sensor
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //check which senor changed
        if(sensorEvent.sensor.getType()!= Sensor.TYPE_ACCELEROMETER)
            return;

        // notify activity of new acceleration value
        setChanged();
        notifyObservers(sensorEvent.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
