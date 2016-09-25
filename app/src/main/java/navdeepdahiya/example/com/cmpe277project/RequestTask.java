package navdeepdahiya.example.com.cmpe277project;

/**
 * Created by navdeepdahiya on 8/2/16.
 */

        import android.os.AsyncTask;

        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.ByteArrayOutputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;


public class RequestTask extends AsyncTask<String, Void, JSONObject>{

    private String emailID;
    private String password;

    private final String USER_AGENT = "Mozilla/5.0";

    public RequestTask( String emailID, String password) {
        super();
        this.emailID = emailID;
        this.password = password;
    }


    public String getEmailID() {
        return emailID;
    }
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // HTTP GET request
    protected JSONObject doInBackground(String... urls)  {

        try {

            //  String url = "http://bluepages.ibm.com/BpHttpApisv3/slaphapi?ibmperson/%28mail=jingcai@cn.ibm.com%29/.list/byjson?*";

            //String url = "http://52.38.210.40:8080/EventManager/nd/rest/getsign/"+this.emailID+"/"+this.password;

            String url = "http://52.38.210.40:8080/EventManager/nd/rest/getsign/nav@gmail.com";



            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print result
            JSONObject obj1 = new JSONObject(response.toString());
            System.out.println("here123"+obj1.getJSONObject("_id").get("$oid"));
            return obj1;
        }catch(Exception e){
            JSONObject obj3 = new JSONObject();
            System.out.println(e);
            return obj3;
        }
    }

    protected void onPostExecute(String y) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

}
