public class BirthdayDiscount extends Discount {
    Ride ride;

    BirthdayDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String Description() {
        return ride.description + " , Birthday Discount";
    }

    @Override
    public double cost() {
        return 0.9* (ride.cost());
    }
}
