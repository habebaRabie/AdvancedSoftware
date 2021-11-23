enum UserStatus {ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE}

public class User extends Person{

    int userId;
    UserStatus stat;

    @Override
    void login(Person person) {

    }

    @Override
    void login() {
        //stat = UserStatus.ACTIVE;
    }
}
