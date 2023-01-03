package co.com.astropay.api;
import co.com.astropay.model.post.Post;
import co.com.astropay.usecase.getallpost.GetAllPostUseCase;
import co.com.astropay.usecase.getpostbyid.GetPostByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
   private final GetAllPostUseCase getAllPostUseCase;

   private final GetPostByIdUseCase getPostByIdUseCase;

    @GetMapping(path = "/posts")
    public List<Post> getAll() throws IOException {
        return getAllPostUseCase.getAll();
    }

    @GetMapping(path = "/posts/{id}")
    public Post getPost(@PathVariable(name = "id") int id) throws IOException {
        return getPostByIdUseCase.getById(id);
    }

    
}
