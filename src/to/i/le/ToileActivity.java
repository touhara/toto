package to.i.le;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;
import java.util.List;
import android.app.Activity;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
>>>>>>> tototo
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
<<<<<<< HEAD
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class ToileActivity extends Activity {
    private ButtonClickListener click_listener;
    private ToiletSounds toilet_sounds;
	private ListView paper_view;
	private List<String> paper_list;
	private ArrayAdapter<String> view_adapter;
	List<String> array = new ArrayList<String>();
    String[] str;
	
=======
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
    
>>>>>>> tototo
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
<<<<<<< HEAD
               
=======

        
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
        
        

        
>>>>>>> tototo
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
        
        // トイレットペーパーを生成する
        paper_view = (ListView)findViewById(R.id.paperView);
        paper_list = new ArrayList<String>();
        add_paper();
        
        view_adapter = new ArrayAdapter<String>(this, R.layout.list, R.id.row_textview1, paper_list);
        paper_view.setAdapter(view_adapter);
        paper_view.setOnScrollListener(new ListListener());
        
        //NOTE: スクロール中の背景表示をやめる。
        paper_view.setScrollingCacheEnabled(false);
        
        str = getResources().getStringArray(R.array.str);

        for(int i=0; i<str.length; i++) {
        	array.add(str[i]);
        }
   	
        
      
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
   
   
    private void add_paper(){

    	
    	//TODO: データは適当 トイレのうんちくを表示したい。
/*    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	paper_list.add(0, "\n\n\n\n\n\n\n\n");
    	*/
    	for(int i=0; i<array.size(); i++){
    		if(i%10 == 0){
    			paper_list.add(0, array.get(i));
    			Collections.shuffle(array);
    		}else{
    			paper_list.add(0, "\n\n\n\n\n");
    		}
    	}
    	
    }
    
    //TODO: 内部クラスでなくて外に分けた方が良い？ 無名クラス使う方が良いのかよくわかってない
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
    
    class ListListener implements OnScrollListener{
    	
    	@Override
    	public void onScroll(AbsListView view, int firstVisibleItem,
    			int visibleItemCount, int totalItemCount) {

    		if (paper_list.size() >= 1000){
        		//TODO: あたりなどのオチをランダムにしたい
    			paper_list.add(0, "あたり");
    		}else if (firstVisibleItem == 0) {
        		//TODO: 円環リストビューにしたい
    			add_paper();
    			paper_view.invalidateViews();
    			paper_view.setSelectionFromTop(11, 0);
    		}
    	}
    	
    	@Override
    	public void onScrollStateChanged(AbsListView view, int scrollState) {
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
