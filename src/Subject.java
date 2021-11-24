package swproject;
import java.util.ArrayList;
import java.util.List;

public interface Subject {
    List<Observer> observers = new ArrayList<Observer>();

    public void subscribe(Person person);
    public void unsubscribe(Person person);
    public void sendNotification();


}