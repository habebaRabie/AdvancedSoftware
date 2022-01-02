public class FirstRideDiscount extends Discount {
    Ride ride;

    FirstRideDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String Description() {
        return ride.description + " , First Ride.Ride Discount";
    }

    @Override
    public double cost() {
        return 0.9* (ride.cost());
    }
}
