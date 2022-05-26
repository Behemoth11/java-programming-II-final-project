package lib.service.user;

import lib.service.saving.SavingAccount;

import java.sql.SQLException;

public class User {

    public String firstName;
    public String lastName;
    public String address;
    public String phoneNumber;
    protected String id;
    private SavingAccount savingAccount;

    protected User setId(String id) {
        this.id = id;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public SavingAccount getSavingAccount() {
        return this.savingAccount;
    }

    public User setSavingAccount(SavingAccount savingAccount) throws SQLException {
        if (this.savingAccount == savingAccount) return this;

        this.savingAccount = savingAccount;
        UserDAO.updateUser(this);
        return this;
    }
}
