package <%= appPackage %>.util.data;

import <%= appPackage %>.core.model.User;


@SuppressWarnings("unchecked")
public class Cache {
    private static User user = new User();

    public static void copyUser(User user) {
        Cache.user = user;
    }

    public static User readUser() {
        return Cache.user;
    }
}
