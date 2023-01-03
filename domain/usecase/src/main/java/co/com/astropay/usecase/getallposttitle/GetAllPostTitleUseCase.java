package co.com.astropay.usecase.getallposttitle;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostRepository;
import co.com.astropay.usecase.exceptions.custom.BadRequestException;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
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
            throw new NoContentException("No existen datos asociados a esta busqueda");
        }

        if(title.isBlank() || title == null){
            throw new BadRequestException("No se ingreso el nombre del titulo correctamente.");
        }

       List<Post> filtered = posts.stream().filter(p -> p.getTitle().contains(title)).collect(Collectors.toList());

        if(filtered.isEmpty()){
            throw new NoContentException("No existen datos asociados a esta busqueda");
        }

        return filtered;
    }
}
