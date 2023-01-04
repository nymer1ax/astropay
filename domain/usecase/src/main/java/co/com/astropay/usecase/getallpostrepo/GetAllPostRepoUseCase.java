package co.com.astropay.usecase.getallpostrepo;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostRepository;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllPostRepoUseCase {
    private final PostRepository postRepository;

    public List<Post> getallpostpersisted(){
        List<Post> posts = postRepository.getAll();

        if(posts.isEmpty()){
            throw new NoContentException("No hay datos");
        }

        return posts;
    }
}
