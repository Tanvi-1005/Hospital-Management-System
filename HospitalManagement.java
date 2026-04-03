import java.sql.*;
import java.util.Scanner;

public class HospitalManagement {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital", "root", "password");

            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Patient ID: ");
                int pid = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Age: ");
                int age = sc.nextInt();

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO patient VALUES(?,?,?)");
                ps.setInt(1, pid);
                ps.setString(2, name);
                ps.setInt(3, age);

                ps.executeUpdate();
                System.out.println("Patient Added Successfully");
            } else if (choice == 2) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM patient");

                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " "
                            + rs.getString(2) + " "
                            + rs.getInt(3));
                }
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
