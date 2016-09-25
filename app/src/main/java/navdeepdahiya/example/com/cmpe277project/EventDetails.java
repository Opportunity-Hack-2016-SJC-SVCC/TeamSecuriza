package navdeepdahiya.example.com.cmpe277project;

/**
 * Created by navdeepdahiya on 8/2/16.
 */
        import android.app.AlertDialog;
        import android.app.ProgressDialog;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.apache.http.client.methods.HttpGet;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.net.URI;
        import java.util.ArrayList;

public class EventDetails extends AppCompatActivity {

    private static final String TAG = "AsyncTestFragment";

    // get some fake data
    //private static final String TEST_URL                 = "http://jsonplaceholder.typicode.com/comments";
    private String GET_URL  = "http://54.69.248.179:8080/location/ms/rest/get";
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";

    ProgressDialog progress;
    //private TextView ourTextView;
    Context context;
    //private ListView ourTextView;
    private TextView ourTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);


        context = this;

        ourTextView = (TextView) findViewById(R.id.myTextViewED);


        try {
            HttpGet httpGet = new HttpGet(new URI(GET_URL));
            RestTask task = new RestTask(context, ACTION_FOR_INTENT_CALLBACK);
            task.execute(httpGet);
            progress = ProgressDialog.show(context, "Getting Data ...", "Waiting For Results...", true);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }



    }

    @Override
    public void onPause() {
        super.onPause();
        context.unregisterReceiver(receiver);
    }


    @Override
    public void onResume() {
        super.onResume();
        context.registerReceiver(receiver, new IntentFilter(ACTION_FOR_INTENT_CALLBACK));
    }



    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<String> mylist = new ArrayList<String>();
            // clear the progress indicator
            if (progress != null) {
                progress.dismiss();
            }


            String columnArr[]= new String[3];
            String response = intent.getStringExtra(RestTask.HTTP_RESPONSE);
            try {
                //JSONObject obj = new JSONObject(response);
                //String nameE = getJSONFromStringBuffer(response);
                JSONObject jsonObj = new JSONObject(response.toString());

/*
               for(int i=0; i< jsonObj.length();i++){
                   columnArr[i] = jsonObj.getJSONObject(0).getString("latitude");
                   columnArr[i]


               }*/

                Toast.makeText(getBaseContext(), "ND "+new String(
                        new JSONArray(response).getJSONObject(0).getString("latitude")), Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "ND "+new String(
                        new JSONArray(response).getJSONObject(0).getString("longitude")), Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "ND "+new String(
                        new JSONArray(response).getJSONObject(0).getString("streetName")), Toast.LENGTH_LONG).show();
                //response = nameE;

            } catch (JSONException e) {
                e.printStackTrace();
            }


            ourTextView.setText(response);
            Log.i(TAG, "RESPONSE = " + response);
            Intent intent2 = new Intent(context, MapsActivity.class);
            intent2.putStringArrayListExtra("incident", mylist);
            startActivity(intent2);
            //
            // my old json code was here. this is where you would parse it.
            //
        }
    };

    private String getJSONFromStringBuffer(String stringJSONArray) throws JSONException {
        return new String(
                new JSONArray(stringJSONArray).getJSONObject(0).getString("name")).toString();
    }
}
