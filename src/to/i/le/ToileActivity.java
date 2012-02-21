package to.i.le;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class ToileActivity extends Activity {
    private ButtonClickListener click_listener;
    private ToiletSounds toilet_sounds;
    
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
                sc.fullScroll(View.FOCUS_DOWN);
            }
        });
        
        // 音声ファイルを読み込む
        toilet_sounds = new ToiletSounds(ToileActivity.this);
        toilet_sounds.load_sound_file();
        
        click_listener = new ButtonClickListener();
        
        ImageButton btn1 = (ImageButton)findViewById(R.id.btn1);
        btn1.setOnClickListener(click_listener);
        
        ImageButton btn2 = (ImageButton)findViewById(R.id.btn2);
        btn2.setOnClickListener(click_listener);
        
        ImageButton btn3 = (ImageButton)findViewById(R.id.btn3);
        btn3.setOnClickListener(click_listener);
        
        ImageButton btn4 = (ImageButton)findViewById(R.id.btn4);
        btn4.setOnClickListener(click_listener);
    }
    
    @Override
    protected void onResume() {
        toilet_sounds.load_sound_file();
        super.onResume();
    }

    @Override
    protected void onPause() {
        toilet_sounds.unload_sound_file();
        super.onPause();
    }
    
    @Override
    protected void onDestroy(){
        toilet_sounds.unload_sound_file();
        super.onDestroy();
    }
    
    // ボタン押したときの処理を記述
    class ButtonClickListener implements OnClickListener{
        @Override
        public void onClick(View v){
            switch(v.getId()){
            case R.id.btn1:
                toilet_sounds.play(ToiletSounds.type.JAVAA);
                break;
            case R.id.btn2:
                toilet_sounds.play(ToiletSounds.type.PIYO);
                break;
            case R.id.btn3:
                toilet_sounds.play(ToiletSounds.type.CHARAN);
                break;
            case R.id.btn4:
                toilet_sounds.play(ToiletSounds.type.SING);
                break;
            }
        }
    }
}
