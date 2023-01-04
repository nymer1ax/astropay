package co.com.astropay.usecase.getallpost;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostConsumer;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
import co.com.astropay.usecase.getallposttitle.GetAllPostTitleUseCase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GetAllPostUseCase {
    private final PostConsumer postRepository;

    private final GetAllPostTitleUseCase getAllPostTitleUseCase;

    public List<Post> getAll(Optional<String> title) throws IOException {

        if(title.isPresent()){
            return getAllPostTitleUseCase.getAllFilterByTitle(title.get());
        }

        List<Post> posts = postRepository.getAll();

        if(posts.isEmpty()){
            throw new NoContentException("No existen datos asociados a esta busqueda");
        }

        return posts;
    }
}
