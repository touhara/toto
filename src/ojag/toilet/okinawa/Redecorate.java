package ojag.toilet.okinawa;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Redecorate extends Activity {

    private LinearLayout cover;
    private LinearLayout main;
    private ImageView[] btn = new ImageView[8];
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String FILE_NAME = UntikuTask.FILE_NAME;

    public class click implements OnClickListener {
        public void onClick(View arg0) {
            switch (arg0.getId()) {
            case R.id.ImageView00:
                main.setBackgroundResource(R.drawable.bg00);
                editor.putInt("BACKGROUND", 0).commit();
                break;
            case R.id.ImageView01:
                main.setBackgroundResource(R.drawable.bg01);
                editor.putInt("BACKGROUND", 1).commit();
                break;
            case R.id.ImageView02:
                main.setBackgroundResource(R.drawable.bg02);
                editor.putInt("BACKGROUND", 2).commit();
                break;
            case R.id.ImageView03:
                main.setBackgroundResource(R.drawable.bg03);
                editor.putInt("BACKGROUND", 3).commit();
                break;
            case R.id.ImageView04:
                cover.setBackgroundResource(R.drawable.cv00);
                editor.putInt("COVER", 0).commit();
                break;
            case R.id.ImageView05:
                cover.setBackgroundResource(R.drawable.cv01);
                editor.putInt("COVER", 1).commit();
                break;
            case R.id.ImageView06:
                cover.setBackgroundResource(R.drawable.cv02);
                editor.putInt("COVER", 2).commit();
                break;
            case R.id.ImageView07:
                cover.setBackgroundResource(R.drawable.cv03);
                editor.putInt("COVER", 3).commit();
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

        sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        editor = sp.edit();

        btn[0] = (ImageView) findViewById(R.id.ImageView00);
        btn[1] = (ImageView) findViewById(R.id.ImageView01);
        btn[2] = (ImageView) findViewById(R.id.ImageView02);
        btn[3] = (ImageView) findViewById(R.id.ImageView03);
        btn[4] = (ImageView) findViewById(R.id.ImageView04);
        btn[5] = (ImageView) findViewById(R.id.ImageView05);
        btn[6] = (ImageView) findViewById(R.id.ImageView06);
        btn[7] = (ImageView) findViewById(R.id.ImageView07);

        for (int i = 0; i < 8; i++) {
            btn[i].setOnClickListener(new click());
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
