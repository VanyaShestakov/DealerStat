package by.ivanshestakov.dealerstat.rest.dto.converter;

import by.ivanshestakov.dealerstat.rest.dto.CommentDTO;
import by.ivanshestakov.dealerstat.rest.entity.Comment;
import by.ivanshestakov.dealerstat.rest.entity.GameObject;
import by.ivanshestakov.dealerstat.rest.entity.User;
import by.ivanshestakov.dealerstat.rest.exception.RecordNotFoundException;
import by.ivanshestakov.dealerstat.rest.repository.GameObjectRepository;
import by.ivanshestakov.dealerstat.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommentConverter {

    private final GameObjectRepository gameObjectRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentConverter(GameObjectRepository gameObjectRepository, UserRepository userRepository) {
        this.gameObjectRepository = gameObjectRepository;
        this.userRepository = userRepository;
    }

    public Comment convertToEntity(CommentDTO commentDTO) {
        GameObject gameObject = gameObjectRepository
                .findById(commentDTO.getGameObjectId())
                .orElseThrow(() -> {
                    throw new RecordNotFoundException("gameObject with id=" + commentDTO.getGameObjectId() + " not found");
                });
        User author = userRepository
                .findById(commentDTO.getAuthorId())
                .orElseThrow(() -> {
                    throw new RecordNotFoundException("author with id=" + commentDTO.getAuthorId() + " not found");
                });
        Comment comment = new Comment();
        comment.setApproved(commentDTO.isApproved());
        comment.setCreatedAt(commentDTO.getCreatedAt());
        comment.setMessage(commentDTO.getMessage());
        comment.setAuthor(author);
        comment.setGameObject(gameObject);
        return comment;
    }

    public CommentDTO convertToDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthorId(comment.getAuthor().getId());
        commentDTO.setGameObjectId(comment.getGameObject().getId());
        commentDTO.setApproved(comment.isApproved());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setId(comment.getId());
        return commentDTO;
    }
}
