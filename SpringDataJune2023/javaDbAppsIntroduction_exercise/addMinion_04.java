package javaDbAppsIntroduction_exercise;

import java.security.PublicKey;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class addMinion_04 {
    public static final String SQL_INSERT_MINION = "" +
            "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?)" ;

    public static final String SQL_SELECT_ID_FROM_MINIONS = "SELECT id FROM minions ORDER BY id DESC" ;
    public static final String SQL_INSERT_MINIONS_VILLAINS = "INSERT INTO minions_villains VALUES(?, ?)" ;
    public static final String SQL_SELECT_ID_FROM_VILLAINS = "SELECT id FROM villains WHERE name = ?";
    public static final String SQL_INSERT_INTO_VILLAINS = "INSERT INTO villains(name, evilness_factor) VALUES(?, ?)";
    public static final String SQL_SELECT_ID_TOWNS = "SELECT id FROM towns WHERE name = ?";
    public static final String SQL_INSERT_INTO_TOWNS = "INSERT INTO towns(name) VALUES (?);";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "****");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String[] minionInfo = scanner.nextLine().split("\\s+");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];
        String villainName = scanner.nextLine().split("\\s+")[1];

        int townId = getOrInsertTown(connection, minionInfo, minionTown);
        int villainId = getOrInsertVillain(connection, villainName);

        PreparedStatement insertMinion = connection.prepareStatement(SQL_INSERT_MINION);
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);
        insertMinion.executeUpdate();

        PreparedStatement getLastMinion = connection.prepareStatement(
                SQL_SELECT_ID_FROM_MINIONS);
        ResultSet lastMinionSet = getLastMinion.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt("id");


        PreparedStatement insertMinionsVillains = connection.prepareStatement(
                SQL_INSERT_MINIONS_VILLAINS);
        insertMinionsVillains.setInt(1, lastMinionId);
        insertMinionsVillains.setInt(2, villainId);
        insertMinionsVillains.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s\n", minionName, villainName);
        connection.close();
    }

    private static int getOrInsertVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement selectVillain = connection.prepareStatement(
                SQL_SELECT_ID_FROM_VILLAINS);
        selectVillain.setString(1, villainName);

        ResultSet villainSet = selectVillain.executeQuery();
        int villainId = 0;
        if (!villainSet.next()) {
            PreparedStatement insertVillain = connection.prepareStatement(
                    SQL_INSERT_INTO_VILLAINS);
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");
            insertVillain.executeUpdate();
            ResultSet newVillainSet = selectVillain.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.\n", villainName);
        } else {
            villainId = villainSet.getInt("id");

        }
        return villainId;

    }

    private static int getOrInsertTown(Connection connection, String[] minionInfo, String minionTown) throws SQLException {
        PreparedStatement selectTown = connection.prepareStatement(
                SQL_SELECT_ID_TOWNS);
        selectTown.setString(1, minionTown);
        ResultSet townSet = selectTown.executeQuery();
        int townId = 0;
        if (!townSet.next()) {
            PreparedStatement insertTown = connection.prepareStatement(
                    SQL_INSERT_INTO_TOWNS);
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();
            ResultSet newTownSet = selectTown.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = townSet.getInt("id");
        }
        return townId;

    }
}

