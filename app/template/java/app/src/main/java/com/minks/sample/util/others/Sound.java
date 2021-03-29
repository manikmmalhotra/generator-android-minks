package <%= appPackage %>.util.others;

import android.content.Context;
import android.media.MediaPlayer;


public class Sound {

    private MediaPlayer musicPlayer;
    private MediaPlayer effectPlayer;
    private int length;
    private static Sound singleton;

    public static Sound get() {
        if (singleton == null) singleton = new Sound();
        return singleton;
    }

    private Sound() {
        if (musicPlayer == null) {
            musicPlayer = new MediaPlayer();
            effectPlayer = new MediaPlayer();
            length = 0;
        }
    }

    public Sound clear() {
        musicPlayer.reset();
        musicPlayer.release();
        musicPlayer = null;
        return this;
    }

    public Sound start(Context context, int resid) {
        clear();
        musicPlayer = MediaPlayer.create(context, resid);
        musicPlayer.start();
        return this;
    }

    public Sound stop() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            this.clear();
            length = 0;
        }
        return this;
    }

    public Sound pause() {
        if (musicPlayer != null) {
            musicPlayer.pause();
            length = musicPlayer.getCurrentPosition();
        }
        return this;
    }

    public Sound resume(Context context, int resId) {
        if (musicPlayer != null) clear();
        musicPlayer = MediaPlayer.create(context, resId);
        musicPlayer.seekTo(length);
        musicPlayer.start();
        return this;
    }

    public Sound loop(boolean loop) {
        if (musicPlayer != null) {
            musicPlayer.setLooping(loop);
        }
        return this;
    }

    public Sound effectSound(Context context, int resId) {
        effectPlayer.reset();
        effectPlayer.release();
        effectPlayer = null;
        effectPlayer = MediaPlayer.create(context, resId);
        effectPlayer.start();
        effectPlayer.setLooping(false);
        return this;
    }
}
