package models;

import java.time.LocalDateTime;

public class News {
    private int id;
    private String postDate;
    private NewsCategory categoryId;
    private String title;
    private String text;

    public News() {
    }

    public News(int id, String postDate, NewsCategory categoryId, String title, String text) {
        this.id = id;
        this.postDate = postDate;
        this.categoryId = categoryId;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public NewsCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(NewsCategory categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
