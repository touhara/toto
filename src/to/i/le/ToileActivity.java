package to.i.le;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

public class ToileActivity extends Activity {
    private AudioManager m_am;
    private int max_vol;
    private SoundPool m_sp;
    private int sound_id;

    
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
        load_sound_file();
        // 最大音量を指定(着信音)
        m_am = (AudioManager)getSystemService(AUDIO_SERVICE);
        max_vol = m_am.getStreamMaxVolume(AudioManager.STREAM_RING);
        
        ImageButton btn1 = (ImageButton)findViewById(R.id.btn1);
        btn1.setOnClickListener(new ButtonClickListener());
        
        ImageButton btn2 = (ImageButton)findViewById(R.id.btn2);
        btn2.setOnClickListener(new ButtonClickListener());
        
        ImageButton btn3 = (ImageButton)findViewById(R.id.btn3);
        btn3.setOnClickListener(new ButtonClickListener());
        
        ImageButton btn4 = (ImageButton)findViewById(R.id.btn4);
        btn4.setOnClickListener(new ButtonClickListener());
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	load_sound_file();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	unload_sound_file();
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	unload_sound_file();
    }

    private void load_sound_file(){
        m_sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound_id = m_sp.load(this, R.raw.bin070719184706001, 0);
    }
    
    private void unload_sound_file(){
    	m_sp.stop(sound_id);
    	m_sp.unload(sound_id);
    	m_sp.release();
    }
    
    // ボタン押したときの処理を記述
    class ButtonClickListener implements OnClickListener{
    	@Override
    	public void onClick(View v){
    		int current_vol = m_am.getStreamVolume(AudioManager.STREAM_RING);
    		float vol = (float)current_vol/max_vol;
    		switch(v.getId()){
    		case R.id.btn1:
    			Toast.makeText(ToileActivity.this, "hoge", Toast.LENGTH_SHORT).show();
    			m_sp.play(sound_id, vol, vol, 1, 0, 1.0F);
    		case R.id.btn2:
    			Toast.makeText(ToileActivity.this, "huga", Toast.LENGTH_SHORT).show();
    			m_sp.play(sound_id, vol, vol, 1, 0, 1.0F);
    		case R.id.btn3:
    			Toast.makeText(ToileActivity.this, "hago", Toast.LENGTH_SHORT).show();
    			m_sp.play(sound_id, vol, vol, 1, 0, 1.0F);
    		case R.id.btn4:
    			Toast.makeText(ToileActivity.this, "hage", Toast.LENGTH_SHORT).show();
    			m_sp.play(sound_id, vol, vol, 1, 0, 1.0F);
    		}
    	}
    }
}
