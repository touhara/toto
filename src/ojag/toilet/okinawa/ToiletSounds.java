package ojag.toilet.okinawa;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

class ToiletSounds{
    private AudioManager _am;
    private SoundPool _sp;
    private int[] sound_id = new int[4];
    private ToileActivity _ta;
    
    public enum type{
        JAVAA, PIYO, CHARAN, SING
    }

    // コンストラクタ
    public ToiletSounds(ToileActivity ta){
        // Activityを引き継ぎ
        _ta = ta;
        // 音声ファイルを読み込む
        load_sound_file();
    }
    
     public void play(type type){
        //着信音の最大値音量を取得
        int max_vol = _am.getStreamMaxVolume(AudioManager.STREAM_RING);
        //現在の音量を取得
         int current_vol = _am.getStreamVolume(AudioManager.STREAM_RING);
        //ボリュームを計算
         float vol = (float)current_vol/max_vol;

         switch(type){
         case JAVAA:
             _sp.play(sound_id[0], vol, vol, 1, 0, 1.0F);
             break;
         case PIYO:
             _sp.play(sound_id[1], vol, vol, 1, 0, 1.0F);
             break;
         case CHARAN:
             _sp.play(sound_id[2], vol, vol, 1, 0, 1.0F);
             break;
         case SING:
             _sp.play(sound_id[3], vol, vol, 1, 0, 1.0F);
             break;
         }
     }
     
    public void load_sound_file(){
        _am = (AudioManager)_ta.getSystemService(Context.AUDIO_SERVICE);
        //最大5つの音を重ねて再生する
        _sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        //ファイル読み込み
        sound_id[0] = _sp.load(_ta, R.raw.tm2_river007, 0);
        sound_id[1] = _sp.load(_ta, R.raw.bin091019064957001, 0);
        sound_id[2] = _sp.load(_ta, R.raw.bin070719184706001, 0);
        sound_id[3] = _sp.load(_ta, R.raw.zatou1, 0);
     }
     
     public void unload_sound_file(){
         for (int i=0; i<4; i++) {
             _sp.stop(sound_id[i]);
             _sp.unload(sound_id[i]);             
         }
         _sp.release();
     }
}