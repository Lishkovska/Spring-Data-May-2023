package javaDbAppsIntroduction_exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class changeTownNameCasing_05 {
    public static final String SQL_UPDATE_TOWNS = "update towns set name = (name) WHERE country = ?;";
    public static final String SQL_SELECT_NAME_WHERE_COUNTRY = "SELECT name FROM towns WHERE country = ?";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String country = scanner.nextLine();

        PreparedStatement updateStatement = connection.prepareStatement(
                SQL_UPDATE_TOWNS);
        updateStatement.setString(1, country);
        int countOfUpdatedRows = updateStatement.executeUpdate();

        if (countOfUpdatedRows == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", countOfUpdatedRows);


            PreparedStatement selectStatement = connection.prepareStatement(
                    SQL_SELECT_NAME_WHERE_COUNTRY);

            selectStatement.setString(1, country);

            ResultSet resultSet = selectStatement.executeQuery();

            List<String> towns = new ArrayList<>();
            while (resultSet.next()) {
                String townName = resultSet.getString("name");
                towns.add(townName);
            }

            System.out.println(towns);

            connection.close();
        }
     }
}
