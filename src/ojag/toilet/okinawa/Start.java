package ojag.toilet.okinawa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Start extends Activity {

	static final String FILE_NAME = "data";
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;	
	private final Handler handler = new Handler();
	private final Runnable start = new Runnable() {
        public void run() {
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
    };
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
       // json取得
    	new UntikuTask(ToileActivity.untiku_array).execute();
    	
    	sp = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
       
    	LinearLayout ll = new LinearLayout(this);
    	ll.setBackgroundResource(R.drawable.kaihatuteamlogo);
    	setContentView(ll);

    	handler.postDelayed(start, 2000);//ミリ秒
    }
	
	// 実機の「戻る」ボタンでこの画面に戻ってきたとき、正常終了。
	protected void onActivityResult(int reqcode, int rescode, Intent data) {
	    setResult(RESULT_OK);
	    finish();
	}
	
}