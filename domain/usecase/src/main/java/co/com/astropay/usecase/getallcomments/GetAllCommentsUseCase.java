package co.com.astropay.usecase.getallcomments;

import co.com.astropay.model.comments.Comments;
import co.com.astropay.model.comments.gateways.CommentsRepository;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class GetAllCommentsUseCase {
    private final CommentsRepository commentsRepository;

    public List<Comments> commentsList(int postId) throws IOException {
        List<Comments> comments = commentsRepository.getAll(postId);
        if(comments.isEmpty()){
            throw new NoContentException("No existen datos asociados a esta busqueda");
        }
        return comments;
    }
}
