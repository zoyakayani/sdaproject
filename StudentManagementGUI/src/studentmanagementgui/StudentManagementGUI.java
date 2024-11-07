/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentmanagementgui;

/**
 *
 * @author CUI
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagementGUI extends JFrame {
    private ArrayList<Student> students;
    private DefaultListModel<String> studentListModel;

    public StudentManagementGUI() {
        students = new ArrayList<>();
        studentListModel = new DefaultListModel<>();
        
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set background color of the main frame
        getContentPane().setBackground(new Color(220, 220, 255)); // Light blue background
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Add student panel
        JPanel addPanel = createAddStudentPanel();
        tabbedPane.add("Add Student", addPanel);

        // View student panel
        JPanel viewPanel = createViewStudentPanel();
        tabbedPane.add("View Students", viewPanel);

        // Update student panel
        JPanel updatePanel = createUpdateStudentPanel();
        tabbedPane.add("Update Student", updatePanel);

        // Delete student panel
        JPanel deletePanel = createDeleteStudentPanel();
        tabbedPane.add("Delete Student", deletePanel);

        add(tabbedPane);
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 204)); // Light yellow background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(10);
        JButton addButton = createStyledButton("Add Student");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(ageLabel, gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(addButton, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                addStudent(new Student(id, name, age));
                JOptionPane.showMessageDialog(null, "Student added successfully!");
                idField.setText("");
                nameField.setText("");
                ageField.setText("");
            }
        });

        return panel;
    }

    private JPanel createViewStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(204, 255, 255)); // Light cyan background

        JList<String> studentList = new JList<>(studentListModel);
        panel.add(new JScrollPane(studentList), BorderLayout.CENTER);

        JButton refreshButton = createStyledButton("Refresh List");
        panel.add(refreshButton, BorderLayout.SOUTH);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshStudentList();
            }
        });

        return panel;
    }

    private JPanel createUpdateStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 204, 204)); // Light red background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel idLabel = new JLabel("Enter ID to Update:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("New Name:");
        JTextField nameField = new JTextField(10);
        JLabel ageLabel = new JLabel("New Age:");
        JTextField ageField = new JTextField(10);
        JButton updateButton = createStyledButton("Update Student");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(ageLabel, gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                if (updateStudent(id, name, age)) {
                    JOptionPane.showMessageDialog(null, "Student updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Student ID not found.");
                }
                idField.setText("");
                nameField.setText("");
                ageField.setText("");
            }
        });

        return panel;
    }

    private JPanel createDeleteStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 223, 186)); // Light peach background
        
        JLabel idLabel = new JLabel("Enter ID to Delete:");
        JTextField idField = new JTextField(10);
        JButton deleteButton = createStyledButton("Delete Student");

        JPanel inputPanel = new JPanel();
        inputPanel.add(idLabel);
        inputPanel.add(idField);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(deleteButton, BorderLayout.SOUTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                if (deleteStudent(id)) {
                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Student ID not found.");
                }
                idField.setText("");
            }
        });

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 150, 255)); // Blue button
        button.setPreferredSize(new Dimension(200, 40));
        button.setFocusPainted(false);

        // Add hover effect (optional)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 180, 255)); // Lighter blue
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 150, 255)); // Default blue
            }
        });

        return button;
    }

    private void addStudent(Student student) {
        students.add(student);
        studentListModel.addElement(student.getId() + ": " + student.getName() + ", Age: " + student.getAge());
    }

    private boolean updateStudent(int id, String newName, int newAge) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(newName);
                student.setAge(newAge);
                refreshStudentList();
                return true;
            }
        }
        return false;
    }

    private boolean deleteStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                refreshStudentList();
                return true;
            }
        }
        return false;
    }

    private void refreshStudentList() {
        studentListModel.clear();
        for (Student student : students) {
            studentListModel.addElement(student.getId() + ": " + student.getName() + ", Age: " + student.getAge());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementGUI gui = new StudentManagementGUI();
            gui.setVisible(true);
        });
    }
}

