
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    public ArrayList<User> activeUsers;
    //public ArrayList<Driver> activeDrivers;

    public Database() {
        activeUsers = new ArrayList<>();
        //activeDrivers = new ArrayList<>();
    }
    
    public void addUser(User user){
        activeUsers.add(user);
        System.out.println("You are now registered in the system.");
    }
    
//    public void addDriver(Driver driver){
//        activeDrivers.add(driver);
//        System.out.print("You are now registered in the system.");
//    }
    
    public User login(String username, String password) {
        for (int i = 0; i < activeUsers.size(); i++) {
            if (activeUsers.get(i).getUserName() == username && activeUsers.get(i).getPassword() == password) {
                return activeUsers.get(i);
            }
        }
        System.out.println("incorrect username or password");
        return null;
    }
    
//    public Driver login(String username, String password) {
//            for(int i=0; i<activeDrivers.size(); i++){
//                if(activeDrivers.get(i).userName == username && activeDrivers.get(i).password == password){
//                    return activeDrivers.get(i);
//                }
//            }
//            System.out.println("incorrect username or password");
//            return null;
//    }
}
