package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.CommentRequest;
import by.pvt.shaurma.api.dto.CommentResponse;

import java.util.List;

public interface CommentApi {
    CommentResponse save(CommentRequest commentRequest);

    void delete(Long id);

    CommentResponse findById(Long id);

    List<CommentResponse> getAll();
}
