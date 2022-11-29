package Model;

public class EmployeeModel {
    private String username;
    private String password;
    private boolean employeeRoleManager;


    public EmployeeModel() {

    }

    public EmployeeModel(String username, String password, boolean employeeRoleManager) {
        this.username = username;
        this.password = password;
        this.employeeRoleManager = employeeRoleManager;

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

    public boolean getEmployeeRoleManager() {
        return employeeRoleManager;
    }

    public void setEmployeeRoleManager(boolean employeeRole) {
        this.employeeRoleManager = employeeRoleManager;
    }

    @Override

    public String toString() {
         return "Employee{" + "Employee Username is: " + username + " , Employee role manager? :" + employeeRoleManager + "}";

    }


}

