
import java.util.ArrayList;
import java.util.Scanner;

enum UserStatus {
    ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE
}

public class User extends Person {

    private int userID;
    private UserStatus status;
    private static int count;
    userRequestRide rideRequest;

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public static void setCount(int count) {
        User.count = count;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public UserStatus getStatus() {
        return status;
    }

    public static int getCount() {
        return count;
    }

    public userRequestRide getRideRequest() {
        return rideRequest;
    }

    public User() {
        this.userID = count;
        this.status = UserStatus.ACTIVE;
        count++;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    public User(int userID, String userName, String password, String email, String phoneNumber) {
        super(userName, password, email, phoneNumber);
        this.userID = count;
        count++;
    }

    public int getUserID() {
        return userID;
    }

    public void register() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your information: ");
        System.out.println("Username: ");
        userName = input.nextLine();
        System.out.println("Password: ");
        password = input.nextLine();
        System.out.println("Email (optional): press enter to skip it if you want");
        email = input.nextLine();
        System.out.println("Phone Number: ");
        phoneNumber = input.nextLine();
        // status = UserStatus.ACTIVE;
        Admin.addActiveUser(this);
    }

    static User loginUser() {
        ArrayList<User> allUsers = Admin.getAllUsers();
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        String Name = input.nextLine();
        String pass = input.nextLine();
        for (User user : allUsers) {
            if (user.getUserName().equals(Name)) {
                if (user.getPassword().equals(pass)) {
                    System.out.println("Logged in successfully");
                    user.setStatus(UserStatus.ACTIVE); //= UserStatus.ACTIVE;
                    return user;
                } else {
                    System.out.println("Wrong password");
                    return null;
                }
            }

        }
        System.out.println("Wrong password or user name");
        return null;
    }

    /*public User login(){
        ArrayList<User> allUsers = Admin.getAllUsers();
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        userName = input.nextLine();
        password = input.nextLine();
        for(User user: allUsers){
            if(user.userName.equals(userName) ){
                if(user.password.equals(password) ){
                    System.out.println("Logged in successfully");
                    user.status = UserStatus.ACTIVE;
                    return user;
                }
                else{
                System.out.println("Wrong password");
                return null;
                }
            }
            else{
            System.out.println("Wrong username");
             return null;
            }
        }
        return null;
    }
     */
    public Ride requestUserRide() {
        Ride ride = new Ride();
        return ride.requestRide();
    }

    public void setRideRequest(userRequestRide rideRequest) {
        this.rideRequest = rideRequest;
    }

    /*public void requestRide() {
        Scanner input = new Scanner(System.in);
        System.out.println("Where are you?");
        String source = input.nextLine();
        System.out.print("Where do you want to go?");
        String destination = input.nextLine();
        
        Ride ride = new Ride(source, destination);
    }*/
    public void rateDriver(Driver driver) {
        driver.setMyRate(this);
    }
}
