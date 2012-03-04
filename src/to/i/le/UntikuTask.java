package to.i.le;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;

public class UntikuTask extends AsyncTask<ArrayList<String>, Integer, ArrayList<String>> {

	ArrayList<String> list_ = new ArrayList<String>();
	
	public UntikuTask(ArrayList<String> list) {
		super();
		list_ = list;
	}
		
	@Override
	protected ArrayList<String> doInBackground(ArrayList<String>... params) {

    	 HttpClient httpClient = new DefaultHttpClient();
    	 StringBuilder uri = new StringBuilder("http://untikun.heroku.com/untiku.json");
    	 HttpGet request = new HttpGet(uri.toString());
    	 HttpResponse httpResponse = null;
    	 
    	 try {
    	     httpResponse = httpClient.execute(request);
    	 } catch (Exception e) {
    		 return null;
     	 }
    	 
    	 int status = httpResponse.getStatusLine().getStatusCode();
    	 
    	 if (HttpStatus.SC_OK == status) {
    	    try {
    	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	        httpResponse.getEntity().writeTo(outputStream);
    	        
    	        String data = outputStream.toString();
    	        JSONObject rootObject = new JSONObject(data);
    			 JSONArray jsonArray = rootObject.getJSONArray("untiku");
 
    			 for (int i=0; i<jsonArray.length(); i++) {
    				 list_.add(jsonArray.getString(i));
    			 }		 
    	    } catch (Exception e) {
    	    	return null;
    	    }
    	 } else {
    		return null;
    	 }	
		return null;
	}
}