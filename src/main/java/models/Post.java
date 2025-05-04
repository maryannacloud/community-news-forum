package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Post {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

    private final UUID id;
    private final String title;
    private final String message;
    private final User user;
    private final Date date;
    private final List<Comment> comments;

    public Post(String title, String message, User user) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.message = message;
        this.user = user;
        this.date = new Date();
        this.comments = new ArrayList<>();
    }

    public Post(String id, String title, String message, User user, long date, List<Comment> comments) {
        this.id = UUID.fromString(id);
        this.title = title;
        this.message = message;
        this.user = user;
        this.date = new Date(date);
        this.comments = comments;
    }

    public void addComment(User user, String comment) {
        if (comment.length() > 168){
            comment = comment.substring(0, 165);
            comment = comment.concat("...");
        }

        Comment newComment = new Comment(comment, user, this);
        comments.add(newComment);
    }

    public void displayComments() {
        System.out.println("There are " + comments.size() + " comments:");
        comments.forEach(comment -> {
            System.out.print("On " + simpleDateFormat.format(comment.getDate()));
            System.out.println(", " + comment.getUser().getUsername() + " commented: ");
            System.out.println(" " + comment.getComment());
        });
    }

    public void printPost(boolean withComments) {
        System.out.println("Title: " + title);
        System.out.println("Date: " + simpleDateFormat.format(date));
        System.out.println("Message: " + message);

        if (withComments) {
            displayComments();
        }
    }

    public String getIdString() {
        return id.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
