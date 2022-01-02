

public class FirstRideDiscount extends Discount {
    Discount discount;

    FirstRideDiscount(Discount discount){
        this.discount = discount;
    }

    @Override
    public String Description() {
        return discount.description + " , First Ride.Ride Discount";
    }

    @Override
    public double cost() {
        return 0.9* (discount.cost());
    }
}
