import java.util.ArrayList;
import java.util.Scanner;

public class userRequestRide implements Subject{

    Ride myRide;
    User user;
    ArrayList<Driver> myDriver; //myDriver.get(0)


    public void userRequest (User client){
        this.user = client;
        myRide = client.requestUserRide();
        searchAreas(myRide.getSource());
//        listAllRides(myDriver.get(0));
//        return myDriver;
    }

    public void searchAreas(String source){ //on the user point of view
        ArrayList<Driver> myavailableDrivers = Admin.getAllDrivers();
        for(Driver driver: myavailableDrivers){
            for(String area: driver.getMyAreas()){
                if(myRide.source == area){
                    myDriver.add(driver);
                    break;
                }
            }
        }
    }

//    public void listAllRides(Driver driver){ //on the user point of view
//        for(String area: driver.getMyAreas()){
//
//        }
//    }

    @Override
    public void subscribe(Person person) {

    }

    @Override
    public void unsubscribe(Person person) {

    }

    @Override
    public void sendNotification() {

    }
}
