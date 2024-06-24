package model;

import entity.User;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUser extends DBConnect {

    public int insertUser(User user) {
        int n = 0;
        String sql = "INSERT INTO [User] (FullName, Gender, Address, Phone, Email, Role, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getAddress());
            pre.setString(4, user.getPhone());
            pre.setString(5, user.getEmail());
            pre.setString(6, user.getRole());
            pre.setString(7, user.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateUser(User user) {
        int n = 0;
        String sql = "UPDATE [User] SET FullName = ?, Gender = ?, Address = ?, Phone = ?, Email = ?, Role = ?, Password = ? WHERE UserID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getGender());
            pre.setString(3, user.getAddress());
            pre.setString(4, user.getPhone());
            pre.setString(5, user.getEmail());
            pre.setString(6, user.getRole());
            pre.setString(7, user.getPassword());
            pre.setInt(8, user.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteUser(String userID) {
        int n = 0;
        String sql = "DELETE FROM [User] WHERE UserID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, userID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<User> getAllUsers(String sql) {
        Vector<User> vector = new Vector<>();
        try {
            Statement state = (Statement) conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt("UserID");
                String fullName = rs.getString("FullName");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String role = rs.getString("Role");
                String password = rs.getString("Password");
                User user = new User(userID, fullName, gender, address, phone, email, role, password);
                vector.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    

    public User getUserByID(int userID) {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE UserID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("FullName");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String role = rs.getString("Role");
                String password = rs.getString("Password");
                user = new User(userID, fullName, gender, address, phone, email, role, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public int getUserCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM [User]";
        try (
             PreparedStatement pre = conn.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    
    public boolean login(String email, String password) {
        String sql = "select * from [User] where Email=? and Password=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, password);

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String getRoleByEmail(String email) {
    String role = null;
    String sql = "SELECT Role FROM [User] WHERE Email = ?";
    try (PreparedStatement pre = conn.prepareStatement(sql)) {
        pre.setString(1, email);
        try (ResultSet rs = pre.executeQuery()) {
            if (rs.next()) {
                role = rs.getString("Role");
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return role;
}


    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        boolean check = dao.login( "PA001", "123");
        if (check = true) {
            System.out.print("ok");
        }
    }
}
