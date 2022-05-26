package lib.service.saving;

import lib.DB;
import lib.service.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SavingAccountDAO {

    static {

        try {
            String createTableStatement = "CREATE TABLE IF NOT EXISTS saving_accounts ( " + "id varchar(40) PRIMARY KEY," + "balance REAL NOT NULL, " + "interest_rate REAL NOT NULL " + ") ;";

            DB.executeStatement(createTableStatement);

        } catch (SQLException e) {
            throw new RuntimeException("Could not get initialize UserDAO");
        }
    }

    public static SavingAccount createSavingAccount(double interest_rate) throws SQLException {
        String savingAccountCreationStatement = "INSERT INTO saving_accounts ( id, balance, interest_rate) VALUES ( ?, ?, ? ); ";

        PreparedStatement statement = DB.getPreparedStatement(savingAccountCreationStatement);

        SavingAccount savingAccount = new SavingAccount().setId(String.valueOf(UUID.randomUUID())).setBalance(0).setInterestRate(interest_rate);

        statement.setString(1, savingAccount.id);
        statement.setDouble(2, savingAccount.balance);
        statement.setDouble(3, savingAccount.interestRate);

        statement.execute();

        return savingAccount;
    }

    public static void updateSavingAccount(SavingAccount account) throws SQLException {
        String savingAccountUpdateStatement = "UPDATE saving_accounts " +
                "SET balance = ?, " +
                "interest_rate = ? " +
                "WHERE id = ? ; ";

        PreparedStatement statement = DB.getPreparedStatement(savingAccountUpdateStatement);

        System.out.println(account.balance);

        statement.setDouble(1, account.balance);
        statement.setDouble(2, account.interestRate);
        statement.setString(3, account.id );

        statement.execute();
    }

    public static SavingAccount getSavingAccount(String account_id) throws SQLException {
        String savingAccountSelectStatement = "SELECT * FROM saving_accounts WHERE id=?;";

        PreparedStatement statement = DB.getPreparedStatement(savingAccountSelectStatement);

        statement.setString(1, account_id);

        ResultSet rs = statement.executeQuery();

        SavingAccount savingAccount = null;

        while (rs.next()) {
            savingAccount = new SavingAccount()
                    .setId(account_id)
                    .setBalance(rs.getDouble("balance"))
                    .setInterestRate(rs.getDouble("interest_rate"));
        }

        return savingAccount;
    }

}
