import java.util.ArrayList;

enum DriverStatus {ACTIVE, REJECTED, SUSPENDED, PENDING, WAITING, OFFLINE}

public class Driver extends Person{

    private int driverID;
    private String nationalId;
    private String drivingLicense;
    private DriverStatus state = DriverStatus.PENDING;
//    private Rating myRate;

    private DriverAreas myAreas;

    public  ArrayList<String> getMyAreas() {
        return myAreas.getAllArea();
    }

    public void setMyAreas(String area) {
        myAreas.addFavAreas(area);
    }
//private DriverStatus;

    Driver () { }

    Driver (String userName, String password,
            String email, String phoneNumber,
            String nationalId, String drivingLicense, int driverID) {

        super(userName, password, email, phoneNumber);
        this.nationalId = nationalId;
        this.drivingLicense = drivingLicense;
        this.driverID = driverID;
    }

    public void setNationalId (String nationalId) { this.nationalId = nationalId; }
    public void setDrivingLicense (String drivingLicense) { this.drivingLicense = drivingLicense; }
    public void setDriverID (int driverID) { this.driverID = driverID; }
    public void setDriverStatus (DriverStatus state) { this.state = state; }

    public String getNationalId () { return  nationalId; }
    public String getDrivingLicense () { return  drivingLicense; }
    public int getDriverID () { return  driverID; }
    public DriverStatus getState() { return state; }

//    public ArrayList<Driver> searchAvailableDrive(String src){
//        ArrayList<Driver> availableDrivers = new ArrayList<>();
//        if
//
//        return availableDrivers;
//    }

    @Override
    void login(Person person) {

    }

    @Override
    void login() {

    }

}
