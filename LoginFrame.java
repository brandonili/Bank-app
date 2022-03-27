import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message, accnum_label;
    JTextField accnum_text;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;

    LoginFrame() {
        
        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();

        // Submit

        submit = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(5,3));//was 3,1

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        //panel.add(accnum_text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(300, 200);// was 300,100
        setVisible(true);

    }

    public static void main(String[] args) {
        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        
       // String accnum = accnum_text.getText();
        
        if  (userName.trim().equals("Brandon") && password.trim().equals("admin")) {
            message.setText(" Hello " + userName
                    + "Your account number is 1111111111111111");
        } 
        else if  (userName.trim().equals("Zach") && password.trim().equals("admin")) {
            message.setText(" Hello " + userName
                    + "");
        } else if  (userName.trim().equals("Henry") && password.trim().equals("admin")) {
            message.setText(" Hello " + userName
                    + "");
        } 
        else if  (userName.trim().equals("Joel") && password.trim().equals("admin")) {
            message.setText(" Hello " + userName
                    + "");
        } 
        else {
            message.setText(" Invalid user.. ");
        }

    }

}