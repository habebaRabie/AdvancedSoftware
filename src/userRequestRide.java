import java.util.ArrayList;
import java.util.Scanner;

public class userRequestRide {

    Ride myRide;
    User user;
    Driver myDriver;

    public void searchAreas(){ //on the user point of view
        System.out.println("Please enter your source and destination");
        Scanner input = new Scanner(System.in);
        myRide.source = input.nextLine();
        myRide.destnation = input.nextLine();

        ArrayList<Driver> myavailableDrivers = Admin.getAllDrivers();
        for(Driver driver: myavailableDrivers){
           // if(myRide.source = driver.getMyAreas())
        }
    }

}
