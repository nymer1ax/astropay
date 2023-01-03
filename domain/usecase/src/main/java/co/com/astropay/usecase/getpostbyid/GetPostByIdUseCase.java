package co.com.astropay.usecase.getpostbyid;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class GetPostByIdUseCase {
    private final PostRepository postRepository;

    public Post getById(int id) throws IOException {
        Post post = postRepository.getById(id);

        if(post == null){
            throw new RuntimeException("No hay contenido");
        }
        return post;
    }
}
