package com.codesmachine.springbootrestapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDtoV2 {
    private String id;

    // title should not be null or empty
    // title should atleast have two characters

    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty
    @NotNull
    @Size(min = 2,message = "Post title should have atleast two characters")
    private String title;

    @Schema(
            description = "Blog Post Description"
    )
    @NotEmpty
    @NotNull
    @Size(min = 10,message = "Post Description should have atleast ten characters")
    private String description;

    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty
    @NotNull
    @Size(max = 20,message = "Post Content should not have more than 20 characters ")
    @Size(min = 5, message = "Post Content should not have less than 5 characters" )
    private String content;

    @Schema(
            description = "Blog Post Comments"
    )
    private Set<CommentDto> comments;

    @Schema(
            description = "Blog Post Category"
    )
    private String categoryId;

    private List<String> tags;
}
