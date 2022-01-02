package ModulesPackage;

public class User extends Person {

    private UserStatus status;
    private int passangerNum;
    private boolean fistRide;

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }

    public User() {
        super();
        this.status = UserStatus.ACTIVE;
    }
}
