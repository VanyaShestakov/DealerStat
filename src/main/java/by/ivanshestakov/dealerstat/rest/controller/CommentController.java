package by.ivanshestakov.dealerstat.rest.controller;

import by.ivanshestakov.dealerstat.rest.dto.CommentDTO;
import by.ivanshestakov.dealerstat.rest.dto.converter.CommentConverter;
import by.ivanshestakov.dealerstat.rest.entity.Comment;
import by.ivanshestakov.dealerstat.rest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

}
