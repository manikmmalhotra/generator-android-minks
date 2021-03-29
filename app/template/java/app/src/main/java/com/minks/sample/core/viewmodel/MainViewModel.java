package <%= appPackage %>.core.viewmodel;

import <%= appPackage %>.R;
import <%= appPackage %>.base.BaseViewModel;
import <%= appPackage %>.core.activity.MainActivity;
import <%= appPackage %>.core.model.User;
import <%= appPackage %>.util.data.Cache;
import <%= appPackage %>.util.data.Data;
import <%= appPackage %>.util.view.OnXML;


public class MainViewModel extends BaseViewModel {
    public Data<String> greeting = new Data<>();

    @OnXML(resid = R.layout.main)
    public void showInformation(MainActivity activity) {
        User user = Cache.readUser();
        greeting.set(user.getName() + "Welcome.");
    }
}
