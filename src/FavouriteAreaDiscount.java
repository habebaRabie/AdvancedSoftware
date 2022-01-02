
public class FavouriteAreaDiscount extends Discount {
    Ride ride;

    FavouriteAreaDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String Description() {
        return ride.description + " , Favourite Area Discount";
    }

    @Override
    public double cost() {
        return 0.9* (ride.cost());
    }
}
