
public class PassengerNumberDiscount extends Discount {
    Discount discount;

    PassengerNumberDiscount(Discount discount){
        this.discount = discount;
    }

    @Override
    public String Description() {
        return discount.description + " , Passenger Number Discount";
    }

    @Override
    public double cost() {
        return 0.95* (discount.cost());
    }
}
