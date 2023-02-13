package com.c0722g1repobe.dto.post.create_post;

import com.c0722g1repobe.utils.ResponseStatusEnum;
import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BaseResponseCreatePost {
    private Integer code = 200;
    private ResponseStatusEnum status = ResponseStatusEnum.SUCCESS;
    private String message = "Thêm mới thành công";
    private CreatePostDto createPostDto;
}
