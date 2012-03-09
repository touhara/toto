package to.i.le;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Redecorate extends Activity {

	LinearLayout cover;
	LinearLayout center;
	LinearLayout left;
	LinearLayout right;
	Button[] btn = new Button[8];
	SharedPreferences sp;
	SharedPreferences.Editor editor;
	String FILE_NAME = Start.FILE_NAME;

	public class click implements OnClickListener {
		public void onClick(View arg0) {
			switch(arg0.getId()) {
			case R.id.Button00:
				center.setBackgroundResource(R.drawable.c);
				left.setBackgroundResource(R.drawable.a);
				right.setBackgroundResource(R.drawable.b);
				editor.putInt("BACKGROUND",0).commit();
				break;
			case R.id.Button01:
				center.setBackgroundResource(R.drawable.test_c1);
				left.setBackgroundResource(R.drawable.test_a1);
				right.setBackgroundResource(R.drawable.test_b1);
				editor.putInt("BACKGROUND",1).commit();
				break;
			case R.id.Button02:
				center.setBackgroundResource(R.drawable.test_c2);
				left.setBackgroundResource(R.drawable.test_a2);
				right.setBackgroundResource(R.drawable.test_b2);
				editor.putInt("BACKGROUND",2).commit();
				break;
			case R.id.Button03:
				center.setBackgroundResource(R.drawable.test_c3);
				left.setBackgroundResource(R.drawable.test_a3);
				right.setBackgroundResource(R.drawable.test_b3);
				editor.putInt("BACKGROUND",3).commit();
				break;
			case R.id.Button04:
				cover.setBackgroundResource(R.drawable.d);
				editor.putInt("COVER",0).commit();
				break;
			case R.id.Button05:
				cover.setBackgroundResource(R.drawable.test_d1);
				editor.putInt("COVER",1).commit();
				break;
			case R.id.Button06:
				cover.setBackgroundResource(R.drawable.test_d2);
				editor.putInt("COVER",2).commit();
				break;
			case R.id.Button07:
				cover.setBackgroundResource(R.drawable.test_d3);
				editor.putInt("COVER",3).commit();
				break;
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.redecorate);

		cover = (LinearLayout)findViewById(R.id.cover);
		center = (LinearLayout)findViewById(R.id.center);
		left = (LinearLayout)findViewById(R.id.left);
		right = (LinearLayout)findViewById(R.id.right);		
		
    	sp = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
    	editor = sp.edit();
    			
		btn[0] = (Button)findViewById(R.id.Button00);
		btn[1] = (Button)findViewById(R.id.Button01);
		btn[2] = (Button)findViewById(R.id.Button02);
		btn[3] = (Button)findViewById(R.id.Button03);
		btn[4] = (Button)findViewById(R.id.Button04);
		btn[5] = (Button)findViewById(R.id.Button05);
		btn[6] = (Button)findViewById(R.id.Button06);
		btn[7] = (Button)findViewById(R.id.Button07);

		for(int i=0; i<8; i++) {
			btn[i].setOnClickListener(new click());			
		}
		
		
		// カバーの初期設定
		switch(sp.getInt("COVER",0)) {
		case 0:
			cover.setBackgroundResource(R.drawable.d);
			break;
		case 1:
			cover.setBackgroundResource(R.drawable.test_d1);
			break;
		case 2:
			cover.setBackgroundResource(R.drawable.test_d2);			
			break;
		case 3:
			cover.setBackgroundResource(R.drawable.test_d3);			
			break;
		}

		
		//　背景の初期設定
		switch(sp.getInt("BACKGROUND",0)) {
		case 0:
			center.setBackgroundResource(R.drawable.c);
			left.setBackgroundResource(R.drawable.a);
			right.setBackgroundResource(R.drawable.b);
			break;
		case 1:
			center.setBackgroundResource(R.drawable.test_c1);
			left.setBackgroundResource(R.drawable.test_a1);
			right.setBackgroundResource(R.drawable.test_b1);
			break;
		case 2:
			center.setBackgroundResource(R.drawable.test_c2);
			left.setBackgroundResource(R.drawable.test_a2);
			right.setBackgroundResource(R.drawable.test_b2);			
			break;
		case 3:
			center.setBackgroundResource(R.drawable.test_c3);
			left.setBackgroundResource(R.drawable.test_a3);
			right.setBackgroundResource(R.drawable.test_b3);			
			break;
		}

	}
}
