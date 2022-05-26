package lib.ui;

import lib.service.user.User;
import lib.service.user.UserDAO;

import javax.swing.*;

import java.awt.*;
import java.sql.SQLException;

import static lib.ui.Utils.getConstraints;

public class CreateUserView implements View {

    JPanel view = new JPanel();

    JTextField firstNameField = Utils.newTextField();
    JTextField lastNameField = Utils.newTextField();

    JTextField addressField = Utils.newTextField();
    JTextField phoneNumberField = Utils.newTextField();

    public CreateUserView(UserDAO userDAO) {
        view.setLayout(new GridBagLayout());
    }

    @Override
    public JPanel getPanel() {
        view.add(new JLabel("Firstname"), getConstraints(0, 0));
        view.add(firstNameField , getConstraints(1, 0));

        view.add(new JLabel("Lastname"), getConstraints(0, 1));
        view.add(lastNameField , getConstraints(1, 1));

        view.add(new JLabel("Address"), getConstraints(0, 2));
        view.add(addressField , getConstraints(1, 2));

        view.add(new JLabel("Phone Number"), getConstraints(0, 3));
        view.add(phoneNumberField , getConstraints(1, 3));

        view.add(getCreateUserButton(), getConstraints(1,10));

        return view;
    }


    private JButton getCreateUserButton(){
      JButton button = new JButton("Add Customer");
      button.addActionListener(e -> {
          try {
             User user = new User()
                  .setFirstName(firstNameField.getText())
                  .setLastName(lastNameField.getText())
                  .setAddress(addressField.getText())
                  .setPhoneNumber(phoneNumberField.getText());
              UserDAO.createUser(user);
              Utils.showSuccessDialog("User successfully created", view );
          } catch (Exception ex) {
              Utils.showErrorDialog(ex.getMessage(), view);
          }
      });
      return button;
    };

    @Override
    public String getTitle() {
        return "User View";
    }
}
