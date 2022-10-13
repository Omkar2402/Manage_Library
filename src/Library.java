import java.sql.*;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/librarydb";
        String username = "root";
        String password = "root";
        Connection conn;
        int ch=0;
        int op=0;
        Scanner sc=new Scanner(System.in);
        try {

            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }

            do {
                switch(op) {

                    case 1:
                        System.out.println("Enter records for updation");
                        System.out.print("Enter student ID whose records are to be updated: ");
                        int uid=sc.nextInt();
                        System.out.print("Enter updated student name: ");
                        String uname=sc.next();
                        System.out.print("Enter updated student department: ");
                        String udept=sc.next();
                        System.out.print("Enter updated student year: ");
                        String uyr = sc.next();
                        System.out.print("Enter updated name of book: ");
                        String ubook = sc.next();
                        System.out.print("Enter updated name of author: ");
                        String uauth = sc.next();



                        String sql2 = "UPDATE student SET StudentName=?, StudentDepartment=?, StudentYear=?, Nameofbook=?, Author=? WHERE StudentId=?";

                        PreparedStatement statement1 = conn.prepareStatement(sql2);

                        statement1.setString(1, uname);
                        statement1.setString(2, udept);
                        statement1.setString(3, uyr);
                        statement1.setString(4, ubook);
                        statement1.setString(5, uauth);
                        statement1.setInt(6, uid);


                        int rowsUpdated = statement1.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("A student was updated successfully!");
                        }
                        break;
                    case 2:
                        System.out.println("Enter records for Deletion");
                        System.out.println("Enter student ID whose records are to be deleted");
                        int did=sc.nextInt();
                        String sql3 = "DELETE FROM student WHERE StudentId=?";

                        PreparedStatement statement3 = conn.prepareStatement(sql3);

                        statement3.setInt(1,did);
                        int rowsDeleted = statement3.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("A user was deleted successfully!");
                        }
                        break;

                    case 3:
                        String sql1 = "SELECT * FROM student";

                        Statement stmt = conn.createStatement();
                        ResultSet result = stmt.executeQuery(sql1);

                        int count = 0;

                        while (result.next()){
                            int sid = result.getInt(1);
                            String sname = result.getString(2);
                            String sdept = result.getString(3);
                            String syr = result.getString(4);
                            String sbook = result.getString(5);
                            String sauth = result.getString(6);

                            String output = "STUDENT #%d: -%s - %s - %s - %s - %s";
                            System.out.println(String.format(output, ++count,sid, sname,sdept, syr,sbook, sauth));


                        }
                        break;
                }

                System.out.println("Enter your choice: 1.Update  2.Delete  3. Display\n");
                op=sc.nextInt();
            }while(op!=0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
