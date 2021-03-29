package <%= appPackage %>.core.viewmodel;

import <%= appPackage %>.R;
import <%= appPackage %>.base.BaseViewModel;
import <%= appPackage %>.core.activity.SignInActivity;
import <%= appPackage %>.core.activity.SignUpActivity;
import <%= appPackage %>.core.model.User;
import <%= appPackage %>.util.data.Data;
import <%= appPackage %>.util.view.OnXML;


public class SignInViewModel extends BaseViewModel {
    public Data<String> id = new Data<>();
    public Data<String> pw = new Data<>();
    public Data<Boolean> stay = new Data<>(false);

    @OnXML(resid = R.layout.sign_in)
    public void signIn(SignInActivity activity) {
        User user = new User();
        user.setId(id.get());
        user.setPw(pw.get());

        if (activity.check(user))
            activity.cachedSignIn(stay.get(), user);
    }

    @OnXML(resid = R.layout.sign_in)
    public void moveToSignUp(SignInActivity activity) {
        activity.move(SignUpActivity.class);
    }
}
