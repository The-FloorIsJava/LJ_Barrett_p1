package Model;

public class EmployeeModel {
    private String username;
    private String password;
    private String employeeRole;
    private String firstName;
    private String lastName;

    public EmployeeModel() {

    }
    public EmployeeModel(String username, String password, String employeeRole, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.employeeRole = employeeRole;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

