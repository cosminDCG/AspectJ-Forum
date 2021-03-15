package com.company.service;

import com.company.dao.CommDAO;
import com.company.dto.CommDTO;

import java.util.List;

public class CommService {

    private static final CommService commServiceInstance = new CommService();

    private CommService() {}

    public static CommService getInstance() {
        return commServiceInstance;
    }

    public void addComment(CommDTO commDTO) {
        CommDAO.getInstance().addComment(commDTO);
    }

    public List<CommDTO> getCommentsByPostId(int postId) {
        return CommDAO.getInstance().getCommentsByPostId(postId);
    }
}
