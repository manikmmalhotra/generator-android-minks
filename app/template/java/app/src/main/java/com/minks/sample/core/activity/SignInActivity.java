package <%= appPackage %>.core.activity;

import <%= appPackage %>.base.BaseActivity;
import <%= appPackage %>.core.model.User;
import <%= appPackage %>.core.viewmodel.SignInViewModel;
import <%= appPackage %>.databinding.SignInView;
import <%= appPackage %>.util.data.Cache;
import <%= appPackage %>.util.data.RxFirebase;
import <%= appPackage %>.util.others.Strings;


public class SignInActivity extends BaseActivity<SignInView, SignInViewModel> {

    public boolean check(User user) {
        if (Strings.empty(user.getId())) return hideAndToast("Please enter your ID.");
        else if (Strings.empty(user.getPw())) return hideAndToast("Please enter a password.");
        else return true;
    }

    public void staySignedIn(boolean stay, User user) {
        if (stay) {
            preference().setString("id", user.getId());
            preference().setString("pw", user.getPw());
        } else {
            preference().setString("id", null);
            preference().setString("pw", null);
        }
    }

    public void signIn(boolean stay, User user) {
        RxFirebase.signIn()
                .success(u -> this.staySignedIn(stay, u))
                .success(u -> moveAndFinish(MainActivity.class))
                .fail(u -> hideAndToast("Login failed."))
                .subscribe(user);
    }

    public void cachedSignIn(boolean stay, User user) {
        this.showProgress();
        String key = Strings.key(user.getId());

        RxFirebase.from("user")
                .child(key)
                .access(User.class)
                .next(Cache::copyUser)
                .next(u -> signIn(stay, user))
                .subscribe();
    }
}