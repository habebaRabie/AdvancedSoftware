import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


enum UserStatus {
    ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE
}

public class User extends Person {

    private UserStatus status;
    private int passangerNum;
    private boolean fistRide;
    //userRequestRide rideRequest;

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }

//    public userRequestRide getRideRequest() {
//        return rideRequest;
//    }

    public User() {
        this.status = UserStatus.ACTIVE;
    }
}
