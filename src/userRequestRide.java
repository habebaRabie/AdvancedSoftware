//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class userRequestRide {
//
//    Ride.Ride myRide;
//    User.User user;
//    Driver.Driver rideDriver;
//
//    public Ride.Ride getMyRide() {
//        return myRide;
//    }
//
//    public User.User getUser() {
//        return user;
//    }
//
//    public Driver.Driver getRideDriver() {
//        return rideDriver;
//    }
//
//    public void userRequest(User.User client) {
//        this.user = client;
//        ArrayList<Driver.Driver> myDriver = new ArrayList();
//        myRide = client.requestUserRide();
//
//        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
//        String query = "insert into ride (user, source, destination, status) values (?, ?, ?, ?)";
//        try (Connection conn = DriverManager.getConnection(url)) {
//            PreparedStatement ins = conn.prepareStatement(query);
//            ins.setString(1, user.getUserName());
//            ins.setString(2, myRide.getSource());
//            ins.setString(3, myRide.getDestnation());
//            ins.setString(4, "PENDINGDRIVER");
//            ins.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        myDriver = searchAreas(myRide.getSource());
//        if (myDriver.size() == 0) {
//            System.out.println("There are no driver in this place");
//            myRide.setMystatus(Ride.rideStatus.REJECTED);
//            return;
//        }
//        // System.out.println(myDriver.get(0).userName);
//        driverRequest(myDriver);
//    }
//
//    public ArrayList<Driver.Driver> searchAreas(String source) { //on the user point of view
//        ArrayList<Driver.Driver> myavailableDrivers = Admin.Admin.getAllDrivers();
//        ArrayList<Driver.Driver> myDriver = new ArrayList();
//        for (Driver.Driver driver : myavailableDrivers) {
//            for (String area : driver.getMyAreas()) {
//                if (myRide.getSource().equals(area)) {
//                    myDriver.add(driver);
//                    break;
//                }
//            }
//        }
//
//        return myDriver;
//    }
//
//    public void driverRequest(ArrayList<Driver.Driver> myDriver) {
//        HashMap<Driver.Driver, Double> offers = new HashMap<Driver.Driver, Double>();
//        for (Driver.Driver driver : myDriver) {
//            System.out.println("The ride is from " + myRide.getSource() + " to " + myRide.getDestnation());
//            System.out.print("Please enter your offer to this ride: ");
//            Scanner in = new Scanner(System.in);
//            double price = in.nextDouble();
//            double driverOffer = driver.rideOffer(price);
//            offers.put(driver, driverOffer);
//        }//Map.Entry<String, Tab> entry : hash.entrySet()
//        int i = 1;
//        for (HashMap.Entry<Driver.Driver, Double> offr : offers.entrySet()) {
//            System.out.println(i + " - the driver name is : " + offr.getKey().userName + " the offer is : " + offr.getValue());
//            i++;
//        }
//        if (i == 1) {
//            System.out.println("No Drivers is valid right now");
//            return;
//        }
//        System.out.println(i + "- Cancel the trip");
//        System.out.println("Please enter the choice of offer you need :");
//        Scanner input = new Scanner(System.in);
//        int choice = input.nextInt();
//        if (choice == i) { //cancelled
//            myRide.setMystatus(Ride.rideStatus.REJECTED);
//        } else {
//            //Driver.Driver selectedOfferDriver = null;
//
//            int counter = 1;
//            for (Driver.Driver driver : offers.keySet()) {
//                if (counter == choice) {
//                    rideDriver = driver;
//                    myRide.setPrice(offers.get(driver));
//                    break;
//                }
//                counter++;
//            }
//            rideDriver.setDriverStatus(Driver.DriverStatus.INDRIVE);
//            rideDriver.setMyRides(myRide);
//            myRide.setMystatus(Ride.rideStatus.ACCEPTED);
//        }
//    }
//}
