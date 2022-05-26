package lib.service.saving;

import lib.Utils;
import lib.service.user.User;

import java.sql.SQLException;

public class SavingAccountService {


    public static SavingAccount attachSavingAccount(User user, String accountId ) throws Exception {

        SavingAccount savingAccount;

        if ( user.getSavingAccount() != null ) throw new Exception("User already has an attached saving account");

        if ( accountId == null || accountId.length() < 1) {
             savingAccount = SavingAccountDAO.createSavingAccount(Utils.getRandomNumber(2,6));
        }else {
            savingAccount = SavingAccountDAO.getSavingAccount(accountId);
        }
        user.setSavingAccount(savingAccount);

        return savingAccount;
    }
}
