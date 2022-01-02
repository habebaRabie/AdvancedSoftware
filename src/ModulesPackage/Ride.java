package ModulesPackage;


public abstract class Ride {
    String Source, destination;
    int passengersNumber;

    public Ride() {
        Source = "";
        this.destination = "";
        this.passengersNumber = 0;
        this.description = description;
    }

    String description="New ModulesPackage.Ride";
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public abstract double cost();
    public static double price;
}
