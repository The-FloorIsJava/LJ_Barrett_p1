package DAO;

import Model.EmployeeModel;
import Model.TicketModel;
import Util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    public TicketModel create(TicketModel newTicketModel) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = "insert into ticket (reimbursement_id, requester, description,  amount, serial_id, status) values (?,?,?,?,?, 'pending')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newTicketModel.getReimbursementID());
            preparedStatement.setString(2, newTicketModel.getRequester());
            preparedStatement.setString(3, newTicketModel.getDescription());
            preparedStatement.setDouble(4, newTicketModel.getAmount());
            preparedStatement.setInt(5, newTicketModel.getSerialID());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException("Employee was not added to the database");

            }
            return newTicketModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TicketModel> getAllEmployeeTickets(EmployeeModel employeeModel) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<TicketModel> ticketModels = new ArrayList<>();

            String sql = "select * from ticket where requester = ? order by ticket.serial_id";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeModel.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketModels.add(convertSqlInfoToTicket(resultSet));
            }
            return ticketModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TicketModel> getAllApprovedTickets(EmployeeModel employeeModel) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<TicketModel> ticketModels = new ArrayList<>();

            String sql = "select * from ticket where requester = ? and status = 'Approved'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeModel.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketModels.add(convertSqlInfoToTicket(resultSet));
            }
            return ticketModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<TicketModel> getAllPendingTickets(EmployeeModel employeeModel) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<TicketModel> ticketModels = new ArrayList<>();

            String sql = "select * from ticket where requester = ? and status = 'pending'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeModel.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketModels.add(convertSqlInfoToTicket(resultSet));
            }
            return ticketModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TicketModel> getAllDeniedTickets(EmployeeModel employeeModel) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<TicketModel> ticketModels = new ArrayList<>();

            String sql = "select * from ticket where requester = ? and status = 'Denied'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeModel.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketModels.add(convertSqlInfoToTicket(resultSet));
            }
            return ticketModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private TicketModel convertSqlInfoToTicket(ResultSet resultSet) throws SQLException {
        TicketModel ticketModel = new TicketModel();
        ticketModel.setAmount(resultSet.getInt("amount"));
        ticketModel.setSerialID(resultSet.getInt("serial_id"));
        ticketModel.setRequester(resultSet.getString("requester"));
        ticketModel.setDescription(resultSet.getString("description"));
        ticketModel.setReimbursementID(resultSet.getString("reimbursement_id"));
        ticketModel.setStatus(resultSet.getString("status"));

        return ticketModel;
    }

    public List<TicketModel> getAllManagerPendingTickets(EmployeeModel employeeModel) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<TicketModel> ticketModels = new ArrayList<>();

            String sql = "select * from ticket where status = 'pending'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketModels.add(convertSqlInfoToTicket(resultSet));
            }
            return ticketModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatingTickets(int ticketID, String ticketStatus) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update ticket set status = ? where serial_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticketStatus);
            preparedStatement.setInt(2, ticketID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}