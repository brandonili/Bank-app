import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RegistrationForm {

public static void main(String[] args) {
// Call method which helps to generate the table
generateJTable();
}

/**
* This method helps to generate Table with buttons like Add, Update, Delete
*/
public static void generateJTable() {

// create JFrame and JTable
JFrame frame = new JFrame();
final JTable table = new JTable();

// create a table model and set a Column Identifiers to this model
Object[] columns = { "Account Number", "First Name", "Last Name", "Account type",
"Year Created" };
final DefaultTableModel model = new DefaultTableModel();
model.setColumnIdentifiers(columns);

// set the model to the table
table.setModel(model);

// Change A JTable Background Color, Font Size, Font Color, Row Height
table.setBackground(Color.CYAN.brighter());
table.setForeground(Color.black);
Font font = new Font("", 1, 18);
table.setFont(font);
table.setRowHeight(30);

// create JTextFields to hold the value
final JTextField textId = new JTextField();
final JTextField textFname = new JTextField();
final JTextField textLname = new JTextField();
final JTextField AccType = new JTextField();
final JTextField textAge = new JTextField();

// create JButtons to add the action
JButton btnAdd = new JButton("Add");
JButton btnDelete = new JButton("Delete");
JButton btnUpdate = new JButton("Update");

textId.setBounds(20, 220, 100, 25);
textFname.setBounds(20, 250, 100, 25);
textLname.setBounds(20, 280, 100, 25);
AccType.setBounds(20, 310, 100, 25);
textAge.setBounds(20, 340, 100, 25);

btnAdd.setBounds(150, 220, 100, 25);
btnUpdate.setBounds(150, 265, 100, 25);
btnDelete.setBounds(150, 310, 100, 25);

// create JScrollPane
JScrollPane pane = new JScrollPane(table);
pane.setBounds(0, 0, 880, 200);

frame.setLayout(null);

frame.add(pane);

// add JTextFields to the jframe
frame.add(textId);
frame.add(textFname);
frame.add(textLname);
frame.add(AccType);
frame.add(textAge);

// add JButtons to the jframe
frame.add(btnAdd);
frame.add(btnDelete);
frame.add(btnUpdate);

// create an array of objects to set the row data
final Object[] row = new Object[5];
// button add row - Clicked on Add Button
btnAdd.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {

row[0] = textId.getText();
row[1] = textFname.getText();
row[2] = textLname.getText();
row[3] = AccType.getText();
row[4] = textAge.getText();

// add row to the model
model.addRow(row);
}
});

// button remove row - Clicked on Delete Button
btnDelete.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {

// i = the index of the selected row
int i = table.getSelectedRow();
if (i >= 0) {
// remove a row from jtable
model.removeRow(i);
} else {
System.out
.println("There were issue while Deleting the Row(s).");
}
}
});

// get selected row data From table to textfields
table.addMouseListener(new MouseAdapter() {

@Override
public void mouseClicked(MouseEvent e) {

// i = the index of the selected row
int i = table.getSelectedRow();

textId.setText(model.getValueAt(i, 0).toString());
textFname.setText(model.getValueAt(i, 1).toString());
textLname.setText(model.getValueAt(i, 2).toString());
AccType.setText(model.getValueAt(i, 3).toString());
textAge.setText(model.getValueAt(i, 4).toString());
}
});

// button update row - Clicked on Update Button
btnUpdate.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {

// i = the index of the selected row
int i = table.getSelectedRow();

if (i >= 0) {
model.setValueAt(textId.getText(), i, 0);
model.setValueAt(textFname.getText(), i, 1);
model.setValueAt(textLname.getText(), i, 2);
model.setValueAt(AccType.getText(), i, 3);
model.setValueAt(textAge.getText(), i, 4);
} else {
System.out.println("Update Error");
}
}
});

frame.setSize(900, 400);
frame.setLocationRelativeTo(null);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);

}
}