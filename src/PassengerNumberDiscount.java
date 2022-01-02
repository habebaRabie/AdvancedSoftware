
public class PassengerNumberDiscount extends Discount {
    Ride ride;

    PassengerNumberDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String getDescription() {
        return ride.description + " , Passenger Number Discount";
    }

    @Override
    public double cost() {
        return 0.95* (ride.cost());
    }
}
