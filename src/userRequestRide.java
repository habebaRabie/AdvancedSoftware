package swproject;
import java.util.ArrayList;
import java.util.Scanner;

public class userRequestRide {

    Ride myRide;
    User user;
     //myDriver.get(0)


    public void userRequest (User client){
        this.user = client;
        ArrayList<Driver> myDriver = new ArrayList();
        myRide = client.requestUserRide();
        myDriver = searchAreas(myRide.getSource());
//        listAllRides(myDriver.get(0));
//        return myDriver;
    }

    public  ArrayList<Driver> searchAreas(String source){ //on the user point of view
        ArrayList<Driver> myavailableDrivers = Admin.getAllDrivers();
        ArrayList<Driver> myDriver = new ArrayList();
        for(Driver driver: myavailableDrivers){
            for(String area: driver.getMyAreas()){
                if(myRide.source == area){
                    myDriver.add(driver);
                    break;
                }
            }
        }
        return myDriver;
    }

//    public void listAllRides(Driver driver){ //on the user point of view
//        for(String area: driver.getMyAreas()){
//
//        }
//    }


}
