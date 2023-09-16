package com.myBlog.springbootdeveloper.controller;


import com.myBlog.springbootdeveloper.dto.AddUserRequest;
import com.myBlog.springbootdeveloper.repository.UserRepository;
import com.myBlog.springbootdeveloper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserApiController {
    private final UserService userService;
    
    @PostMapping("/user")
    public String signup(AddUserRequest request){



        userService.save(request); // 회원 가입 메서드 호출
        return "redirect:/login"; // 회원 가입 이후 로그인 페이지 이동
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
