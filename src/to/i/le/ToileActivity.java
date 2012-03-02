package to.i.le;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ToileActivity extends Activity {
    private ButtonClickListener click_listener;
    private ToiletSounds toilet_sounds;
	private ListView paper_view;
	private List<String> paper_list;
	private ArrayAdapter<String> view_adapter;
	List<String> untiku_array = new ArrayList<String>();
    String[] str;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //NOTE: 男女の判断情報を受け取る。
        	Intent intent = getIntent();
        	String sex = intent.getStringExtra("sex");
        	if (sex.equals("men")) {
        		setContentView(R.layout.men);
        	} else {
        		setContentView(R.layout.women);
        	}
        
        
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
        
        view_adapter = new ArrayAdapter<String>(this, R.layout.list, R.id.row_textview1, paper_list);
        paper_view.setAdapter(view_adapter);
        paper_view.setOnScrollListener(new ListListener());
        
        //NOTE: スクロール中の背景表示をやめる。
        paper_view.setScrollingCacheEnabled(false);
        
        str = getResources().getStringArray(R.array.untikustr);

        for (int i=0; i<str.length; i++) {
        	untiku_array.add(str[i]);
         }
        add_paper();
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
    	//TODO: うんちくをどこかからか取得したい。
    	if (untiku_array.size() > 0){ 
    		Collections.shuffle(untiku_array);
    		paper_list.add(0, untiku_array.get(0));
    	}
    	for(int i=0; i<10; i++){
    		paper_list.add(0, "\n\n\n\n\n");
    	}
    }
    
    //TODO: 内部クラスでなくて外に分けた方が良い？ 無名クラス使う方が良いのかよくわかってない
    // ボタン押したときの処理を記述
    class ButtonClickListener implements OnClickListener{

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
    	

    	public void onScrollStateChanged(AbsListView view, int scrollState) {
    	}
    }
}
