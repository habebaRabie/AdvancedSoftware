import java.util.ArrayList;
import java.util.HashMap;
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
        driverRequest(myDriver);
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

    public void driverRequest(ArrayList<Driver> myDriver) {
        HashMap<Driver, Double> offers = new HashMap<Driver, Double>();
        for (Driver driver : myDriver) {
            System.out.println("The ride is from " + myRide.source + " to " + myRide.destnation);
            double driverOffer = driver.rideOffer();
            offers.put(driver, driverOffer);
        }//Map.Entry<String, Tab> entry : hash.entrySet()
        int i = 1;
        for (HashMap.Entry<Driver, Double> offr : offers.entrySet()) {
            System.out.println(i + " - the driver name is : " + offr.getKey().userName + " the offer is : " + offr.getValue());
            i++;
        }
        System.out.println(i + "- Cancel the trip");
        System.out.println("Please enter the choice of offer you need :");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice == i) {
            myRide.setMystatus(rideStatus.REJECTED);
        } else {
            Driver selectedOfferDriver = null;

            int counter = 1;
            for(Driver driver: offers.keySet()){
                if(counter == choice){
                    selectedOfferDriver = driver;
                    myRide.price = offers.get(driver);
                    break;
                }
                counter ++;
            }
            selectedOfferDriver.setDriverStatus(DriverStatus.INDRIVE);
            selectedOfferDriver.setMyRides(myRide);
            myRide.setMystatus(rideStatus.ACCEPTED);
        }
    }
}
