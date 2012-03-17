package ojag.toilet.okinawa;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;


public class UntikuTask extends AsyncTask<String, Integer, String> {
    static ArrayList<String> _array = new ArrayList<String>(); 
      
    @Override
    protected String doInBackground(String... params) {
    	 StringBuilder uri = new StringBuilder("http://untikun.heroku.com/untiku.json");
    	 HttpGet request = new HttpGet(uri.toString());
    	 HttpClient httpClient = new DefaultHttpClient();
    	 HttpResponse httpResponse = null;
    	 
    	 try {
    	     httpResponse = httpClient.execute(request);
    	 } catch (Exception e) {
    	     _array.clear();
    	     _array.add("error");
    	     return null;
     	 }

    	 int status = httpResponse.getStatusLine().getStatusCode();
    	 
    	 if(HttpStatus.SC_OK == status) {
		    try {
		       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		       httpResponse.getEntity().writeTo(outputStream);
		       String data = outputStream.toString();
		       JSONObject rootObject = new JSONObject(data);
		       JSONArray jsonArray = rootObject.getJSONArray("untiku");

		       for(int i=0; i<jsonArray.length(); i++) {
		    	   _array.add(jsonArray.getString(i));
		       }
    	    } catch (Exception e) {
                _array.clear();
                _array.add("error");
    	    }
    	 } else {
    	     _array.clear();
    	     _array.add("error");
    	 }
    	 return null;
	}
}
