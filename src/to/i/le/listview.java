package to.i.le;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AbsListView.OnScrollListener;

public class listview extends Activity implements OnScrollListener {

	private final static String[] BASE_DATA = { 
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
	
	/** ListViewの要素を表示する為のキー */
	private final static String TEXT = "text";
	/** ListView */
	private ListView mListView;
	/** SimpleAdapter */
	private SimpleAdapter mAdapter;
	/** ListViewに表示するデータのリスト */
	private List<Map<String, String>> mList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView listView = getListView();
		listView.setAdapter(getAdapter());
		listView.setOnScrollListener(this);

	}

	private ListView getListView() {
		if (mListView == null) {
			mListView = (ListView) findViewById(R.id.ListView01);
		}
		return mListView;
	}

	private SimpleAdapter getAdapter() {
		if (mAdapter == null) {
			mAdapter = new SimpleAdapter(this, getList(), android.R.layout.simple_list_item_1, new String[] { TEXT }, new int[] { android.R.id.text1 });
		}
		return mAdapter;
	}

	private List<Map<String, String>> getList() {
		if (mList == null) {
			mList = new ArrayList<Map<String, String>>();
			addListData();
		}
		return mList;
	}

	


	
	/* ListViewのデータを追加する */
	private void addListData() {
		List<Map<String, String>> list = getList();

		for (String b : BASE_DATA) {
			Map<String, String> map = new HashMap<String, String>();
			list.add(0, map);
			map.put(TEXT, b);
		}
	}
	
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (firstVisibleItem == 0) {
			addListData();
			getListView().invalidateViews();
			
			mListView.setSelectionFromTop(BASE_DATA.length + 1, 0);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

}
