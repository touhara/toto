package ojag.toilet.okinawa;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Redecorate extends Activity {

    private LinearLayout cover;
    private LinearLayout main;
    private LinearLayout[] llbtn = new LinearLayout[8];
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String FILE_NAME = UntikuTask.FILE_NAME;
    private Button btn;
    private int s_bg, s_cv;
    private int w_bg, w_cv;
    
    public class click implements OnClickListener {
        public void onClick(View arg0) {
            switch (arg0.getId()) {
            case R.id.llbtn00:
                main.setBackgroundResource(R.drawable.bg00);
                w_bg = 0;
                editor.putInt("BACKGROUND", 0);
                break;
            case R.id.llbtn01:
                main.setBackgroundResource(R.drawable.bg01);
                w_bg = 1;
                editor.putInt("BACKGROUND", 1);
                break;
            case R.id.llbtn02:
                main.setBackgroundResource(R.drawable.bg02);
                w_bg = 2;
                editor.putInt("BACKGROUND", 2);
                break;
            case R.id.llbtn03:
                main.setBackgroundResource(R.drawable.bg03);
                w_bg = 3;
                editor.putInt("BACKGROUND", 3);
                break;
            case R.id.llbtn04:
                cover.setBackgroundResource(R.drawable.cv00);
                w_cv = 0;
                editor.putInt("COVER", 0);
                break;
            case R.id.llbtn05:
                cover.setBackgroundResource(R.drawable.cv01);
                w_cv = 1;
                editor.putInt("COVER", 1);
                break;
            case R.id.llbtn06:
                cover.setBackgroundResource(R.drawable.cv02);
                w_cv = 2;
                editor.putInt("COVER", 2);
                break;
            case R.id.llbtn07:
                cover.setBackgroundResource(R.drawable.cv03);
                w_cv = 3;
                editor.putInt("COVER", 3);
                break;
            case R.id.button:
                if(s_cv != w_cv && s_bg == w_bg) {
                    Toast.makeText(Redecorate.this, "カバーを変更しました。", Toast.LENGTH_SHORT).show();
                } else if(s_cv == w_cv && s_bg != w_bg) {
                    Toast.makeText(Redecorate.this, "背景を変更しました。", Toast.LENGTH_SHORT).show();
                } else if(s_cv != w_cv && s_bg != w_bg) {
                    Toast.makeText(Redecorate.this, "背景とカバーを変更しました。", Toast.LENGTH_SHORT).show();
                }

                editor.commit();
                finish();
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redecorate);

        cover = (LinearLayout) findViewById(R.id.cover);
        main = (LinearLayout) findViewById(R.id.main);
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new click());
        
        sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        editor = sp.edit();
        w_cv = s_cv = sp.getInt("COVER", 0);
        w_bg = s_bg = sp.getInt("BACKGROUND", 0);
        
        llbtn[0] = (LinearLayout) findViewById(R.id.llbtn00);
        llbtn[1] = (LinearLayout) findViewById(R.id.llbtn01);
        llbtn[2] = (LinearLayout) findViewById(R.id.llbtn02);
        llbtn[3] = (LinearLayout) findViewById(R.id.llbtn03);
        llbtn[4] = (LinearLayout) findViewById(R.id.llbtn04);
        llbtn[5] = (LinearLayout) findViewById(R.id.llbtn05);
        llbtn[6] = (LinearLayout) findViewById(R.id.llbtn06);
        llbtn[7] = (LinearLayout) findViewById(R.id.llbtn07);

        for (int i = 0; i < 8; i++) {
            llbtn[i].setOnClickListener(new click());
        }

        // カバーの初期設定
        switch (sp.getInt("COVER", 0)) {
        case 0:
            cover.setBackgroundResource(R.drawable.cv00);
            break;
        case 1:
            cover.setBackgroundResource(R.drawable.cv01);
            break;
        case 2:
            cover.setBackgroundResource(R.drawable.cv02);
            break;
        case 3:
            cover.setBackgroundResource(R.drawable.cv03);
            break;
        }

        // 　背景の初期設定
        switch (sp.getInt("BACKGROUND", 0)) {
        case 0:
            main.setBackgroundResource(R.drawable.bg00);
            break;
        case 1:
            main.setBackgroundResource(R.drawable.bg01);
            break;
        case 2:
            main.setBackgroundResource(R.drawable.bg02);
            break;
        case 3:
            main.setBackgroundResource(R.drawable.bg03);
            break;
        }
    }
}
