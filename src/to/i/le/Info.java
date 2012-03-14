package to.i.le;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Info extends Activity {
	
	Bundle ex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ex = getIntent().getExtras();

		//TODO: URL：　[端末のブラウザを立ち上げる] or [URLの文字列を表示]。
		//TODO: スタートボタン
		
		ScrollView sc = new ScrollView(this);		
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setBackgroundResource(R.drawable.androidmenu);
		ll.setOnClickListener(new OnClickListener() {           
            public void onClick(View v) {
                if(ex.getString("FROM").equals("From_StartActivity")) {
                    Intent i = new Intent(getApplicationContext(),ToileActivity.class);
                    startActivity(i);
                } else {
                    finish();
                }                
            }
        });
		sc.addView(ll);		
		setContentView(sc);
		
	}
}
