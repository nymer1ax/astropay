package co.com.astropay.consumer.comments;

import co.com.astropay.model.comments.Comments;
import co.com.astropay.model.comments.gateways.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class RestCommentsAdapter implements CommentsRepository {

    private final RestCommentsConsumer restCommentsConsumer;

    private final RestCommentsMapper restCommentsMapper;
    @Override
    public List<Comments> getAll(int postId) throws IOException {
        List<RestCommentsResponse> postComments =  restCommentsConsumer.getCommentByPostId(postId);
        return restCommentsMapper.restCommentResponseToCommmentsList(postComments);

    }
}
