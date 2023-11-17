    /*
    Madhur Jaripatke
    Roll No. 55
    TE A Computer
    RMDSSOE, Warje, Pune
    */

    /*
    Database Connectivity:
    Write a program to implement MySQL/Oracle database connectivity with any front end
    language to implement Database navigation operations (add, delete, edit etc.)
    */


    import java.sql.*;
    import java.util.Scanner;

    public class MySQLConnectivity {
        private static final String url = "jdbc:mysql://localhost:3306/DBMSL_Exp_8";
        private static final String user = "root";
        private static final String password = "";

        public static void main(String[] args) {
            try {
                Connection con = DriverManager.getConnection(url, user, password);
                Statement stmt = con.createStatement();
                Scanner scanner = new Scanner(System.in);
                String createQuery = "Create table if not exists data (ID int primary key, Name varchar(45), Class varchar(45), DateOfBirth date)";
                stmt.executeUpdate(createQuery);
                // Create
                System.out.println("Enter ID:");
                int id = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over
                System.out.println("Enter Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Class:");
                String className = scanner.nextLine();
                System.out.println("Enter DateOfBirth (YYYY-MM-DD):");
                String dob = scanner.nextLine();
                String insertQuery = "INSERT INTO data (ID, Name, Class, DateOfBirth) VALUES (" + id + ", '" + name + "', '" + className + "', '" + dob + "')";
                stmt.executeUpdate(insertQuery);

                // Read
                String selectQuery = "SELECT * FROM data";
                ResultSet rs = stmt.executeQuery(selectQuery);
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getDate(4));
                }

                // Update
                System.out.println("Enter ID to update:");
                int updateId = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over
                System.out.println("Enter new Name:");
                String newName = scanner.nextLine();
                System.out.println("Enter new Class:");
                String newClass = scanner.nextLine();
                System.out.println("Enter new DateOfBirth (YYYY-MM-DD):");
                String newDob = scanner.nextLine();
                String updateQuery = "UPDATE data SET Name = '" + newName + "', Class = '" + newClass + "', DateOfBirth = '" + newDob + "' WHERE ID = " + updateId;
                stmt.executeUpdate(updateQuery);

                // Delete
                System.out.println("Enter ID to delete:");
                int deleteId = scanner.nextInt();
                String deleteQuery = "DELETE FROM data WHERE ID = " + deleteId;
                stmt.executeUpdate(deleteQuery);

                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }