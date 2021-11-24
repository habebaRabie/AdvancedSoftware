
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Admin extends Person{

    static ArrayList<User> users;//after login users.add(active logged user)
    static ArrayList<Driver> drivers;

    public void suspend(Person person){
        if(person instanceof User){
            removeUser((User) person);
            for (User u : users){
                if(person == u){
                    u.setStatus(UserStatus.SUSPENDED);
                }
            }
        }else{
            removeDriver((Driver) person);
            for (Driver d : drivers) {
                if (person == d) {
                    d.setDriverStatus(DriverStatus.SUSPENDED);
                }
            }
        }
    }

    public static ArrayList<Driver> getAllDrivers(){
        return drivers;
    }

    public void addActiveUser(User user){
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public void addActiveDriver(Driver driver){
        drivers.add(driver);
    }

    public void removeDriver(Driver driver){
        drivers.remove(driver);
    }

    public Boolean searchUser(User user){
        if(users.contains(user)){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean searchDriver(Driver driver){
        if(drivers.contains(driver)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    void login(Person person) {

    }

    @Override
    void login() {
        System.out.println("Please enter your userName and Password");
        Scanner input = new Scanner(System.in);
        Person admin = new Admin();
        admin.setUserName(input.nextLine());
        admin.setPassword(input.nextLine());
    }

    public void verify(Person person){
        if(person instanceof User){
            addActiveUser((User) person);
            for (User u : users){
                if(person == u){
                    u.setStatus(UserStatus.ACTIVE);
                }
            }
        }else{
            addActiveDriver((Driver) person);
            for (Driver d : drivers) {
                if (person == d) {
                    d.setDriverStatus(DriverStatus.ACTIVE);
                }
            }
        }
    }

}


