import java.util.ArrayList;

public class DriverAreas {
    private ArrayList<String> favoriteAreas = new ArrayList<>();
    
    public void addFavArea(){
        System.out.println("Please enter ther favoite areas: ");
        Scanner input =new Scanner (System.in);
        String location = input.nextLine();
        favoriteAreas.add(location);
        System.out.println("The area is added.");
    }

    public ArrayList<String> getFavoriteAreas() {
        return favoriteAreas;
    }

    public void print() {
        System.out.println("Your favorite areas are: ");
        for(int i=0; i<favoriteAreas.size(); i++){
            System.out.print(favoriteAreas.get(i) +", ");
        }
        System.out.println();
    }
}
