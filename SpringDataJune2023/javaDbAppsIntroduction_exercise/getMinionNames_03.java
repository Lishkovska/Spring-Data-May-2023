package javaDbAppsIntroduction_exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class getMinionNames_03 {
    public static final String SQL_SELECT_NAME_BY_ID = "select v.name  " +
            "from villains as v " +
            "where v.id = ? ;" ;

    public static final String SQL_SELECT_NAME_AGE_BY_ID = "select m.name , m.age " +
            "from minions as m " +
            "join minions_villains as vm " +
            "on vm.minion_id = m.id " +
            "where vm.villain_id = ? ;" ;


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "****");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int villain_id = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainPrepStatement = connection.prepareStatement(SQL_SELECT_NAME_BY_ID);


        villainPrepStatement.setInt(1, villain_id);

        ResultSet resultVillain = villainPrepStatement.executeQuery();

        if(!resultVillain.next()){
            System.out.printf("No villain with ID %d exists in the database.", villain_id);
            return;
        }

        String villainName = resultVillain.getString("v.name");
        System.out.printf("Villain: %s%n", villainName);

        PreparedStatement minionPrepStatement = connection.prepareStatement(SQL_SELECT_NAME_AGE_BY_ID);

        minionPrepStatement.setInt(1, villain_id);
        ResultSet resultMinion = minionPrepStatement.executeQuery();

        for (int i = 1; resultMinion.next(); i++) {
            System.out.printf("%d. %s %d%n", i,
                    resultMinion.getString("m.name"),
                    resultMinion.getInt("m.age"));
        }

        connection.close();
    }
}
