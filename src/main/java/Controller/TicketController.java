package Controller;

import Model.EmployeeModel;
import Model.TicketModel;
import Service.EmployeeService;
import Service.TicketService;
import Util.ProcessTicketDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

public class TicketController {

    TicketService ticketService;

    private final EmployeeService employeeService;

    public TicketController(EmployeeService employeeService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }

    public void ticketsEndpoint(Javalin app) {
        app.post("submitEmployeeTicket", this::submitEmployeeTicketHandler);
        app.get("getEmployeeTicket", this::getTicketHandler);
        app.get("TicketPending", this::seePendingTicketsHandler);
        app.get("DeniedTickets", this::seeDeniedTicketsHandler);
        app.get("ApprovedTickets", this::seeApprovedTicketsHandler);
        app.post("handleTickets", this::handleTicketsHandler);
        app.get("allTickets", this::seeAllTicketsHandler);
    }

    private void seeAllTicketsHandler(Context context) {
        if (managerAccessible()) {
            List<TicketModel> managerPendingTickets = this.ticketService.getAllManagerPendingTickets();
            if (managerPendingTickets == null) {
                context.json("No pending tickets right now.");
            } else {
                context.json(managerPendingTickets);
            }
        }else {
            context.json("This needs manager credentials.");
        }
    }

    private void submitEmployeeTicketHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TicketModel ticketModel = mapper.readValue(context.body(), TicketModel.class);
        ticketModel = ticketService.addTicket(ticketModel);
        context.json(ticketModel);
    }

    private void getTicketHandler(Context context) {
        EmployeeModel employeeModel = this.employeeService.getSessionEmployee();
        if (employeeModel == null) {
            context.json("You have not logged in.");
            return;
        }
        List<TicketModel> ticketModels = this.ticketService.getAllEmployeeTickets(employeeModel);
        if (ticketModels == null) {
            context.json("There are no tickets");
            return;
        }
        context.json(ticketModels);
    }
    private void seePendingTicketsHandler(Context context) {
        EmployeeModel employeeModel = this.employeeService.getSessionEmployee();
        if (employeeModel == null) {
            context.json("You have not logged in.");
        }
        List<TicketModel> pendingTicketModels = this.ticketService.getAllPendingTickets(employeeModel);

        if (pendingTicketModels == null) {
            context.json("There are no pending tickets");
            return;
        }
        context.json(pendingTicketModels);
    }

    private void seeApprovedTicketsHandler(Context context) {
        EmployeeModel employeeModel = this.employeeService.getSessionEmployee();
        if (employeeModel == null) {
            context.json("You are not logged in.");
            return;
        }
        List<TicketModel> approvedTicketModels = this.ticketService.getAllApprovedTickets(employeeModel);

        if (approvedTicketModels == null) {
            context.json("There are no pending tickets.");
            return;
        }
        context.json(approvedTicketModels);
    }

    private void seeDeniedTicketsHandler(Context context) {
        EmployeeModel employeeModel = this.employeeService.getSessionEmployee();
        if (employeeModel == null) {
            context.json("You are not logged in.");
            return;
        }
        List<TicketModel> deniedTicketModels = this.ticketService.getAllDeniedTickets(employeeModel);

        if (deniedTicketModels == null) {
            context.json("there are no pending tickets.");
            return;
        }
        context.json(deniedTicketModels);
    }

    private void handleTicketsHandler(Context context) throws JsonProcessingException {
        if (!managerAccessible()) {
            context.json("You need to be a manager to access this.");
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            ProcessTicketDTO processTicketDTO = objectMapper.readValue(context.body(), ProcessTicketDTO.class);
            context.json(processTicketDTO);
            ticketService.updatingTickets(processTicketDTO.getSerialID(), processTicketDTO.getStatus());
        }


    }

    private boolean managerAccessible() {
        EmployeeModel employeeModel = this.employeeService.getSessionEmployee();
        if(employeeModel.getEmployeeRoleManager()) {
            return true;
        }else {
            return false;
        }
    }


}
