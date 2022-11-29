package DAO;

import Model.EmployeeModel;
import Util.ConnectionFactory;
import Util.Crudable;
import Util.EmployeeException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements Crudable<EmployeeModel> {


    @Override
    public EmployeeModel create(EmployeeModel newEmployeeModel) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into employee (username, password, employee_role_manager) values (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newEmployeeModel.getUsername());
            preparedStatement.setString(2, newEmployeeModel.getPassword());
            preparedStatement.setString(3, newEmployeeModel.getEmployeeRoleManager());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException("Employee was not added to the database");
            }
            return newEmployeeModel;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmployeeModel> findAll() {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<EmployeeModel> employeeModels = new ArrayList<>();
            String sql = "select * from employee";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                employeeModels.add(convertSqlInfoToEmployee(resultSet));
            }
            return employeeModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public EmployeeModel findByUsername(String username) {
        return null;
    }

    @Override
    public boolean update(EmployeeModel updatedEmployeeModel) {
        return false;
    }

    @Override
    public boolean delete(String username) {
        return false;
    }

    public EmployeeModel loginCheck(String username, String password) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from employee where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EmployeeException("Information entered for " + username + "was wrong.");

            }
            return convertSqlInfoToEmployee(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private EmployeeModel convertSqlInfoToEmployee(ResultSet resultSet) throws SQLException {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setUsername(resultSet.getString("username"));
        employeeModel.setPassword(resultSet.getString("password"));
        employeeModel.setEmployeeRoleManager(resultSet.getBoolean("employee_role_manager"));
        return employeeModel;
    }


}



