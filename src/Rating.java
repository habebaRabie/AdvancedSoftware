package swproject;
import java.util.HashMap;

public class Rating {
    HashMap <User, Integer> rating;
//    Driver driver;

    public HashMap<User, Integer> getMyRate(){
        return rating;
    }

    public void setRating(User user, int rate){
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
