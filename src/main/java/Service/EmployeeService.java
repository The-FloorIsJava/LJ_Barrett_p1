package Service;

import DAO.EmployeeDAO;
import Model.EmployeeModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
private EmployeeModel sessionEmployee = null;

private final EmployeeDAO employeeDAO;

public EmployeeService(EmployeeDAO employeeDAO) {
    this.employeeDAO = employeeDAO;
}

public EmployeeModel addEmployee(EmployeeModel employeeModel) {
    List<EmployeeModel> employeeModelList = employeeDAO.findAll();
    List<String> usernames = new ArrayList<>();
    boolean isUnique;

    for (int i = 0; i<employeeModelList.size(); i++) {
        usernames.add(employeeModelList.get(i).getUsername());

    }
    if (usernames.contains(employeeModel.getUsername())) {
        isUnique = false;
    } else {
        isUnique = true;
    }

    if (isUnique) {
        return employeeDAO.create(employeeModel);
    } else {
        return null;
    }
}

public EmployeeModel getEmployee(EmployeeModel employeeModel) {
    return null;
}

public void removeEmployee( String username) {

}
public List<EmployeeModel> getAllEmployees() {
    return employeeDAO.findAll();
}

public void login(String username, String password) {
    sessionEmployee = employeeDAO.loginCheck(username, password);
}
public void logout() {
    this.sessionEmployee = null;
}
public EmployeeModel getSessionEmployee() {
    return this.sessionEmployee;
}


}
