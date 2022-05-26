package lib.ui;

import lib.service.user.UserDAO;

import javax.swing.*;

public class BankingApplication {
    JFrame frame = new JFrame();
    JTabbedPane tabs = new JTabbedPane();

    {
        tabs.setBounds(40, 20, 500, 600);

        newTab(new CreateUserView(new UserDAO()));
        newTab(new SearchUserView(new UserDAO(), tabs));

        frame.add(tabs);

        frame.setSize(600, 680);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void newTab(View view){
        tabs.add(view.getPanel(), view.getTitle());
    }
}
