package javaDbAppsIntroduction_exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class printAllMinionsName_07 {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "Maria201118");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM minions");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> minionNamesList = new ArrayList<>();
        while (resultSet.next()){
            minionNamesList.add(resultSet.getString("name"));
        }

        int startIndex = 0;
        int endIndex = minionNamesList.size() - 1;

        for (int i = 0; i < minionNamesList.size(); i++) {
           if(i % 2 == 0){
               System.out.println(minionNamesList.get(startIndex++));
           } else {
               System.out.println(minionNamesList.get(endIndex--));
           }
        }

        connection.close();
    }
}
