import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends Person {

    static String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";


    @Override
    public String getUserName() {
        return this.userName;
    }
}
