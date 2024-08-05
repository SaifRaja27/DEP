import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDApp {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/crud_db";
        String username="root";
        String password="1234";

        try(Connection conn=DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database successfully.");

            createUser(conn,"SAIF RAJA","saifraja@gmail.com");
            System.out.println("User created successfully.");

            User user=readUser(conn,1);
            if(user!=null) {
                System.out.println(user.getName()+" - "+user.getEmail());
            } else {
                System.out.println("User not found.");
            }

            updateUser(conn,1,"Jane Doe","jane.doe@example.com");
            System.out.println("User updated successfully.");

            deleteUser(conn,1);
            System.out.println("User deleted successfully.");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(Connection conn,String name,String email) throws SQLException {
        String query="INSERT INTO users (name, email) VALUES (?, ?)";
        try(PreparedStatement pstmt= conn.prepareStatement(query)) {
            pstmt.setString(1,name);
            pstmt.setString(2,email);
            pstmt.executeUpdate();
        }
    }

    public static User readUser(Connection conn,int id) throws SQLException {
        String query= "SELECT * FROM users WHERE id = ?";
        try(PreparedStatement pstmt =conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try(ResultSet rs =pstmt.executeQuery()) {
                if(rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                }
            }
        }
        return null;
    }

    public static void updateUser(Connection conn, int id, String name, String email) throws SQLException {
        String query ="UPDATE users SET name = ?, email = ? WHERE id = ?";
        try(PreparedStatement pstmt =conn.prepareStatement(query)) {
            pstmt.setString(1,name);
            pstmt.setString(2,email);
            pstmt.setInt(3,id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteUser(Connection conn, int id) throws SQLException {
        String query ="DELETE FROM users WHERE id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id =id;
        this.name =name;
        this.email =email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

