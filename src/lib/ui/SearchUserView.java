package lib.ui;

import lib.service.saving.SavingAccount;
import lib.service.saving.SavingAccountService;
import lib.service.user.User;
import lib.service.user.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

import static lib.ui.Utils.getConstraints;
import static lib.ui.Utils.showErrorDialog;

public class SearchUserView implements View {

    JPanel view = new JPanel();
    JTextField firstNameField = Utils.newTextField();
    JTextField lastNameField = Utils.newTextField();
    JTextField optionalAccountIdField = Utils.newTextField();

    JTextField currentMonthField = Utils.newTextField();
    JTextField interestField = Utils.newTextField();

    UserDataComponent userDataComponent = new UserDataComponent();
    Vector<User> users = new Vector<User>();
    UserDAO userDAO;
    JTabbedPane tabs;

    AccountView accountView = new AccountView();

    int userIdx = 0;

    public SearchUserView(UserDAO userDAO, JTabbedPane tabs) {
        this.userDAO = userDAO;
        this.tabs = tabs;
    }


    @Override
    public JPanel getPanel() {
        view.setLayout(new BoxLayout(view, BoxLayout.PAGE_AXIS));

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel searchControlWrapper = new JPanel(new GridBagLayout());
        JPanel interestCalculatorWrapper = new JPanel(new GridBagLayout());


        wrapper.add(new JLabel("Firstname"), getConstraints(0, 0));
        wrapper.add(firstNameField, getConstraints(1, 0));

        wrapper.add(new JLabel("Lastname"), getConstraints(0, 1));
        wrapper.add(lastNameField, getConstraints(1, 1));

        wrapper.add(getSearchCustomerButton(), getConstraints(1, 2));


        searchControlWrapper.add(getPreviousButton(), getConstraints(0, 0));
        searchControlWrapper.add(getNextButton(), getConstraints(1, 0));

        searchControlWrapper.add(getAccountCreationButton(), getConstraints(0, 1));
        searchControlWrapper.add(optionalAccountIdField, getConstraints(1, 1));


        interestCalculatorWrapper.add(new Label("current Month"), getConstraints(0, 0));
        interestCalculatorWrapper.add(currentMonthField, getConstraints(1, 0));

        interestCalculatorWrapper.add(getInterestCalculatorButton(), getConstraints(0, 1));
        interestCalculatorWrapper.add(interestField, getConstraints(1, 1));
        interestField.setEditable(false);

        view.add(wrapper);
        view.add(this.userDataComponent);
        view.add(searchControlWrapper);
        view.add(accountView.getPanel());
        view.add(interestCalculatorWrapper);

        return view;
    }

    private JButton getInterestCalculatorButton() {
        JButton getInterestButton = new JButton("Get Interest");

        getInterestButton.addActionListener(e -> {
            SavingAccount savingAccount = users.get(userIdx).getSavingAccount();
            if (savingAccount == null) return;

            double interest = Math.pow(savingAccount.balance * (1 + savingAccount.interestRate / 12), 12) - savingAccount.balance;

            interestField.setText(Utils.toStringAmount(interest));
            render();
        });

        return getInterestButton;
    }

    private JButton getNextButton() {
        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> {
            if (userIdx >= users.size() - 1) {
                Utils.showErrorDialog("No more users with given credentials", view);
                return;
            }
            userIdx++;
            render();
        });

        return nextButton;
    }


    private JButton getPreviousButton() {
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(e -> {
            if (userIdx <= 0) {
                showErrorDialog("No previous user with given credentials", view);
                return;
            }
            userIdx--;
            render();
        });

        return previousButton;
    }

    @Override
    public String getTitle() {
        return "Search User";
    }

    private JButton getAccountCreationButton() {
        JButton button = new JButton("New Account");

        button.addActionListener(e -> {
            String optionalAccountId = optionalAccountIdField.getText();
            try {
                SavingAccountService.attachSavingAccount(users.get(userIdx), optionalAccountId);
                render();
            } catch (Exception ex) {
                Utils.showErrorDialog(ex.getMessage(), view);
            }
        });

        return button;
    }

    public JButton getSearchCustomerButton() {
        JButton button = new JButton("Search Customer");

        button.addActionListener(e -> {
            Vector<User> customers;

            try {
                customers = UserDAO.getUsers(firstNameField.getText(), lastNameField.getText());
            } catch (SQLException ex) {
                System.out.println(ex);
                Utils.showErrorDialog("Could not retrieve user list", view);
                return;
            }

            if (customers.size() < 1) {
                Utils.showSuccessDialog("No user with given user name found", view);
                return;
            }

            this.users = customers;
            this.userIdx = 0;
            render();
        });

        return button;
    }

    private void render() {
        User currentUser = users.get(userIdx);

        this.userDataComponent.setUser(currentUser);
        this.accountView.setAccount(currentUser.getSavingAccount());
    }

}
