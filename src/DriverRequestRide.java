

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DriverRequestRide {

    Ride myRide;
    
    public void driverRequest(ArrayList<Driver> myDriver , Ride ride){
         HashMap<Driver , Double> offers = new  HashMap<Driver , Double>();
     for(Driver driver : myDriver){
         System.out.println( "The ride is from"+ride.source + "to" + ride.destnation);
         double driverOffer = driver.rideOffer();
         offers.put(driver, driverOffer);
     }//Map.Entry<String, Tab> entry : hash.entrySet()
     int i =1;
     for (HashMap.Entry<Driver , Double> offr : offers.entrySet() ){
     System.out.println(i + "- the driver name is : "+offr.getKey().userName+ " the offer is : "+ offr.getValue());
     i++;
     }
     System.out.println(i + "- Cancel the trip");
     System.out.println( "Please enter the choice of offer you need :");
     Scanner input = new Scanner(System.in);
     int choice = input.nextInt();
     if(choice == i){
     //canceld
     }else{
         //Object firstKey = myHashMap.keySet().toArray()[0];
//Object valueForFirstKey = myHashMap.get(firstKey);
    double selectedOffer = offers.get(choice-1);
    ride.price = selectedOffer;
    
    
     }
    }

}
