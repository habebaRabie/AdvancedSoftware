

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Admin extends Person {

    static Person admin = new Admin();
    static ArrayList<Driver> ALLdrivers = new ArrayList<Driver>();

    static ArrayList<User> users = new ArrayList<User>();//after login users.add(active logged user)
    static ArrayList<Driver> drivers = new ArrayList<Driver>();

    static public void suspend(Person person) {
        System.out.println("Please enter the userName");

        if (person instanceof User) {
            removeUser((User) person);
            for (User u : users) {
                if (person == u) {
                    u.setStatus(UserStatus.SUSPENDED);
                }
            }
        } else {
            removeDriver((Driver) person);
            for (Driver d : drivers) {
                if (person == d) {
                    d.setDriverStatus(DriverStatus.SUSPENDED);
                }
            }
        }
    }

    public Boolean searchUser(String userName) {
        for (User user : users) {
            if (user.userName.equals(userName)) {
                suspend(user);
                return true;
            }
        }
        return false;
    }

    public Boolean searchDriverExistance(String userName) {
        for (Driver driver : drivers) {
            if (driver.userName.equals(userName)) {
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

    boolean login() {
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        userName = input.nextLine();
        password = input.nextLine();

        if (userName.equals("Admin") && password.equals("Admin")) {
            admin.setUserName(userName);
            admin.setPassword(password);
            return true;
        } else {
            System.out.println("Wrong information");
            return false;
        }
    }

    public void verify() {
        ArrayList<Driver> driv = new ArrayList<Driver>();
        if (ALLdrivers.size() == 0) {
            System.out.println("there is no drivers need to be verified");
            return;
        }
        for (Driver driver : ALLdrivers) {
            System.out.println("Do you want to verify " + driver.getUserName() + "\n1- Yes\n2- No");

            Scanner choice = new Scanner(System.in);
            int answer = choice.nextInt();
            if (answer == 1) {
                driv.add(driver);
                driver.setDriverStatus(DriverStatus.ACTIVE);
                addActiveDriver(driver);
            }
        }
        for (Driver driver : driv) {
            removeDriverToSystem(driver);
        }
        driv.removeAll(driv);
    }

    @Override
    public String getUserName() {
        return this.userName;
    }
}
