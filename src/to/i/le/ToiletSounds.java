package to.i.le;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

class ToiletSounds{
    private AudioManager _am;
    private SoundPool _sp;
    //TODO: リファクタ
    private int sound_id1;
    private int sound_id2;
    private int sound_id3;
    private int sound_id4;
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
             _sp.play(sound_id1, vol, vol, 1, 0, 1.0F);
             break;
         case PIYO:
             _sp.play(sound_id2, vol, vol, 1, 0, 1.0F);
             break;
         case CHARAN:
             _sp.play(sound_id3, vol, vol, 1, 0, 1.0F);
             break;
         case SING:
             _sp.play(sound_id4, vol, vol, 1, 0, 1.0F);
             break;
         }
     }
     
    public void load_sound_file(){
        _am = (AudioManager)_ta.getSystemService(Context.AUDIO_SERVICE);
        //最大5つの音を重ねて再生する
        _sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        //ファイル読み込み
        sound_id1 = _sp.load(_ta, R.raw.river, 0);
        sound_id2 = _sp.load(_ta, R.raw.bin091019064957001, 0);
        sound_id3 = _sp.load(_ta, R.raw.bin070719184706001, 0);
        sound_id4 = _sp.load(_ta, R.raw.zatou, 0);
     }
     
     public void unload_sound_file(){
    	 //TODO: リファクタ
         _sp.stop(sound_id1);
         _sp.unload(sound_id1);
         _sp.stop(sound_id2);
         _sp.unload(sound_id2);
         _sp.stop(sound_id3);
         _sp.unload(sound_id3);
         _sp.stop(sound_id4);
         _sp.unload(sound_id4);
         _sp.release();
     }
 }
