package to.i.le;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.Toast;

public class UntikuTask extends AsyncTask<String, Integer, String> {

	private ToileActivity _ta;

	public UntikuTask(ToileActivity ta) {
        _ta = ta;
	}
	
	@Override
	protected String doInBackground(String... params) {
		
    	 StringBuilder uri = new StringBuilder("http://untikun.heroku.com/untiku.json");
    	 HttpGet request = new HttpGet(uri.toString());
    	 HttpClient httpClient = new DefaultHttpClient();
    	 HttpResponse httpResponse = null;
    	 
    	 try {
    	     httpResponse = httpClient.execute(request);
    	 } catch (Exception e) {
    		 
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
		    	   _ta.untiku_array.add(jsonArray.getString(i));
		       }
    	    } catch (Exception e) {
    	    	
    	    }
    	 }
    	 return null;
	}
	
	@Override
	protected void onPostExecute(String result) {			
		Toast.makeText(_ta, "うんちくデータを取得しました", Toast.LENGTH_SHORT).show();
	}

}