
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Admin extends Person {


    static Person admin = new Admin();
    static ArrayList<Driver> ALLdrivers = new ArrayList<>();

    static ArrayList<User> users = new ArrayList<>();//after login users.add(active logged user)
    static ArrayList<Driver> drivers = new ArrayList<>();

    static public void suspend(Person person) {
        System.out.println("Please enter the userName");

//        if(person instanceof User){
//            removeUser((User) person);
//            for (User u : users){
//                if(person == u){
//                    u.setStatus(UserStatus.SUSPENDED);
//                }
//            }
//        }else{
//            removeDriver((Driver) person);
//            for (Driver d : drivers) {
//                if (person == d) {
//                    d.setDriverStatus(DriverStatus.SUSPENDED);
//                }
//            }
//        }
    }

    public Boolean searchUser(String userName) {
        for(User user: users){
            if(user.userName == userName){
                suspend(user);
                return true;
            }
        }
        return false;
    }

    public Boolean searchDriverExistance(String userName) {
        for(Driver driver: drivers){
            if(driver.userName == userName){
                suspend(driver);
                return true;
            }
        }
        return false;
    }


    public static ArrayList<Driver> getAllDrivers() {
        return drivers;
    }

    public static ArrayList<User> getAllUsers() {
        return users;
    }

    public static void addActiveUser(User user) {
        users.add(user);
    }

    public static void addDriverToSystem(Driver driver) {
        ALLdrivers.add(driver);
    }

    public static void removeDriverToSystem(Driver driver) {
        ALLdrivers.remove(driver);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void addActiveDriver(Driver driver) {
        if (searchDriver(driver)) {
            drivers.add(driver);
        } else {
            System.out.println("This driver doesn't exist in the system");
        }
    }

    public static void removeDriver(Driver driver) {
        drivers.remove(driver);
    }


    public static Boolean searchDriver(Driver driver) {
        if (ALLdrivers.contains(driver)) {
            return true;
        } else {
            return false;
        }
    }


    void login() {
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        userName = input.nextLine();
        password = input.nextLine();

        if (userName == "Admin" && password == "Admin") {
            admin.setUserName(userName);
            admin.setPassword(password);
        } else {
            System.out.println("Wrong information");
        }
    }

    public void verify() {
        int counter =1;
        for (Driver driver : ALLdrivers){
            System.out.println(counter + "- " + driver.getUserName());
            System.out.println("Do you want to verify this driver\n1- Yes\n2- No");
            Scanner choice = new Scanner(System.in);
            int answer = choice.nextInt();
            if(answer == 1){
                addActiveDriver(driver);
                removeDriverToSystem(driver);
                driver.setDriverStatus(DriverStatus.ACTIVE);
            }
//            addActiveDriver(driver);
//            removeDriverToSystem(driver);
//            driver.setDriverStatus(DriverStatus.ACTIVE);
        }
//        System.out.println("choose the driver you want to verify");
//        Scanner choice = new Scanner(System.in);
//
//        addActiveDriver(ALLdrivers.get(choice.nextInt()-1));
//        removeDriverToSystem(ALLdrivers.get(choice.nextInt()-1));
//        (ALLdrivers.get(choice.nextInt()-1)).setDriverStatus(DriverStatus.ACTIVE);

    }

    @Override
    public String getUserName() {
        return this.userName;
    }
}


