
public class FavouriteAreaDiscount extends Discount {
    Discount discount;

    FavouriteAreaDiscount(Discount discount){
        this.discount = discount;
    }

    @Override
    public String Description() {
        return discount.description + " , Favourite Area Discount";
    }

    @Override
    public double cost() {
        return 0.9* (discount.cost());
    }
}
