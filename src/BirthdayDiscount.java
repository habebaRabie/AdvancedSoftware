public class BirthdayDiscount extends Discount {
    Discount discount;

    BirthdayDiscount(Discount discount){
        this.discount = discount;
    }

    @Override
    public String Description() {
        return discount.description + " , Birthday Discount";
    }

    @Override
    public double cost() {
        return 0.9* (discount.cost());
    }
}
