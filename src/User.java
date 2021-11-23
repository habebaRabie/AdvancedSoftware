import java.util.Scanner;

enum UserStatus {ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE}

public class User extends Person{

    private int userID;
    private UserStatus status;
    private static int count;
    
    public User() {
        this.userID = count;
        this.status = UserStatus.ACTIVE;
        count++;
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
        System.out.println("Email (optional): ");
        email = input.nextLine();
        System.out.println("Phone Number: ");
        phoneNumber = input.nextLine();
        status = UserStatus.ACTIVE;
    }

    userRequestRide rideRequest;

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
    
    public void update(){
        
    }
}
