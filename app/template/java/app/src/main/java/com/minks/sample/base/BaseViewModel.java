package <%= appPackage %>.base;

import androidx.lifecycle.ViewModel;

import <%= appPackage %>.util.data.Data;

public abstract class BaseViewModel extends ViewModel {
    public Data<Integer> progressBar = new Data<>(android.view.View.GONE);

    public void showProgress() {
        this.progressBar.setValue(android.view.View.VISIBLE);
    }

    public void hideProgress() {
        this.progressBar.setValue(android.view.View.GONE);
    }
}
