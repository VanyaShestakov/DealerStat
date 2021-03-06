package by.ivanshestakov.dealerstat.rest.service.impl;

import by.ivanshestakov.dealerstat.rest.entity.Comment;
import by.ivanshestakov.dealerstat.rest.repository.CommentRepository;
import by.ivanshestakov.dealerstat.rest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }


}
