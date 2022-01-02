
public class PublicHolidayDiscount extends Discount {
    Discount discount;

    PublicHolidayDiscount(Discount discount){
        this.discount = discount;
    }

    @Override
    public String Description() {
        return discount.description + " , public Holiday Discount";
    }

    @Override
    public double cost() {
        return 0.95* (discount.cost());
    }
}
