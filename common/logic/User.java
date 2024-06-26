package common.logic;

/**
 * Created by abdul on 3/18/2017.
 */
public class User {

    private int id;
    private String type;
    private String firstName;
    private String secondName;
    private String password;

    public User(){

    }

    public User(int id, String type, String firstName, String secondName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
