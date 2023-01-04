package co.com.astropay.model.post.gateways;

import co.com.astropay.model.post.Post;

import java.util.List;

public interface PostRepository {
    Post guardar(Post post);
    List<Post> getAll();
}
