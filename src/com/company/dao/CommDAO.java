package com.company.dao;

import com.company.dto.CommDTO;
import com.company.dto.UserDTO;
import com.company.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommDAO {

    private static final CommDAO commDAOInstance = new CommDAO();
    private final Connection dbConnection;

    private CommDAO() {
        dbConnection = DbUtil.getConnection();
    }

    public static CommDAO getInstance() {
        return commDAOInstance;
    }

    public void addComment(CommDTO commDTO) {
        String sqlInsert = "" +
                "INSERT INTO comentarii (post_id, user_id, comm_text, comm_date) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlInsert);
            ps.setInt(1, commDTO.getPostId());
            ps.setInt(2, commDTO.getUser().getUserId());
            ps.setString(3, commDTO.getCommText());
            ps.setDate(4, new Date(commDTO.getCommDate().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CommDTO> getCommentsByPostId(int postId) {
        List<CommDTO> commDTOList = new ArrayList<>();
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM comentarii " +
                "WHERE post_id = ? ";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sqlSelect);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                CommDTO commDTO = new CommDTO();
                commDTO.setCommId(rs.getInt("comm_id"));
                commDTO.setPostId(rs.getInt("post_id"));
                commDTO.setUser(new UserDTO(rs.getInt("user_id")));
                commDTO.setCommText(rs.getString("comm_text"));
                commDTO.setCommDate(rs.getDate("comm_date"));
                commDTOList.add(commDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commDTOList;
    }
}
