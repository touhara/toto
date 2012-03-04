package to.i.le;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Entrance extends Activity {
	
	public class touch implements OnClickListener {
		public void onClick(View arg0) {

			Intent sex = new Intent(getApplicationContext(),ToileActivity.class);
			switch (arg0.getId()) {
			case R.id.men:
				sex.putExtra("sex","men");
				break;
			case R.id.women:
				sex.putExtra("sex","women");
				break;
			}
			startActivity(sex);
		}
	}

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance);
        
        Button btn_men = (Button)findViewById(R.id.men);
        Button btn_women = (Button)findViewById(R.id.women);
        
        btn_men.setOnClickListener(new touch());
        btn_women.setOnClickListener(new touch());
        
    }
		
}
