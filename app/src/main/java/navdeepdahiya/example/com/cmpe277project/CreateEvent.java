package navdeepdahiya.example.com.cmpe277project;

/**
 * Created by navdeepdahiya on 8/2/16.
 */
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;


public class CreateEvent extends AppCompatActivity {


    EditText eventIDE;
    EditText nameE;
    EditText detailE;
    EditText descE;
    EditText strTimeE;
    EditText dateEE;
    EditText endimeE;
    EditText venueE;
    EditText longiE;
    EditText latiE;
    EditText costE;
    EditText phoneE;
    EditText emailIDE;
    EditText orgE;
    EditText cataE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);



        Button submit = (Button) findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id="";
                String latitude="";
                String longitude="";
                String date="";
                String time="";
                String type="";
                String streetName="";


                nameE = (EditText) findViewById(R.id.input_name);
                streetName = nameE.getText().toString();

                id = streetName+123;

                detailE = (EditText) findViewById(R.id.input_date);
                date = detailE.getText().toString();

                descE = (EditText) findViewById(R.id.input_time);
                time = descE.getText().toString();

                descE = (EditText) findViewById(R.id.input_type);
                type = descE.getText().toString();

                //strTimeE = (EditText) findViewById(R.id.input_name);
                //streetName = strTimeE.getText().toString();



                latitude = "37";
                longitude = "-121";
                /*
                longiE = (EditText) findViewById(R.id.input_name);
                longi = longiE.getText().toString();

                latiE = (EditText) findViewById(R.id.input_name);
                lati = latiE.getText().toString();
                */





                RequestTaskPostEvent http = new RequestTaskPostEvent(id,latitude, longitude, date, time, type, streetName );
                Toast.makeText(getBaseContext(), "Started POST operation ", Toast.LENGTH_LONG).show();
                System.out.println("Testing 1 - Send Http GET request");
                try{
                    http.execute("test");

                    Toast.makeText(getBaseContext(), "POST operation done ", Toast.LENGTH_LONG).show();
                }catch(Exception exp) {
                    System.out.println("exceptiont"+ exp);
                }
            }
        });


        //

        Button btnMap = (Button) findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(CreateEvent.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        //


    }


}
