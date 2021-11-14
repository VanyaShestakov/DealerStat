package by.ivanshestakov.dealerstat.rest.controller;

import by.ivanshestakov.dealerstat.rest.dto.CommentDTO;
import by.ivanshestakov.dealerstat.rest.dto.converter.CommentConverter;
import by.ivanshestakov.dealerstat.rest.entity.Comment;
import by.ivanshestakov.dealerstat.rest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    private final CommentConverter converter;

    @Autowired
    public CommentController(CommentService commentService, CommentConverter converter) {
        this.commentService = commentService;
        this.converter = converter;
    }

    @PostMapping("/articles/comments")
    private ResponseEntity<CommentDTO> postComment(@RequestBody CommentDTO comment) {
        comment.setCreatedAt(new Date());
        commentService.saveComment(converter.convertToEntity(comment));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/articles/comments")
    private List<Comment> getComments() {
        return commentService.getAllComments();
    }

}
