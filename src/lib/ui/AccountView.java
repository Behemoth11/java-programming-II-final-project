package lib.ui;
import java.awt.*;
import javax.swing.*;
import java.sql.SQLException;
import lib.service.saving.SavingAccount;
import lib.service.saving.SavingAccountDAO;

public class AccountView implements View {
    JPanel view = new JPanel(new GridBagLayout());

    SavingAccount savingAccount;

    JTextField balanceField = Utils.newTextField();
    JTextField accountNumberField = Utils.newTextField();
    JTextField interestRateField = Utils.newTextField();

    JTextField depositAmountField = Utils.newTextField();

    @Override
    public JPanel getPanel() {
        view.add(new Label("Balance"), Utils.getConstraints(0, 0));
        view.add(balanceField, Utils.getConstraints(1, 0));
        balanceField.setEditable(false);

        view.add(new Label("Account Number"), Utils.getConstraints(0, 1));
        view.add(accountNumberField, Utils.getConstraints(1, 1));
        accountNumberField.setEditable(false);

        view.add(new Label("Interest Rate"), Utils.getConstraints(0, 2));
        view.add(interestRateField, Utils.getConstraints(1, 2));
        interestRateField.setEditable(false);

        view.add(getDepositButton(), Utils.getConstraints(0, 3));
        view.add(depositAmountField, Utils.getConstraints(1, 3));

        return view;
    }

    private JButton getDepositButton() {
        JButton depositButton = new JButton("Deposit");

        depositButton.addActionListener(e -> {
            double amount = Double.parseDouble(depositAmountField.getText());
            try {
                savingAccount.deposit(amount);
                SavingAccountDAO.updateSavingAccount(savingAccount);
                render();
            } catch (SQLException | RuntimeException ex) {
                Utils.showErrorDialog(ex.getMessage(), view);
            }
        });


        return depositButton;
    }

    public void setAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
        render();
    }

    private void render() {
        if (savingAccount == null) {
            accountNumberField.setText("");
            balanceField.setText("");
            interestRateField.setText("");
            return;
        }
        accountNumberField.setText(String.valueOf(savingAccount.id));
        balanceField.setText(Utils.toStringAmount(savingAccount.balance));
        interestRateField.setText(String.valueOf(savingAccount.interestRate));
    }

    @Override
    public String getTitle() {
        return "Account";
    }
}
