package lib.ui;

import lib.service.user.User;

import javax.swing.*;

import java.awt.*;

import static lib.ui.Utils.getConstraints;

public class UserDataComponent extends JPanel {

    private User user;

    JTextField firstNameField = Utils.newTextField();
    JTextField lastNameField = Utils.newTextField();

    JTextField addressField = Utils.newTextField();
    JTextField phoneNumberField = Utils.newTextField();

    public UserDataComponent() {
        setLayout(new GridBagLayout());

        add(new JLabel("Firstname"), getConstraints(0, 0));
        add(firstNameField, getConstraints(1, 0));
        firstNameField.setEditable(false);

        add(new JLabel("Lastname"), getConstraints(0, 1));
        add(lastNameField, getConstraints(1, 1));
        lastNameField.setEditable(false);

        add(new JLabel("Address"), getConstraints(0, 2));
        add(addressField, getConstraints(1, 2));
        addressField.setEditable(false);

        add(new JLabel("Phone Number"), getConstraints(0, 3));
        add(phoneNumberField, getConstraints(1, 3));
        phoneNumberField.setEditable(false);
    }

    public void setUser(User user){
        this.user = user;
        render();
    }

    private void render(){
        firstNameField.setText(user.firstName);
        lastNameField.setText(user.lastName);
        addressField.setText(user.address);
        phoneNumberField.setText(user.phoneNumber);
    }
}
