package co.com.astropay.usecase.getallposttitle;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAllPostTitleUseCase {
    private final PostRepository postRepository;

    public List<Post> getAllFilterByTitle(String title) throws IOException {

        List<Post> posts = postRepository.getAll();

        if(posts.isEmpty()){
            throw new RuntimeException("No existen posts");
        }

        if(title.isBlank() || title == null){
            throw new RuntimeException("No se ingreso contenido");
        }

       List<Post> filtered = posts.stream().filter(p -> p.getTitle().contains(title)).collect(Collectors.toList());

        if(filtered.isEmpty()){
            throw new RuntimeException("No ha concidido tu busqueda");
        }

        return filtered;
    }
}
