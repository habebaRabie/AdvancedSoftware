package Discount;

import ModulesPackage.Ride;

public class BirthdayDiscount extends Discount {
    Ride ride;

    public BirthdayDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String getDescription() {
        return ride.getDescription() + " , Birthday Discount.Discount";
    }

    @Override
    public double cost() {
        return 0.9* (ride.cost());
    }
}
