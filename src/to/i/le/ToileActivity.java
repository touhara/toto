package to.i.le;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;


public class ToileActivity extends Activity {



	private AudioManager m_am;
    private int max_vol;
    private SoundPool m_sp;
    private int sound_id;

    ScrollView sc;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        
    	ListView listView = getListView();
    	listView.setAdapter(getAdapter());
    	listView.setOnScrollListener(new lv_sc());
    	
    	
        sc = (ScrollView)findViewById(R.id.scrollView1);
        sc.setOnTouchListener(new sc_touch());
        sc.post(new Runnable() {
			@Override
			public void run() {
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



	String[] ini_BASE_DATA = { 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n", 
			"\n\n\n\n\n\n\n\n\n\n"  };

	String[] BASE_DATA = { 
		"\n*\n", 
		"\n***\n", 
		"\n*****\n", 
		"\n*******\n", 
		"\n*********\n",
		"\n***********\n", 
		"\n*************\n", 
		"\n***************\n", 
		"\n*****************\n",
		"\n*******************\n" };

	String TEXT = "text";
	ListView mListView;
	SimpleAdapter mAdapter;
	List<Map<String, String>> mList;
	int listposition;

	ListView getListView() {
		if (mListView == null) {
			mListView = (ListView) findViewById(R.id.ListView01);
			mListView.setScrollingCacheEnabled(false);
		}
		return mListView;
	}
	SimpleAdapter getAdapter() {
		if (mAdapter == null) {
			mAdapter = new SimpleAdapter(this, getList(), R.layout.row, new String[] { TEXT }, new int[] { R.id.text });
		}
		return mAdapter;
	}

	List<Map<String, String>> getList() {
		if (mList == null) {
			mList = new ArrayList<Map<String, String>>();
			addListData();
		}
		return mList;
	}
	
	int ini = 1;
	void addListData() {
		List<Map<String, String>> list = getList();
		if(ini == 1){
			for (String c : ini_BASE_DATA) {
				Map<String, String> map = new HashMap<String, String>();
				list.add(0,map);
				map.put(TEXT, c);
			}	
			ini = 0;
		}
		
		for (String b : BASE_DATA) {
			Map<String, String> map = new HashMap<String, String>();
			list.add(0, map);
			map.put(TEXT, b);
		}
	}

	
	public class lv_sc extends Activity implements OnScrollListener {
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
	
			if (firstVisibleItem == 0) {
				addListData();
				getListView().invalidateViews();
				
				mListView.setSelectionFromTop(BASE_DATA.length + 1, 0);
				listposition = totalItemCount-visibleItemCount+5;
			}
			
			//ListView →　引き出してる感
			if (firstVisibleItem+visibleItemCount == totalItemCount) {
				sc.scrollTo(0, 10);
				mListView.setVisibility(View.GONE);
				sc.setVisibility(View.VISIBLE);
			}
		}
	
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
		
	}
	
	
	public class sc_touch extends Activity implements OnTouchListener {
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			//引き出してる感 →　ListView
			if (sc.getScrollY() == 0) {
				mListView.setSelectionFromTop(listposition, 0);
				mListView.setVisibility(View.VISIBLE);
				sc.setVisibility(View.GONE);
			}
			return false;
		}
	}
	
}
