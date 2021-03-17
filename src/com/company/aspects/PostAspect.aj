package com.company.aspects;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.company.dto.PostDTO;
import com.company.service.PostService;

public aspect PostAspect {
    Logger LOG = Logger.getLogger("com.company");

    pointcut callAddPost(PostDTO postDTO):
                call(* PostService.addPost(PostDTO)) && args(postDTO);

    before(PostDTO postDTO): callAddPost(postDTO) {
        LOG.log(Level.INFO, "Context: " + thisJoinPoint.getSignature() + " - " +
                "User with id: " + postDTO.getUserId() + " triggered an add post event");
    }

    after(PostDTO postDTO) returning: callAddPost(postDTO) {
        LOG.log(Level.INFO, "Add post successfully executed with details: " + postDTO.toString());
    }

    after(PostDTO postDTO) throwing(Exception e): callAddPost(postDTO) {
        LOG.log(Level.SEVERE, e.getMessage());
    }

    pointcut callGetAllPosts():
                call(* PostService.getAllPosts());

    before(): callGetAllPosts() {
        LOG.log(Level.INFO, "Context:" + thisJoinPoint.getSignature() + " - " +
                "Get all posts triggered");
    }

    after() returning(List<PostDTO> posts): callGetAllPosts() {
        LOG.log(Level.INFO, "Get all posts successfully executed; " +
                posts.size() + " posts retrieved");
    }

    after() throwing(RuntimeException e): callGetAllPosts() {
        LOG.log(Level.SEVERE, e.getMessage());
    }
}
