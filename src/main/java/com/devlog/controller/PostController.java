package com.devlog.controller;

import com.devlog.dto.post.PostDto;
import com.devlog.dto.post.PostRequest;
import com.devlog.security.JwtUtil;
import com.devlog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostRequest request,
                              @RequestHeader("Authorization") String authHeader) {
        String username = jwtUtil.getUsernameFromHeader(authHeader);
        return postService.createPost(request, username);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id,
                              @RequestBody PostRequest request,
                              @RequestHeader("Authorization") String authHeader) {
        String username = jwtUtil.getUsernameFromHeader(authHeader);
        return postService.updatePost(id, request, username);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id,
                           @RequestHeader("Authorization") String authHeader) {
        String username = jwtUtil.getUsernameFromHeader(authHeader);
        postService.deletePost(id, username);
    }
}
