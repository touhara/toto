package to.i.le;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ScrollView;



public class ToileActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final ScrollView sc = (ScrollView)findViewById(R.id.scrollView1);
        
        sc.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sc.fullScroll(sc.FOCUS_DOWN);
			}
		});
    }
    
}