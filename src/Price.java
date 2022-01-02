public class Price extends Ride {
    double price = 0.0;

    public Price(double price){
        description = "No Discounts";
        this.price = price;
    }
//    public void setPrice(double price){
//        this.price = price;
//    }

    @Override
    public double cost() {
        return price;
    }
}
