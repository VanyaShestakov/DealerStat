package by.ivanshestakov.dealerstat.rest.dto;

import by.ivanshestakov.dealerstat.rest.entity.GameObject;
import by.ivanshestakov.dealerstat.rest.entity.User;

import javax.persistence.*;
import java.util.Date;


public class CommentDTO {

    private int id;

    private String message;

    private Date createdAt;

    private boolean approved;

    private int gameObjectId;

    private int authorId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getGameObjectId() {
        return gameObjectId;
    }

    public void setGameObjectId(int gameObjectId) {
        this.gameObjectId = gameObjectId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
