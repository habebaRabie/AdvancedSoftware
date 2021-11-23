import java.util.Scanner;



public class Main {
    static Admin myAdmin;

    public static void actUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("1- Register\n2- Log in\n3- Exit");
    }

    public static void showOptions(){
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to our program\n1- Register\n2- Log in\n3- Exit");
            switch (input.nextLine()){
                case "1":
                    System.out.println("1- Register as User\n2- Register as Driver\n3- Exit");
                    Scanner choice = new Scanner(System.in);
                    switch (choice.nextLine()){
                        case "1":
                            User user = new User();
                            user.register();
                            actUser();

                        case "2":
                            Driver driver = new Driver();
                            driver.register();
                        case "3":
                            break;

                        default:
                            System.out.println("Please choose from the valid choices");
                    }
                case "2":
                    System.out.println("1- Log in as User\n2- Log in as Driver\n2- Log in as Admin\n3- Exit");
                    Scanner choice2 = new Scanner(System.in);
                    switch (choice2.nextLine()){
                        case "1":

                    }

            }
        }

    }



    public static void main(String[] args) {
        myAdmin.login("Admin", "Admin");
        showOptions();

    }
}
