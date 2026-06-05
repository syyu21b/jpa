package com.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    // 생성 요청 DTO
    @Getter
    @NoArgsConstructor
    public static class CreateRequest {
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 100, message = "제목은 100자 이하여야 합니다.")
        private String title;

        @NotBlank(message = "내용은 필수입니다.")
        private String content;

        @NotBlank(message = "작성자는 필수입니다.")
        private String author;
    }

    // 수정 요청 DTO
    @Getter
    @NoArgsConstructor
    public static class UpdateRequest {
        @NotBlank(message = "제목은 필수입니다.")
        private String title;

        @NotBlank(message = "내용은 필수입니다.")
        private String content;
    }

    // 응답 DTO
    @Getter
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private String author;
        private String createdAt;
        private String updatedAt;

        public static Response from(com.example.demo.entity.Post post) {
            Response response = new Response();
            response.id = post.getId();
            response.title = post.getTitle();
            response.content = post.getContent();
            response.author = post.getAuthor();
            response.createdAt = post.getCreatedAt() != null ? post.getCreatedAt().toString() : null;
            response.updatedAt = post.getUpdatedAt() != null ? post.getUpdatedAt().toString() : null;
            return response;
        }
    }
}
