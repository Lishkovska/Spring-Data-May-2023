package javaDbAppsIntroduction_exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class increaseAgeStoredProcedure_09 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int id = Integer.parseInt(scanner.nextLine());

        CallableStatement callableStatement = connection.prepareCall("CALL usp_get_older(?) ");
        callableStatement.setInt(1, id);
        callableStatement.execute();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name,age FROM minions WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getInt("age"));
        }
         connection.close();
    }
}
