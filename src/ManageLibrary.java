import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ManageLibrary extends JFrame implements ActionListener {

    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
    JLabel l2, l3, l4;

    public ManageLibrary() {
        this.setSize(815, 400);
        this.setTitle("Library Management System");
        this.setLayout(null);

        JLabel l1 = new JLabel();
        l1.setForeground(new Color(0xed1a1a));
        l1.setText("Welcome to the Library Management System");
        l1.setBounds(200, 20, 800, 40);
        l1.setFont(new Font("Georgia", Font.BOLD, 18));
        this.add(l1);

        JLabel lid = new JLabel();
        lid.setText("Student Id");
        lid.setForeground(new Color(3, 69, 252));
        lid.setBounds(60, 70, 180, 20);
        this.add(lid);

        tf1 = new JTextField();
        tf1.setBounds(135, 70, 180, 20);
        this.add(tf1);

        JLabel lname = new JLabel();
        lname.setText("Student Name");
        lname.setForeground(new Color(3, 69, 252));
        lname.setBounds(365, 70, 300, 20);
        this.add(lname);

        tf2 = new JTextField();
        tf2.setBounds(460, 70, 180, 20);
        this.add(tf2);

        JLabel ldepartment = new JLabel();
        ldepartment.setText("Student Department");
        ldepartment.setForeground(new Color(3, 69, 252));
        ldepartment.setBounds(60, 100, 300, 20);
        this.add(ldepartment);

        tf3 = new JTextField();
        tf3.setBounds(185, 100, 150, 20);
        this.add(tf3);

        JLabel lyear = new JLabel();
        lyear.setText("Student Year");
        lyear.setForeground(new Color(3, 69, 252));
        lyear.setBounds(365, 100, 300, 20);
        this.add(lyear);

        tf4 = new JTextField();
        tf4.setBounds(460, 100, 180, 20);
        this.add(tf4);

        JLabel lbookname = new JLabel();
        lbookname.setText("Name of book");
        lbookname.setBounds(60,130,300,20);
        lbookname.setForeground(new Color(3, 69, 252));
        this.add(lbookname);

        tf5 = new JTextField();
        tf5.setBounds(145, 130, 180, 20);
        this.add(tf5);

        JLabel lauthor = new JLabel();
        lauthor.setText("Author");
        lauthor.setBounds(365,130,300,20);
        lauthor.setForeground(new Color(3, 69, 252));
        this.add(lauthor);

        tf6 = new JTextField();
        tf6.setBounds(415, 130, 180, 20);
        this.add(tf6);

        JLabel lissue = new JLabel();
        lissue.setText("Issue Date");
        lissue.setBounds(60,160,300,20);
        lissue.setForeground(new Color(3, 69, 252));
        this.add(lissue);

        tf7 = new JTextField();
        tf7.setBounds(125, 160, 180, 20);
        this.add(tf7);

        JLabel lreturn = new JLabel();
        lreturn.setText("Return Date");
        lreturn.setBounds(365,160,300,20);
        lreturn.setForeground(new Color(3, 69, 252));
        this.add(lreturn);

        tf8 = new JTextField();
        tf8.setBounds(445, 160, 180, 20);
        this.add(tf8);

        JButton b1 = new JButton();
        b1.setText("Add Record");
        b1.setBackground(new Color(26, 188, 237));
        b1.setForeground(new Color(255,255,255));
        b1.setBounds(260, 200, 180, 40);
        this.add(b1);
        b1.addActionListener(this);

        JButton b2 = new JButton();
        b2.setText("Delete Record");
        b2.setBackground(new Color(26, 188, 237));
        b2.setForeground(new Color(255,255,255));

        b2.setBounds(660, 0, 140, 40);
        this.add(b2);
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String dbURL = "jdbc:mysql://localhost:3306/librarydb";
                String username = "root";
                String password = "root";
                Connection conn;

                try {

                    conn = DriverManager.getConnection(dbURL, username, password);

                    if (conn != null) {
                        System.out.println("Connected");
                    }

                    String id = tf1.getText();
                    String sql = "DELETE FROM student WHERE StudentId = ? ";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, id);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {

                        l3.setText("Record deleted successfully");
                        l3.setBounds(270, 250, 300, 40);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton b3 = new JButton();
        b3.setText("Update Record");
        b3.setBackground(new Color(26, 188, 237));
        b3.setForeground(new Color(255,255,255));

        b3.setBounds(660, 60, 140, 40);
        this.add(b3);
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String dbURL = "jdbc:mysql://localhost:3306/librarydb";
                String username = "root";
                String password = "root";
                Connection conn;

                try {

                    conn = DriverManager.getConnection(dbURL, username, password);

                    if (conn != null) {
                        System.out.println("Connected");
                    }

                    String uid = tf1.getText();
                    String unam = tf2.getText();
                    String udept = tf3.getText();
                    String uyr = tf4.getText();
                    String ubookname = tf5.getText();
                    String uauth = tf6.getText();
                    String uissdate = tf7.getText();
                    String uretdate = tf8.getText();


                    String sql2 = "UPDATE student SET StudentName=?, StudentDepartment=?, StudentYear=?, Nameofbook=?, Author=? WHERE StudentId=?";

                    PreparedStatement statement1 = conn.prepareStatement(sql2);

                    statement1.setString(1, unam);
                    statement1.setString(2, udept);
                    statement1.setString(3, uyr);
                    statement1.setString(4, ubookname);
                    statement1.setString(5, uauth);
                    statement1.setString(6, uid);


                    int rowsUpdated = statement1.executeUpdate();
                    if (rowsUpdated > 0) {
                        l3.setText("Record updated successfully");
                        l3.setBounds(270, 270, 300, 40);
                    }



                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });


        l2 = new JLabel();
        l2.setBounds(140, 250, 180, 40);
        this.add(l2);

        l3 = new JLabel();
        l3.setBounds(140, 250, 180, 40);
        this.add(l3);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dbURL = "jdbc:mysql://localhost:3306/librarydb";
        String username = "root";
        String password = "root";
        Connection conn;

        try {

            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }

            String id = tf1.getText();
            String nam = tf2.getText();
            String dept = tf3.getText();
            String yr = tf4.getText();
            String bookname = tf5.getText();
            String auth = tf6.getText();
            String issdate = tf7.getText();
            String retdate = tf8.getText();

            String sql = "INSERT INTO student (StudentId, StudentName, StudentDepartment, StudentYear, Nameofbook, Author, Issuedate, Returndate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,id);
            statement.setString(2,nam);
            statement.setString(3,dept);
            statement.setString(4,yr);
            statement.setString(5,bookname);
            statement.setString(6,auth);
            statement.setString(7,issdate);
            statement.setString(8,retdate);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted>0) {

                l2.setText("Record added successfully");
                l2.setBounds(270, 230, 300, 40);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


    public static void main(String[] args) {

        ManageLibrary ml = new ManageLibrary();
    }
}

























