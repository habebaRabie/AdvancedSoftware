
abstract class Person {

    protected String userName;
    protected String password;
    protected String email;
    protected String phoneNumber;

    Person() {
    }

    Person(String userName, String password, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword() {
        return password;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
    
}
