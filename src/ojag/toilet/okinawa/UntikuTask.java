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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;

public class UntikuTask extends Activity {
    static final String FILE_NAME = "data";
    static ArrayList<String> array = new ArrayList<String>();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        new UntikuTaskE().execute();
        
        LinearLayout ll = new LinearLayout(this);
        ll.setBackgroundResource(R.drawable.kaihatuteamlogo);
        setContentView(ll);
    }

    class UntikuTaskE extends AsyncTask<String, Integer, String> {
        
        @Override
        protected String doInBackground(String... params) {
        	 StringBuilder uri = new StringBuilder("http://untikun.heroku.com/untiku.json");
        	 HttpGet request = new HttpGet(uri.toString());
        	 HttpClient httpClient = new DefaultHttpClient();
        	 HttpResponse httpResponse = null;
        	 
        	 try {
        	     httpResponse = httpClient.execute(request);
        	 } catch (Exception e) {
        	     array.clear();
        	     array.add("error");
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
    		    	   array.add(jsonArray.getString(i));
    		       }
        	    } catch (Exception e) {
                    array.clear();
                    array.add("error");
        	    }
        	 } else {
        	     array.clear();
        	     array.add("error");
        	 }
        	 return null;
    	}
        
    
        @Override
        protected void onPostExecute(String result) {
            if(sp.getString("start","1st").equals("1st")) {
                // 一回目の起動
                editor = sp.edit();
                editor.putString("start","done").commit();
                Intent i = new Intent(getApplicationContext(),Info.class);
                i.putExtra("FROM", "From_StartActivity");
                startActivityForResult(i,0);
            } else {
                // 二回目以降の起動
                Intent i = new Intent(getApplicationContext(),ToileActivity.class);
                startActivityForResult(i,0);
            }
        }
    }
    
    // 実機の「戻る」ボタンでこの画面に戻ってきたとき、正常終了。
    protected void onActivityResult(int reqcode, int rescode, Intent data) {
        setResult(RESULT_OK);
        finish();
    }
}

