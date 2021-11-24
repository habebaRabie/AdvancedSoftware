import java.util.ArrayList;
import java.util.Scanner;

enum UserStatus {ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE}

public class User extends Person{

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

    public void login(){
        ArrayList<User> allUsers = Admin.getAllUsers();
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        userName = input.nextLine();
        password = input.nextLine();
        for(User user: allUsers){
            if(user.userName == userName){
                if(user.password == password){
                    System.out.println("Logged in successfully");
                    status = UserStatus.ACTIVE;
                    break;
                }
                System.out.println("Wrong password");
            }
            System.out.println("Wrong username");
        }
    }

    public Ride requestUserRide(){
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
    public void rateDriver(Driver driver){
        driver.setMyRate(this);
    }
}
