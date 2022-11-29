package Controller;
import Model.EmployeeModel;
import Service.EmployeeService;
import Util.LoginCreds;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public void employeesEndpoint(Javalin app) {
        app.post("register", this::postEmployeeRegisterHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
        app.get("allEmployees", this::getAllEmployeesHandler);
    }

    private void postEmployeeRegisterHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        EmployeeModel employeeModel = mapper.readValue(context.body(), EmployeeModel.class);
        employeeModel = employeeService.addEmployee(employeeModel);
        if (employeeModel == null) {
            context.json("ERROR! This email is registered already. ");
        } else {
            context.json(employeeModel);
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        employeeService.login(loginCreds.getUsername(),loginCreds.getPassword());
        context.json("You logged in Successfully! ");
    }

    private void logoutHandler(Context context) {
        String username = employeeService.getSessionEmployee().getUsername();
        employeeService.logout();
        context.json(username + " has successfully logged out");
    }
    private void getAllEmployeesHandler(Context context) {
        List<EmployeeModel> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
    }


    }


    /*
    EmployeeService employeeService;
    Javalin app;

    public EmployeeController(Javalin app) {
        employeeService = new EmployeeService(new EmployeeDAO());
        this.app = app;
    }

    public void employeeEndpoint() {
        app.get("hello", this::helloHandler);
        app.post("employee", this::postEmployeeHandler);
        app.get("employee", this::getAllEmployeesHandler);
        app.get("employee/{username}", this::getSpecificEmployeeHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);

    }

   public void helloHandler (Context ctx) {
        ctx.result("Hello World");
   }

   private void getSpecificEmployeeHandler(Context context) {
        String username = context.pathParam("username");
        Employee employee = employeeService.getEmployee(username);
        context.json(employee);
   }
   private void getAllEmployeesHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
   }

   private void postEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(), Employee.class);
        employeeService.addEmployee(employee);
        context.json(employee);
   }

   private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        employeeService.login(loginCreds.getEmployeeUsername(), loginCreds.getPassword());
        context.json("Logged in successfully");
   }

   private void logoutHandler(Context context) {
        String employeeUsername = employeeService.getSessionEmployee().getEmployeeUsername();
        employeeService.logout();
        context.json("Logged out successfully");
   }

*/






