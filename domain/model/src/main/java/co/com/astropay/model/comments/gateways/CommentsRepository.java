package co.com.astropay.model.comments.gateways;

import co.com.astropay.model.comments.Comments;

import java.io.IOException;
import java.util.List;

public interface CommentsRepository {
    List<Comments> getAll(int postId) throws IOException;
}
