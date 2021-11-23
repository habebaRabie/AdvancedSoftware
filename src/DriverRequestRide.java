public class DriverRequestRide implements Subject, Observer{

    Ride myRide;



    @Override
    public void subscribe(Person person) {
        if(person instanceof User){

        }

    }

    @Override
    public void unsubscribe(Person person) {

    }

    @Override
    public void sendNotification() {

    }

    @Override
    public void update() {

    }
}
