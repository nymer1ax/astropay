package co.com.astropay.consumer.post;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestPostAdapter implements PostRepository {

    private final RestPostConsumer restPostConsumer;
    private final RestPostMapper restPostMapper;


    @Override
    public List<Post> getAll() throws IOException {
        List<RestPostResponse> posts = restPostConsumer.findAllPost();
        return restPostMapper.restPostResponseToPost(posts);
    }

    @Override
    public Post getById(int id) throws IOException {
        RestPostResponse post = restPostConsumer.findPostById(id);
        return restPostMapper.restPostResponseToPost(post);
    }
}
