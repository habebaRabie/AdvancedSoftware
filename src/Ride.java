import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

enum rideStatus {
    ACCEPTED, REJECTED, PENDINGDRIVER, PENDINGUSER
}

public abstract class Ride {

    String description="New Ride.Ride";

    public String getDescription(){
        return description;
    }
    public abstract double cost();

//    private String source;
//    private String destnation;
//    private double price;
//    private Ride.rideStatus mystatus;




    public int requestUserRide(String source, String destination, int passengersNum , String username) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql = "insert into ride (source, destination, passengersNumber,user) values (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, source);
            ins.setString(2, destination);
            ins.setInt(3, passengersNum);
            ins.setString(4, username);
            ins.setString(5, "WAITING");
            ins.setDate(6, Date.valueOf(LocalDate.now()));
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User has been registered successfully");
        return 5;

        //Ride ride = new Ride();
        //return ride.requestRide();
    }

    public ArrayList<String> RidePrice(int RideId){
        ArrayList<String> Result = new ArrayList<>();
        String req , user =  "";
        double price = 0, passengersNumber=0;
        Date date = null, userDate = null;
        boolean firstRide = true;
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql2 = "select passengersNumber , date , user from ride where RideID = " + RideId;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql2);
            passengersNumber = RS.getInt("passengersNumber");
            user = RS.getString("user") ;
            date = RS.getDate("date");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql3 = "select firstRide , birthDate  from user where username = " + user;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql3);
            passengersNumber = RS.getInt("passengersNumber");
            firstRide = RS.getBoolean("firstRide") ;
            userDate = RS.getDate("birthDate");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql = "select driverName , price from RideRequest where RideID = " + RideId;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql);
            while (RS.next()){
                //if(firstRide ||userDate.equals(date)){//TODO////location

                    price =  RS.getDouble("price");//*0.9);
               /* }else if(passengersNumber==2){//TODO///////////holiday
                    price =  (RS.getDouble("price")*0.95);
                }else {
                    price = RS.getDouble("price");
                }*/
                req = RS.getString("driverName") + " "+ String.valueOf(price);
                Result.add(req);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Result;
    }

    //TODO/////////////////////////done(tony)//////////
    public void SelectRidePrice(String driverName , int RideID){
        String req;
        double pri =0;
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql = "select price from RideRequest where driverName = " +driverName ;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql);
            pri=RS.getDouble("price");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql2 = "UPDATE ride SET price ='?' , driver ='?' where rideID = ?" ;
        try(Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql2);
            ins.setDouble(1, pri);
            ins.setString(2, driverName);
            ins.setInt(3, RideID);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

  /*  public rideStatus getMystatus() {
        return mystatus;
    }

    public void setMystatus(rideStatus mystatus) {
        this.mystatus = mystatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestnation() {
        return destnation;
    }

    public void setDestnation(String destnation) {
        this.destnation = destnation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Ride.Ride(String source, String destnation) {
        this.source = source;
        this.destnation = destnation;
    }

    public Ride.Ride requestRide() {
        System.out.println("please enter your location");
        Scanner in = new Scanner(System.in);
        String src = in.next();
        System.out.println("please enter the location of destination");
        //Scanner in = new Scanner(System.in);
        String des = in.next();
        Ride.Ride myRide = new Ride.Ride(src, des);
        return myRide;
    }

    public Ride.Ride() {
    }

    @Override
    public String toString() {
        return "Ride.Ride{"
                + "source='" + source + '\''
                + ", destnation='" + destnation + '\''
                + ", price=" + price
                + '}';
    }*/
}
