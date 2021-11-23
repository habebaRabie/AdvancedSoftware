import java.util.HashMap;
import java.util.Scanner;

public class Rating {
    HashMap <User, Integer> rating;

    public HashMap<User, Integer> getMyRate(){
        return rating;
    }

    public void setRating(User user){
        System.out.println("Please rate your driver in the last ride");
        Scanner input = new Scanner(System.in);
        int rate = input.nextInt();
        rating.put(user, rate);
    }

    public double calcAvgRate(){
        double averageRate = 0;
        for (int i : rating.values()){
            averageRate+= i;
        }
        averageRate = averageRate / rating.size();
        return averageRate;
    }

}
