package <%= appPackage %>.util.others;


public class Strings {

    public static String key(String key) {
        return key.replaceAll("\\.", "")
                .replaceAll(" ", "");
    }

    public static boolean empty(String str) {
        return str == null || str.replaceAll(" ", "").equals("");
    }
}