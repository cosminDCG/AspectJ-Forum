package com.company.dao;

import com.company.dto.PostDTO;
import com.company.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    private static final PostDAO postDAOInstance = new PostDAO();
    private final Connection dbConnection;

    private PostDAO() {
        dbConnection = DbUtil.getConnection();
    }

    public static PostDAO getInstance() {
        return postDAOInstance;
    }

    public void addPost(PostDTO postDTO) {
        String sqlInsert = "" +
                "INSERT INTO postari (user_id, post_text, post_date) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlInsert);
            ps.setInt(1, postDTO.getUserId());
            ps.setString(2, postDTO.getPostText());
            ps.setDate(3, new Date(postDTO.getPostDate().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOList = new ArrayList<>();
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM postari";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlSelect);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PostDTO postDTO = new PostDTO();
                postDTO.setPostId(rs.getInt("post_id"));
                postDTO.setUserId(rs.getInt("user_id"));
                postDTO.setPostText(rs.getString("post_text"));
                postDTO.setPostDate(rs.getDate("post_date"));
                postDTOList.add(postDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postDTOList;
    }

    public List<PostDTO> getPostsByUserId(int userId) {
        List<PostDTO> postDTOList = new ArrayList<>();
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM postari " +
                "WHERE user_id = ?";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlSelect);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PostDTO postDTO = new PostDTO();
                postDTO.setPostId(rs.getInt("post_id"));
                postDTO.setUserId(rs.getInt("user_id"));
                postDTO.setPostText(rs.getString("post_text"));
                postDTO.setPostDate(rs.getDate("post_date"));
                postDTOList.add(postDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postDTOList;
    }
}
