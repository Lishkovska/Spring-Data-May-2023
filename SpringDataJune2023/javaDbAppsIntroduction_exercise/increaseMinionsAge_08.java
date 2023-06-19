package javaDbAppsIntroduction_exercise;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class increaseMinionsAge_08 {
    public static final String SQL_QUERY_UPDATE = "UPDATE minions SET age=age+1, name=LOWER(name)  WHERE id=?;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int[] idArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_UPDATE);
        for (int id : idArr) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }

        preparedStatement = connection.prepareStatement("SELECT * FROM minions");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getInt("age"));
        }

        connection.close();

    }
}
