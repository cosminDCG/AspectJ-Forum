package com.company.aspects;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.company.dto.CommDTO;
import com.company.service.CommService;

public aspect CommAspect {
    Logger LOG = Logger.getLogger("com.company");

    pointcut callAddComment(CommDTO commDTO):
                call(* CommService.addComment(CommDTO)) && args(commDTO);

    before(CommDTO commDTO): callAddComment(commDTO) {
        LOG.log(Level.INFO, "Context: " + thisJoinPoint.getSignature() + " - " +
                "User with id: " + commDTO.getUser().getUserId() + " triggered an comm post event " +
                "for post with id: " + commDTO.getPostId());
    }

    after(CommDTO commDTO) returning: callAddComment(commDTO) {
        LOG.log(Level.INFO, "Add comment successfully executed with details: " + commDTO.toString());
    }

    after(CommDTO commDTO) throwing(Exception e): callAddComment(commDTO) {
        LOG.log(Level.SEVERE, e.getMessage());
    }

    pointcut callGetCommentsByPostId(int postId):
                call(* CommService.getCommentsByPostId(int)) && args(postId);

    before(int postId): callGetCommentsByPostId(postId) {
        LOG.log(Level.INFO, "Context: " + thisJoinPoint.getSignature() + " - " +
                "Get all comments event triggered for post with id: " + postId);
    }

    after(int postId) returning(List<CommDTO> comments): callGetCommentsByPostId(postId) {
        LOG.log(Level.INFO, "Get al comments for post with id: " + postId + " successfully executed; " +
                comments.size() + " comments retrieved");
    }

    after(int postId) throwing(Exception e): callGetCommentsByPostId(postId) {
        LOG.log(Level.SEVERE, e.getMessage());
    }
}
