package Service;

import DAO.TicketDAO;
import Model.EmployeeModel;
import Model.TicketModel;
import java.util.List;

import java.awt.*;

public class TicketService {
    private final TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public List<TicketModel> getAllEmployeeTickets(EmployeeModel employeeModel) {
        return ticketDAO.getAllEmployeeTickets(employeeModel);
    }

    public TicketModel addTicket(TicketModel ticketModel) {
        return ticketDAO.create(ticketModel);
    }

    public List<TicketModel> getAllPendingTickets(EmployeeModel employeeModel) {
        return ticketDAO.getAllPendingTickets(employeeModel);
    }
    public List<TicketModel> getAllApprovedTickets(EmployeeModel employeeModel) {
        return ticketDAO.getAllApprovedTickets(employeeModel);
    }

    public List<TicketModel> getAllDeniedTickets(EmployeeModel employeeModel) {
        return ticketDAO.getAllDeniedTickets(employeeModel);
    }

    public List<TicketModel> getAllManagerPendingTickets(EmployeeModel employeeModel) {
        return this.ticketDAO.getAllManagerPendingTickets(employeeModel);
    }

    public void updatingTickets(int ticketID, String ticketStatus){
        ticketDAO.updatingTickets(ticketID, ticketStatus);
    }

}
