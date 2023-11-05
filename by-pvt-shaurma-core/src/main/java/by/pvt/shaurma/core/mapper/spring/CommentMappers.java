package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.CommentRequest;
import by.pvt.shaurma.api.dto.CommentResponse;
import by.pvt.shaurma.core.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMappers {
    CommentResponse toResponse(Comment comment);
    Comment toEntity(CommentRequest commentRequest);
}
