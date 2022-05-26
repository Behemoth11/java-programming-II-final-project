package lib.service.user;

import lib.DB;
import lib.service.saving.SavingAccountDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserDAO {

    static {

        try {
            String createTableStatement = "CREATE TABLE IF NOT EXISTS users ( " +
                    "id integer PRIMARY KEY," +
                    "firstname varchar(100) NOT NULL, " +
                    "lastname varchar(100) NOT NULL, " +
                    "address varchar(250) NOT NULL, " +
                    "phone_number varchar(20) NOT NULL, " +
                    "saving_account varchar(40)," +
                    "FOREIGN KEY (saving_account) REFERENCES saving_accounts (id) ) ;";

            DB.executeStatement(createTableStatement);

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Could not get initialize UserDAO");
        }
    }


    public static void createUser(User user) throws SQLException {
        String userCreationStatement = "INSERT INTO users ( firstname, lastname, address, phone_number ) VALUES ( ?, ?, ?, ?);";

        PreparedStatement statement = DB.getPreparedStatement(userCreationStatement);

        statement.setString(1, user.firstName);
        statement.setString(2, user.lastName);
        statement.setString(3, user.address);
        statement.setString(4, user.phoneNumber);

        statement.execute();
    }

    public static void updateUser(User user) throws SQLException {

        if (user.id == null) throw new RuntimeException("User id missing");

        String userUpdateStatement = "UPDATE users " +
                "SET firstname = ?, " +
                "lastname = ?, " +
                "address = ?, " +
                "phone_number = ?, " +
                "saving_account = ?  " +
                "WHERE id = ? ; ";

        PreparedStatement statement = DB.getPreparedStatement(userUpdateStatement);

        statement.setString(1, user.firstName);
        statement.setString(2, user.lastName);
        statement.setString(3, user.address);
        statement.setString(4, user.phoneNumber);
        statement.setString(5, user.getSavingAccount().id);
        statement.setString(6, user.id);
        statement.execute();
    }

    public static Vector<User> getUsers(int limit, int page) throws SQLException {

        String userUpdateStatement = "SELECT * FROM users LIMIT ? OFFSET ?;";

        PreparedStatement statement = DB.getPreparedStatement(userUpdateStatement);

        statement.setInt(1, limit);
        statement.setInt(2, page * limit);

        ResultSet rs = statement.executeQuery();

        return extractUsers(rs);
    };

    public static Vector<User> getUsers(String firstname, String lastname) throws SQLException {

        String userUpdateStatement = "SELECT * FROM users WHERE firstname=? AND lastname=?; ";

        PreparedStatement statement = DB.getPreparedStatement(userUpdateStatement);

        statement.setString(1, firstname);
        statement.setString(2, lastname);

        ResultSet rs = statement.executeQuery();

        return extractUsers(rs);
    };

    private static Vector<User> extractUsers(ResultSet rs) throws SQLException {
        Vector<User> users = new Vector<User>();


        while ( rs.next() ) {

            User user = new User()
                    .setId(rs.getString("id"))
                    .setAddress(rs.getString("address"))
                    .setLastName(rs.getString("lastname"))
                    .setFirstName(rs.getString("firstname"))
                    .setPhoneNumber(rs.getString("phone_number"))
                    .setSavingAccount(SavingAccountDAO.getSavingAccount(rs.getString("saving_account")));

            System.out.println(SavingAccountDAO.getSavingAccount(rs.getString("saving_account")));
            users.add( user );
        }

        return users;
    }
}
