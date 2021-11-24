import java.util.HashMap;
import java.util.Scanner;

public class Rating {
    HashMap <User, Integer> rating;

    public void getRating(){
        for (HashMap.Entry<User, Integer> rate : rating.entrySet()) {
            System.out.println(rate.getKey().getUserName() + " the offer is : " + rate.getValue());
        }
    }

    public void setRating(User user){
        System.out.println("Please rate the selected driver");
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
