package <%= appPackage %>.core.activity;

import <%= appPackage %>.base.BaseActivity;
import <%= appPackage %>.core.model.User;
import <%= appPackage %>.core.viewmodel.SplashViewModel;
import <%= appPackage %>.databinding.SplashView;
import <%= appPackage %>.util.data.Cache;
import <%= appPackage %>.util.data.RxFirebase;
import <%= appPackage %>.util.others.Strings;

public class SplashActivity extends BaseActivity<SplashView, SplashViewModel> {

    public boolean isRemembered() {
        String remembered = preference().getString("id");
        return remembered != null;
    }

    public void delayAndMove(int mills) {
        handler.postDelayed(() ->
                moveAndFinish(SignInActivity.class), mills);
    }

    public void signIn(User user) {
        RxFirebase.signIn()
                .success(u -> moveAndFinish(MainActivity.class))
                .fail(u -> toast("Login failed."))
                .subscribe(user);
    }

    public void autonomousSignIn() {
        String id = preference().getString("id");
        String key = Strings.key(id);

        RxFirebase.from("user")
                .child(key)
                .access(User.class)
                .next(Cache::copyUser)
                .next(this::signIn)
                .subscribe();
    }
}
