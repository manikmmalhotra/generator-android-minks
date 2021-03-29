package <%= appPackage %>.core.viewmodel;

import <%= appPackage %>.R;
import <%= appPackage %>.base.BaseViewModel;
import <%= appPackage %>.core.activity.SignUpActivity;
import <%= appPackage %>.core.model.User;
import <%= appPackage %>.util.data.Data;
import <%= appPackage %>.util.view.OnXML;


public class SignUpViewModel extends BaseViewModel {
    public Data<String> id = new Data<>();
    public Data<String> pw = new Data<>();
    public Data<String> name = new Data<>();

    @OnXML(resid = R.layout.sign_up)
    public void signUp(SignUpActivity activity) {
        User user = new User();
        user.setId(id.get());
        user.setPw(pw.get());
        user.setName(name.get());

        if (activity.check(user))
            activity.signUp(user);
    }
}
