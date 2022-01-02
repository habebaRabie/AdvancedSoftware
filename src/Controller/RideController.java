package Controller;

import ModulesPackage.*;
import Discount.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RideController {
    public EventController event = new EventController();
    public int requestUserRide(String source, String destination, int passengersNum , String username) {
        String sql = "insert into ride (source, destination, passengersNumber,user) values (?, ?, ?, ?, ?, ?)";
        int RideId = 0;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, source);
            ins.setString(2, destination);
            ins.setInt(3, passengersNum);
            ins.setString(4, username);
            ins.setString(5, "WAITING");
            ins.setDate(6, Date.valueOf(LocalDate.now()));
            RideId = ins.getMaxRows();
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
        DriverController.driversACTIVE(RideId);
        return RideId;

        //ModulesPackage.Ride ride = new ModulesPackage.Ride();
        //return ride.requestRide();
    }

    public ArrayList<String> RidePrice(int RideId) {
        ArrayList<String> Result = new ArrayList<>();
        String req , user =  "", destination ="";
        double passengersNumber=0;
        Date date = null, userDate = null;
        boolean firstRide = true;
        String sql2 = "select passengersNumber , date , user, destination from ride where RideID = " + RideId;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql2);
            passengersNumber = RS.getInt("passengersNumber");
            date = RS.getDate("date");
            user = RS.getString("user");
            destination = RS.getString("destination");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql3 = "select firstRide , birthDate  from user where username = " + user;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql3);
            passengersNumber = RS.getInt("passengersNumber");
            firstRide = RS.getBoolean("firstRide");
            userDate = RS.getDate("birthDate");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Boolean desHasDiscount = false;
        String sql4 = "select discount from Area where location = " + destination;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql4);
            desHasDiscount = RS.getBoolean("discount");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql5 = "select count(*) from Holiday where date = " + date;
        Boolean exist = false;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql5);
            exist = RS.getBoolean(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql = "select driverName , price from RideRequest where RideID = " + RideId;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql);
            while (RS.next()){
                Ride.price =  RS.getDouble("price");
                Ride ride = new Price(Ride.price);
                if(firstRide){
                    ride = new FirstRideDiscount(ride);
                }
                if(passengersNumber==2){
                    ride = new PassengerNumberDiscount(ride);
                }
                if(userDate.equals(date)){
                    ride = new BirthdayDiscount(ride);
                }
                if(desHasDiscount){
                    ride = new FavouriteAreaDiscount(ride);
                }
                if(exist){
                    ride = new PublicHolidayDiscount(ride);
                }
                req = RS.getString("driverName") + " "+ String.valueOf(ride.cost());
                Result.add(req);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Result;
    }


    public void SelectRidePrice(String driverName , int RideID){
        String req;
        String sql = "select price from RideRequest where driverName = " +driverName ;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql);
            Ride.price=RS.getDouble("price");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        String sql2 = "UPDATE ride SET price ='?' , driver ='?' where rideID = ?" ;
        try(Connection conn = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = conn.prepareStatement(sql2);
            ins.setDouble(1, Ride.price);
            ins.setString(2, driverName);
            ins.setInt(3, RideID);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql3 = "select user, date from ride where rideID = " + RideID;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql3);
            String username =RS.getString("user");
            Date d = RS.getDate("date");
            String date = d.toString();
            event.event2(date, username);
            event.event3(date , username,"CAPTAINARRIVED", driverName);
            TimeUnit.MINUTES.sleep(5);
            event.event3(date , username,"ARRIVIEDTODISTINATAION", driverName);
        } catch (SQLException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
