package Discount;

import ModulesPackage.*;

public class PublicHolidayDiscount extends Discount {
    Ride ride;

    public PublicHolidayDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String getDescription() {
        return ride.getDescription() + " , public Holiday Discount.Discount";
    }

    @Override
    public double cost() {
        return 0.95* (ride.cost());
    }
}
