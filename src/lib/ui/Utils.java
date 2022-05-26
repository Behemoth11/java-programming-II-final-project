package lib.ui;

import lib.Configuration;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    public static JTextField newTextField(){
        JTextField textField = new JTextField();

        textField.setMinimumSize(Configuration.textFieldDimension);
        textField.setPreferredSize(Configuration.textFieldDimension);

        return textField;
    };

    public static void showErrorDialog(String message, JPanel parentComponent) {
        JOptionPane.showMessageDialog(parentComponent, message,
                "Invalid Entry", JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccessDialog(String message, JPanel parentComponent) {
        JOptionPane.showMessageDialog(parentComponent, message,
                "Operation Successful", JOptionPane.INFORMATION_MESSAGE);
    }


    public static String toStringAmount(double amount){
      return String.valueOf(amount);
    };

}
