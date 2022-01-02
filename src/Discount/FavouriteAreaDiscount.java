package Discount;

import ModulesPackage.*;


public class FavouriteAreaDiscount extends Discount {
    Ride ride;

    public FavouriteAreaDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String getDescription() {
        return ride.getDescription() + " , Favourite Area Discount.Discount";
    }

    @Override
    public double cost() {
        return 0.9* (ride.cost());
    }
}
