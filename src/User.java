import java.util.Scanner;

enum UserStatus {ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE}

public class User extends Person{

    int userId;
    UserStatus stat;

    userRequestRide rideRequest;

    public Ride requestUserRide(){
        Ride ride = new Ride();
        return ride.requestRide();
    }

    public void setRideRequest(userRequestRide rideRequest) {
        this.rideRequest = rideRequest;
    }

    @Override
    void login(Person person) {

    }

    @Override
    void login() {
        //stat = UserStatus.ACTIVE;
    }
}
