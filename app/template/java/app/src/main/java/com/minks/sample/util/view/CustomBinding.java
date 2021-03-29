package <%= appPackage %>.util.view;

import android.view.View;

import androidx.databinding.BindingAdapter;


public class CustomBinding {


    @BindingAdapter("onViewCreate")
    public static void onViewCreate(View view, View.OnClickListener listener) {
        if (view != null) listener.onClick(view);
    }
}