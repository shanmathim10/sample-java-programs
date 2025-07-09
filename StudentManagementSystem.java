import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame {
    private JTextField nameField, regNoField, degreeField, branchField;
    private DefaultTableModel tableModel;
    private JTable table;
    private ArrayList<Student> studentList = new ArrayList<>();

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        //  Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        formPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Register Number:"));
        regNoField = new JTextField();
        formPanel.add(regNoField);

        formPanel.add(new JLabel("Degree:"));
        degreeField = new JTextField();
        formPanel.add(degreeField);

        formPanel.add(new JLabel("Branch:"));
        branchField = new JTextField();
        formPanel.add(branchField);

        //  Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete Selected");
        JButton resetButton = new JButton("Reset");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(resetButton);

        formPanel.add(new JLabel()); // empty cell
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.NORTH);

        //  Table Panel
        tableModel = new DefaultTableModel(new String[]{"Name", "Register No", "Degree", "Branch"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        //  Button Actions
        addButton.addActionListener(e -> addStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        resetButton.addActionListener(e -> resetForm());
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String regNo = regNoField.getText().trim();
        String degree = degreeField.getText().trim();
        String branch = branchField.getText().trim();

        if (name.isEmpty() || regNo.isEmpty() || degree.isEmpty() || branch.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Student student = new Student(name, regNo, degree, branch);
        studentList.add(student);

        tableModel.addRow(new Object[]{student.getName(), student.getRegNo(), student.getDegree(), student.getBranch()});
        resetForm();
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            studentList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void resetForm() {
        nameField.setText("");
        regNoField.setText("");
        degreeField.setText("");
        branchField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystem().setVisible(true));
    }
}

//  Student Class
class Student {
    private String name, regNo, degree, branch;

    public Student(String name, String regNo, String degree, String branch) {
        this.name = name;
        this.regNo = regNo;
        this.degree = degree;
        this.branch = branch;
    }

    public String getName() { return name; }
    public String getRegNo() { return regNo; }
    public String getDegree() { return degree; }
    public String getBranch() { return branch; }
}
