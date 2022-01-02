import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

enum rideStatus {
    ACCEPTED, REJECTED, PENDINGDRIVER, PENDINGUSER
}

public abstract class Ride {

    String description="New Ride";
    Event event = new Event();

    public String getDescription(){
        return description;
    }
    public abstract double cost();

//    private String source;
//    private String destnation;
//    private double price;
//    private rideStatus mystatus;




    public int requestUserRide(String source, String destination, int passengersNum , String username) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql = "insert into ride (source, destination, passengersNumber,user) values (?, ?, ?, ?, ?, ?)";
        int RideId = 0;
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
            RideId = ins.getMaxRows();
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
        return RideId;

        //Ride ride = new Ride();
        //return ride.requestRide();
    }

    public ArrayList<String> RidePrice(int RideId) {
        ArrayList<String> Result = new ArrayList<>();
        String req , user =  "", destination ="";
        double price = 0, passengersNumber=0;
        Date date = null, userDate = null;
        boolean firstRide = true;
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql2 = "select passengersNumber , date , user, destination from ride where RideID = " + RideId;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql2);
            passengersNumber = RS.getInt("passengersNumber");
            date = RS.getDate("date");
            user = RS.getString("user");
            destination = RS.getString("destination");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql3 = "select firstRide , birthDate  from user where username = " + user;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql3);
            passengersNumber = RS.getInt("passengersNumber");
            firstRide = RS.getBoolean("firstRide");
            userDate = RS.getDate("birthDate");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Boolean desHasDiscount = false;
        String sql4 = "select discount from Area where location = " + destination;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql4);
            desHasDiscount = RS.getBoolean("discount");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql5 = "select count(*) from Holiday where date = " + date;
        Boolean exist = false;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql5);
            exist = RS.getBoolean(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql = "select driverName , price from RideRequest where RideID = " + RideId;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql);
            while (RS.next()){
                price =  RS.getDouble("price");
                Ride ride = new Price(price);
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

        String sql3 = "select user, date from ride where rideID = " + RideID;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql3);
            String username =RS.getString("user");
            Date d = RS.getDate("date");
            String date = d.toString();
            event.event2(date, username);
            event.event3(date , username,"CAPTAINARRIVED", driverName);
            TimeUnit.MINUTES.sleep(5);
            event.event3(date , username,"ARRIVIEDTODISTINATAION", driverName);
        } catch (SQLException | InterruptedException e) {
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
