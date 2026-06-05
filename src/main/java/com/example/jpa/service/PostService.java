package com.example.jpa.service;

import com.example.jpa.dto.PostDto;
import com.example.jpa.entity.Post;
import com.example.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션
public class PostService {

    private final PostRepository postRepository;

    // ✅ CREATE
    @Transactional  // 쓰기 작업은 @Transactional 별도 선언
    public PostDto.Response createPost(PostDto.CreateRequest request) {
        Post post = Post.create(request.getTitle(), request.getContent(), request.getAuthor());
        Post savedPost = postRepository.save(post);
        return PostDto.Response.from(savedPost);
    }

    // ✅ READ - 전체 조회
    public List<PostDto.Response> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostDto.Response::from)
                .collect(Collectors.toList());
    }

    // ✅ READ - 단건 조회
    public PostDto.Response getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다. id: " + id));
        return PostDto.Response.from(post);
    }

    // ✅ UPDATE
    @Transactional
    public PostDto.Response updatePost(Long id, PostDto.UpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다. id: " + id));

        post.update(request.getTitle(), request.getContent()); // 더티 체킹(Dirty Checking)으로 자동 업데이트
        return PostDto.Response.from(post);
    }

    // ✅ DELETE
    @Transactional
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("게시글을 찾을 수 없습니다. id: " + id);
        }
        postRepository.deleteById(id);
    }

    // ✅ SEARCH
    public List<PostDto.Response> searchPosts(String keyword) {
        return postRepository.searchByKeyword(keyword)
                .stream()
                .map(PostDto.Response::from)
                .collect(Collectors.toList());
    }
}