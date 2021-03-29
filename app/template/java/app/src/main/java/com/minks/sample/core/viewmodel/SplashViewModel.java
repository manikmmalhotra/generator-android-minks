package <%= appPackage %>.core.viewmodel;

import <%= appPackage %>.R;
import <%= appPackage %>.base.BaseViewModel;
import <%= appPackage %>.core.activity.SplashActivity;
import <%= appPackage %>.util.view.OnXML;


public class SplashViewModel extends BaseViewModel {

    @OnXML(resid = R.layout.splash)
    public void splash(SplashActivity activity) {
        if (activity.isRemembered())
            activity.autonomousSignIn();

        else activity.delayAndMove(2500);
    }
}