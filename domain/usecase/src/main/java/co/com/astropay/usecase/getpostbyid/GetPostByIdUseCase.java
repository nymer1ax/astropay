package co.com.astropay.usecase.getpostbyid;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostConsumer;
import co.com.astropay.model.post.gateways.PostRepository;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class GetPostByIdUseCase {
    private final PostConsumer postConsumer;

    private final PostRepository postRepository;

    public Post getById(int id) throws IOException {
        Post post = postConsumer.getById(id);

        if(post == null){
            throw new NoContentException("No existen datos asociados a esta busqueda");
        }
        postRepository.guardar(post);
        return post;
    }
}
