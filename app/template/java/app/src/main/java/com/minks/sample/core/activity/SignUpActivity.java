package  <%= appPackage %>.core.activity;

import  <%= appPackage %>.base.BaseActivity;
import  <%= appPackage %>.core.model.User;
import  <%= appPackage %>.core.viewmodel.SignUpViewModel;
import  <%= appPackage %>.databinding.SignUpView;
import  <%= appPackage %>.util.data.RxFirebase;
import  <%= appPackage %>.util.others.Strings;


public class SignUpActivity extends BaseActivity<SignUpView, SignUpViewModel> {

    public boolean check(User user) {
        if (Strings.empty(user.getId())) return hideAndToast("Please enter your ID.");
        else if (Strings.empty(user.getPw())) return hideAndToast("Please enter a password.");
        else if (user.getPw().length() < 6) return hideAndToast("Password must be at least 6 characters long.");
        else if (Strings.empty(user.getName())) return hideAndToast("Input your name, please.");
        else return true;
    }

    public void storeUserInformation(User user) {
        String key = Strings.key(user.getId());

        RxFirebase.from("user")
                .child(key)
                .access(User.class)
                .upload(user);
    }

    public void signUp(User user) {
        this.showProgress();

        RxFirebase.signUp()
                .success(this::storeUserInformation)
                .success(u -> hideAndToast("Membership registration was successful."))
                .success(u -> moveAndFinish(SignInActivity.class))
                .fail(u -> hideAndToast("Membership registration failed."))
                .subscribe(user);
    }

}
