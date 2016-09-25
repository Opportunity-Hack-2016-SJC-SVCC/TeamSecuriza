package navdeepdahiya.example.com.cmpe277project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        /*
        Intent intent4 = new Intent(getApplicationContext(), EventDetails.class);
        //Toast.makeText(getBaseContext(), "MMMM Text searched Map NDND !!!  " + loc, Toast.LENGTH_LONG).show();
        startActivityForResult(intent4, 100);
        */
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        /*

        Intent i = getIntent();
        ArrayList<String> list = i.getStringArrayListExtra("incident");

        */

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        //

        Button btnIncident = (Button) findViewById(R.id.btn_incident);
        btnIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MapsActivity.this, CreateEvent.class);
                startActivity(intent);
            }
        });

        //

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 100) && (resultCode == RESULT_OK)) {
            //TextView textViewActFir = (TextView) findViewById(R.id.textViewActFir2);
            // TextView textViewOP = (TextView) findViewById(R.id.textViewOP);
            double lati  = data.getIntExtra("user_latitude", 0);
            double longi  = data.getIntExtra("user_longitude", 0);
            //nearPlaces = (PositionQueue) data.getSerializableExtra("near_places");

            //textViewActFir.setText(grade + " grade is equal to "+score+"%.");
            //Toast.makeText(getBaseContext(), "God is great!!!",Toast.LENGTH_LONG).show();


            //Toast.makeText(getBaseContext(), "Got the data NDNDND!!!", Toast.LENGTH_LONG).show();

            LatLng latLng=null;


            latLng = new LatLng(lati, longi);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Current location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            //mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng , 14.0f) );



                    //latLng = new LatLng(lati, longi);
                    //mMap.addMarker(new MarkerOptions().position(latLng).title(position.name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


            //textViewOP.setText(name + " grade is equal to "+pass+"%.");
            //finish();

        }

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(37.279518, -121.867905);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


    }


    @Override
    public void onLocationChanged(Location location) {
        /*
        // txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        LatLng l1 = new LatLng(location.getLatitude(), location.getLongitude());
        //mMap.addMarker(new MarkerOptions().position(l1).title("Current location"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(l1));
        //mMap.animateCamera(CameraUpdateFactory.newLatLng(l1));
       // mMap.addMarker(new MarkerOptions().position(l1).title("Current location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        //mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(l1 , 14.0f) );

        mMap.addMarker(new MarkerOptions().position(l1).title("Current location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(l1 , 14.0f) );
        */
        /*
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        float speed = location.getSpeed();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        CameraPosition camPos = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(18)
                .bearing(location.getBearing())
                .tilt(70)
                .build();
        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        googleMap.animateCamera(camUpd3);
        */

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
       // googleMap.addMarker(new MarkerOptions().position(latLng));
       // mMap.addMarker(new MarkerOptions().position(latLng).title("Current location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);

       // mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng , 14.0f) );

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }


}
