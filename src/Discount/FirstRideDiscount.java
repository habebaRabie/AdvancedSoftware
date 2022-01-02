package Discount;

import ModulesPackage.Ride;

public class FirstRideDiscount extends Discount {
    Ride ride;

    public FirstRideDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String getDescription() {
        return ride.getDescription() + " , First ModulesPackage.Ride.ModulesPackage.Ride Discount.Discount";
    }

    @Override
    public double cost() {
        return 0.9* (ride.cost());
    }
}
