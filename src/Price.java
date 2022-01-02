import Ride.Ride;

public abstract class Price extends Ride {
    double price = 0.0;

    public Price(){
        description = "No Discounts";
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double cost(double price){
        return price;
    }

}
