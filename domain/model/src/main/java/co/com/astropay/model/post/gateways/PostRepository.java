package co.com.astropay.model.post.gateways;

import co.com.astropay.model.post.Post;

import java.io.IOException;
import java.util.List;

public interface PostRepository {

    List<Post> getAll() throws IOException;
    Post getById(int id) throws IOException;
}
