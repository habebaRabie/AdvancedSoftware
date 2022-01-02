package ModulesPackage;

import ModulesPackage.Person;

public class Admin extends Person {

    public static String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";


    @Override
    public String getUserName() {
        return this.userName;
    }
}
