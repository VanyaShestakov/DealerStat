package by.ivanshestakov.dealerstat.rest.service;

import by.ivanshestakov.dealerstat.rest.entity.Comment;

import java.util.List;

public interface CommentService {
    public void saveComment(Comment comment);

    public List<Comment> getAllComments();

}
