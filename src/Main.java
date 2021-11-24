/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Admin myAdmin = new Admin();
    static User user;
    static Driver driver;
    static HashMap<User, userRequestRide> allUserRides = new HashMap<>();
    static ArrayList<Driver> allDrivers = Admin.getAllDrivers();
    static HashMap<Driver, userRequestRide> allRides = new HashMap<>();


    public static void actUser() {
        Scanner input = new Scanner(System.in);
        Scanner chooseDriver = new Scanner(System.in);
        System.out.println("1- Request Ride\n2- Rate a driver\n3- Check driver's rating\n4- Exit");
        //ArrayList<userRequestRide> allUserRide;
        switch (input.nextLine()) {
            case "1":
                userRequestRide newRide = new userRequestRide();
                newRide.userRequest(user);
                if (newRide.getMyRide().getMystatus() != rideStatus.REJECTED) {
                    allUserRides.put(user, newRide);
                    allRides.put(newRide.getRideDriver(), newRide);
                }
                actUser();
                break;
            case "2":

                if (allUserRides.size() == 0) {
                    System.out.println("there is no ride yet");
                    actUser();
                    break;
                }

                for (HashMap.Entry<User, userRequestRide> rideDriver : allUserRides.entrySet()) {
                    if (rideDriver.getKey() == user){
                        System.out.println(rideDriver.getValue().getRideDriver().getUserName());
                        System.out.println("Do you want to rate this driver\n 1-Yes\n 2-No");
                        int ch = chooseDriver.nextInt();
                        if (ch == 1){
                            user.rateDriver(rideDriver.getValue().getRideDriver());
                        }
                    }

                }
                /*System.out.println("Please choose the driver you want to rate");
                //get(chooseDriver.nextInt()-1)
                int ch = chooseDriver.nextInt();
                if (ch > allUserRides.size()) {
                    System.out.println("worng choice");
                    actUser();
                    break;
                }*/
               // user.rateDriver(allUserRides.get(ch - 1).getRideDriver());
                actUser();
                break;
            case "3":
                if (allUserRides.size() == 0) {
                    System.out.println("there is no ride yet to see there rating");
                    actUser();
                    break;
                }

                /*for (userRequestRide rideDriver : allUserRides) {
                    System.out.println(counter + "- " + rideDriver.getRideDriver().getUserName());
                    counter++;
                }*/

                for (HashMap.Entry<User, userRequestRide> rideDriver : allUserRides.entrySet()) {
                    if (rideDriver.getKey() == user){
                        System.out.println(rideDriver.getValue().getRideDriver().getUserName());
                        System.out.println("Do you want to see this driver rating\n 1-Yes\n 2-No");
                        int ch = chooseDriver.nextInt();
                        if (ch == 1){
                            System.out.println(rideDriver.getValue().getRideDriver().getAvgRate());
                        }
                    }

                }
                //System.out.println("Please choose the driver you want to rate");
                //Driver driver = allUserRides.get(chooseDriver.nextInt() - 1).getRideDriver();
                //System.out.println("The average rate of the driver you have selected is " + driver.getAvgRate());
                actUser();
                break;
            case "4":
                //allUserRides.clear();
                // user = null;
                break;
            default:
                System.out.println("Please choose from the valid choices");
                actUser();
                break;
        }
    }

    public static void actDriver() {
        System.out.println("1- List all rides in your area\n"
                + "2- Add new Area to your favourite areas\n"
                + "3- List all users rating\n4- Exit");
        Scanner input = new Scanner(System.in);
        switch (input.nextLine()) {
            case "1":
                if (allRides.size() == 0) {
                    System.out.println("there is no available ride yet in your area");
                    actDriver();
                    break;
                }
                for (HashMap.Entry<Driver, userRequestRide> ride : allRides.entrySet()) {
                    if (ride.getKey() == driver) {
                        System.out.println(ride.getValue().getMyRide());
                    }
                }
                actDriver();
                break;
            case "2":
                driver.setMyAreas();
                actDriver();
                break;
            case "3":
                driver.getMyRate();
                actDriver();
                break;
            case "4":
                break;

            default:
                System.out.println("Please choose from the valid choices");
                break;
        }
    }

    public static void actAdmin() {
        System.out.println("1- Verify all Drivers\n2- Suspend\n3- Exit");
        Scanner choice3 = new Scanner(System.in);
        switch (choice3.nextLine()) {
            case "1":
                myAdmin.verify();
                actAdmin();
                break;
            case "2":
                System.out.println("1- Suspend User\n2- Suspend Driver\n3- Exit");
                Scanner choice4 = new Scanner(System.in);
                switch (choice4.nextLine()) {

                    case "1":
                        System.out.println("Please enter the username of the user you want to suspend");
                        Scanner choice5 = new Scanner(System.in);
                        if (myAdmin.searchUser(choice5.nextLine())) {
                            System.out.println("Suspended successfully");
                        } else {
                            System.out.println("Didn't found that user");
                        }
                        break;
                    case "2":
                        System.out.println("Please enter the username of the driver you want to suspend");
                        Scanner choice6 = new Scanner(System.in);
                        if (myAdmin.searchDriverExistance(choice6.nextLine())) {
                            System.out.println("Suspended successfully");
                        } else {
                            System.out.println("Didn't found that user");
                        }
                        break;
                    case "3":
                        break;

                    default:
                        System.out.println("Please choose from the valid choices");
                        break;
                }
                actAdmin();
                break;
            case "3":
                break;

            default:
                System.out.println("Please choose from the valid choices");
                break;
        }
    }

    public static void showOptions() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("1- Register\n2- Log in\n3- Exit");
            switch (input.nextLine()) {
                case "1":
                    System.out.println("1- Register as User\n2- Register as Driver\n3- Exit");
                    Scanner choice = new Scanner(System.in);
                    switch (choice.nextLine()) {
                        case "1":
                            user = new User();
                            user.register();
                            actUser();
                            break;

                        case "2":
                            driver = new Driver();
                            driver.register();
//                            actDriver();
                            break;
                        case "3":
                            break;

                        default:
                            System.out.println("Please choose from the valid choices");
                            break;
                    }
                    break;
                case "2":
                    System.out.println("1- Log in as User\n2- Log in as Driver\n3- Log in as Admin\n4- Exit");
                    Scanner choice2 = new Scanner(System.in);
                    switch (choice2.nextLine()) {
                        case "1":
                            user = User.loginUser();
                            if (user != null) {
                                actUser();

                            }
                            break;
                        case "2":
                            driver = Driver.loginDriver();
                            if (driver == null) {
                                break;
                            } else {//logged in
                                actDriver();
                            }
                            break;
                        case "3":
                            if (!myAdmin.login()) {
                                break;
                            }
                            actAdmin();
                            break;
                        case "4":
                            break;

                        default:
                            System.out.println("Please choose from the valid choices");
                            break;
                    }
                    break;
                case "3":
                    return;
                //break;
                default:
                    System.out.println("Please choose from the valid choices");
                    break;
            }

        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to our program");
//        myAdmin.login();
        showOptions();

    }
}
