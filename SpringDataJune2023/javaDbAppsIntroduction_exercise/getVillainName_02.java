package javaDbAppsIntroduction_exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class getVillainName_02 {
    public static final String SQL_SELECT_NAME_COUNT = "SELECT v.name , " +
            "count(distinct vm.minion_id) as count_minion " +
            "from minions_villains as vm" +
            " join villains as v " +
            "on vm.villain_id = v.id " +
            "group by v.name " +
            "having count_minion > 15 " +
            "order by count_minion desc;" ;

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
       props.setProperty("user", "root");
        props.setProperty("password", "****");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_NAME_COUNT);

        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            System.out.println(result.getString("name") + " " +
                    result.getString("count_minion"));
        }

        connection.close();
    }
}
