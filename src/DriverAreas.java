
import java.util.ArrayList;
import java.util.Scanner;

public class DriverAreas {

    ArrayList<String> favouriteArea = new ArrayList<>();

    public void addFavAreas() {
        System.out.println("Enter the area you want to add");
        Scanner inputArea = new Scanner(System.in);
        favouriteArea.add(inputArea.nextLine());
    }

    public ArrayList<String> getAllArea() {
        return favouriteArea;
    }
}
