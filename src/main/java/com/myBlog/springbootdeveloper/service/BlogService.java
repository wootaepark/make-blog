package com.myBlog.springbootdeveloper.service;

import com.myBlog.springbootdeveloper.domain.Article;
import com.myBlog.springbootdeveloper.dto.AddArticleRequest;
import com.myBlog.springbootdeveloper.dto.UpdateArticleRequest;
import com.myBlog.springbootdeveloper.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    /*public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }*/

    // @RequiredArgsConstructor는 위와 같이 final 이나 @NotNull 이 붙은 필드가 있는 생성자를 만든다.

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){return blogRepository.findAll();}

    public Article findById(Long id){return blogRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("not found: " + id));}

    public void delete(Long id){blogRepository.deleteById(id);}

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article=blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }



}
