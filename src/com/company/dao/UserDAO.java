package com.company.dao;

import com.company.dto.UserDTO;
import com.company.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final UserDAO userDAOInstance = new UserDAO();
    private final Connection dbConnection;

    private UserDAO() {
        dbConnection = DbUtil.getConnection();
    }

    public static UserDAO getInstance() {
        return userDAOInstance;
    }

    public UserDTO getUserByEmail(String username) {

        UserDTO userDTO = null;
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM utilizatori " +
                "WHERE email = ?";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlSelect);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                userDTO = new UserDTO();
                userDTO.setUserId(rs.getInt("user_id"));
                userDTO.setFirstName(rs.getString("first_name"));
                userDTO.setLastName(rs.getString("last_name"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDTO;
    }

    public void addUser(UserDTO userDTO) {

        String sqlInsert = "" +
                "INSERT INTO utilizatori (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlInsert);
            ps.setString(1, userDTO.getFirstName());
            ps.setString(2, userDTO.getLastName());
            ps.setString(3, userDTO.getEmail());
            ps.setString(4,userDTO.getPassword());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String email) {

        String sqlDelete = "" +
                "DELETE " +
                "FROM utilizatori " +
                "WHERE email = ?";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlDelete);
            ps.setString(1, email);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
