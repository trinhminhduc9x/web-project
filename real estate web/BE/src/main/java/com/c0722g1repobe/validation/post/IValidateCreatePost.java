package com.c0722g1repobe.validation.post;

import com.c0722g1repobe.dto.post.create_post.BaseResponseCreatePost;
import com.c0722g1repobe.dto.post.create_post.CreatePostDto;

public interface IValidateCreatePost {
    BaseResponseCreatePost validateCreatePost(CreatePostDto createPostDto);
}
