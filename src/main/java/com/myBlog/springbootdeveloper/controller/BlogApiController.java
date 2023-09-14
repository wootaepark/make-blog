package com.myBlog.springbootdeveloper.controller;

import com.myBlog.springbootdeveloper.domain.Article;
import com.myBlog.springbootdeveloper.dto.AddArticleRequest;
import com.myBlog.springbootdeveloper.dto.ArticleResponse;
import com.myBlog.springbootdeveloper.dto.UpdateArticleRequest;
import com.myBlog.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>>findAllArticles(){
        List<ArticleResponse> articles=blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
        // return ResponsseEntity.status(HttpStatus.OK).body(articles); 도 가능함
        //  이 경우 유연성이 더 있다. .OK 를 CREATED,NO CONTENT 등으로 바꿀 수 있는 장점
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article=blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));  // 우리는 위에서 AticleResponse 형태를 아무것도 지정 x
    }
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build(); // 빈 데이터 body 를 반환하는 것 위에 <Void> 인 이유가 있다.
    }

    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle=blogService.update(id,request);
        return ResponseEntity.ok()
                .body(updatedArticle);

    }
}
