package com.company.service;

import com.company.dao.PostDAO;
import com.company.dto.PostDTO;

import java.util.List;

public class PostService {

    private static final PostService postServiceInstance = new PostService();

    private PostService() {}

    public static PostService getInstance() {
        return postServiceInstance;
    }

    public void addPost(PostDTO postDTO) {
        PostDAO.getInstance().addPost(postDTO);
    }

    public List<PostDTO> getAllPosts() {
        return PostDAO.getInstance().getAllPosts();
    }

    public List<PostDTO> getPostsByUserId(int userId) {
        return PostDAO.getInstance().getPostsByUserId(userId);
    }
}
