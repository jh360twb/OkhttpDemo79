package com.example.okhttpdemo79.beans;

/**
 * @author Tian
 * @description
 * @date :2020/7/9 10:02
 */
public class PostRequest {

    /**
     * articleId : 234123
     * commentContent : 这是评论内容
     */

    private String articleId;
    private String commentContent;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
