import Controller.EmployeeController;
import Controller.TicketController;
import DAO.EmployeeDAO;
import DAO.TicketDAO;
import Service.EmployeeService;
import Service.TicketService;
import io.javalin.Javalin;

public class Main {
    public static void main (String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        TicketDAO ticketDAO = new TicketDAO();

        EmployeeService employeeService = new EmployeeService(employeeDAO);
        TicketService ticketService = new TicketService(ticketDAO);

        EmployeeController employeeController = new EmployeeController(employeeService);
        TicketController ticketController = new TicketController(employeeService, ticketService);
        Javalin app = Javalin.create().start(8080);

        employeeController.employeesEndpoint(app);
        ticketController.ticketsEndpoint(app);

    }
}
