package co.com.astropay.usecase.getallpost;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class GetAllPostUseCase {
    private final PostRepository postRepository;

    public List<Post> getAll() throws IOException {
        List<Post> posts = postRepository.getAll();

        if(posts.isEmpty()){
            throw new RuntimeException("No hay contenido");
        }

        return posts;
    }
}
