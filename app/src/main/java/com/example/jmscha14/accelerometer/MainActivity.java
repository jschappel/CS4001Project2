package com.example.jmscha14.accelerometer;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    //Instance Variable
    TextView z_axis = null;
    TextView x_axis = null;
    TextView y_axis = null;
    TextView lat = null;
    TextView longi = null;
    AccelerometerHandler ah = null;
    LocationHandler lh = null;
   private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1; // maybe 11?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Location and Axis Find");

        ActivityCompat.requestPermissions( this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSION_ACCESS_FINE_LOCATION );

        z_axis = (TextView)findViewById(R.id.z_gravity_view);
        x_axis = (TextView)findViewById(R.id.x_gravity_view);
        y_axis = (TextView)findViewById(R.id.y_gravity_view);
        lat = (TextView)findViewById(R.id.lat_gravity_view);
        longi = (TextView)findViewById(R.id.long_gravity_view);
    }


    @Override
    protected void onStart() {
        super.onStart();

        //lat = (TextView)findViewById(R.id.lat_gravity_view);
        //longi = (TextView)findViewById(R.id.long_gravity_view);

        lat.setText(Float.toString(getPreferences(MODE_PRIVATE).getFloat("LATATUDE",(float) 0.0)));
        longi.setText(Float.toString(getPreferences(MODE_PRIVATE).getFloat("LONGITUDE",(float) 0.0)));

    }

    @Override
    protected void onPause() {
        super.onPause();

        getPreferences(MODE_PRIVATE).edit().putFloat("LATATUDE",Float.parseFloat(lat.getText().toString())).apply();
        getPreferences(MODE_PRIVATE).edit().putFloat("LONGITUDE",Float.parseFloat(longi.getText().toString())).apply();


    }

    @Override
    protected void onResume() {
        super.onResume();
        lh = new LocationHandler(this);
        lh.addObserver(this);
        ah = new AccelerometerHandler(this);
        ah.addObserver(this);
    }

    public void update(Observable observable, Object o) {
        if (observable instanceof AccelerometerHandler){
            float [] xyz = (float[]) o;

            z_axis.setText(Float.toString(xyz[2]));
            x_axis.setText(Float.toString(xyz[0]));
            y_axis.setText(Float.toString(xyz[1]));

        }
        else if (observable instanceof LocationHandler) {
            double[] cords = (double[]) o;

            longi.setText(Double.toString(cords[0]));
            lat.setText(Double.toString(cords[1]));
        }
    }
}