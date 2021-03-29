package <%= appPackage %>.util.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class TypeWriter extends TextView {

    private CharSequence mText;
    private int mIndex;
    private long mDelay = 100; // ms
    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public TypeWriter(Context context) {
        super(context);
    }

    public TypeWriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void animateText(CharSequence txt) {
        mText = txt;
        mIndex = 0;
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    private void setCharacterDelay(long m) {
        mDelay = m;
    }

    public void write(String text, int speed) {
        setText("");
        setCharacterDelay(speed);
        animateText(text);
    }
}