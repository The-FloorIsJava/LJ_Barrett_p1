package Controller;

import Service.EmployeeService;
import io.javalin.Javalin;

public class EmployeeController {
    EmployeeService employeeService;
    Javalin app;

    public EmployeeController(Javalin app) {
        employeeService = new EmployeeService(new EmployeeDAO());
        this.app = app;
    }

    public void employeeEndpoint() {
        app.get("hello", this::helloHandler);
    }


}
