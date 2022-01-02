package Discount;

import ModulesPackage.Ride;

public class PassengerNumberDiscount extends Discount {
    Ride ride;

    public PassengerNumberDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String getDescription() {
        return ride.getDescription() + " , Passenger Number Discount.Discount";
    }

    @Override
    public double cost() {
        return 0.95* (ride.cost());
    }
}
