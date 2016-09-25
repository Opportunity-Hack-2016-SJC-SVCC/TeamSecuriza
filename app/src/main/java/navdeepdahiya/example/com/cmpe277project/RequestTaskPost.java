package navdeepdahiya.example.com.cmpe277project;

/**
 * Created by navdeepdahiya on 8/2/16.
 */

        import android.os.AsyncTask;
        import android.widget.Toast;

        import java.io.BufferedOutputStream;
        import java.io.BufferedReader;
        import java.io.ByteArrayOutputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;



public class RequestTaskPost extends AsyncTask<String, Void, String>{

    private final String USER_AGENT = "Mozilla/5.0";

    private String name;
    private String emailID;
    private String password;
    private String phone;

    public RequestTaskPost(String name, String emailID, String password, String phone) {
        super();
        this.name = name;
        this.emailID = emailID;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // HTTP Post request
    protected String doInBackground(String... urls)  {

        try {

            String url = "http://52.38.210.40:8080/EventManager/nd/rest/signon";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type","application/json");


            //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
            //String data = "{\"ll\":[{\"id\":\"Q1\",\"la\":\"1\",\"lo\":\"1\",\"ts\":\"1001\"},{\"id\":\"Q2\",\"la\":\"412\",\"lo\":\"912\",\"ts\":\"1000\"},{\"id\":\"Q4\",\"la\":\"4\",\"lo\":\"4\",\"ts\":\"1000\"}]}";

            String data = "{\"name\":\""+this.name+"\",\"emailID\":\""+this.emailID+"\",\"password\":\""+this.password+"\",\"phone\":\""+this.phone+"\"}";

            //Toast.makeText(getBaseContext(), "Started POST operation ", Toast.LENGTH_LONG).show();
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + data);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("ABCD"+response.toString());
            return response.toString();
        }catch(Exception e){
            System.out.println(e);
            return ("TEST");
        }
    }

    protected void onPostExecute(String y) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

}


