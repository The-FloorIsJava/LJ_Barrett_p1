package Controller;
import Model.EmployeeModel;
import Service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

import javax.naming.Context;

public class EmployeeController {
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







}
