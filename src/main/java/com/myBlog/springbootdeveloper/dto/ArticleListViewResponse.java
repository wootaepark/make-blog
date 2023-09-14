package com.myBlog.springbootdeveloper.dto;


import com.myBlog.springbootdeveloper.domain.Article;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
