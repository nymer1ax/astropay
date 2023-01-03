package co.com.astropay.api;
import co.com.astropay.model.post.Post;
import co.com.astropay.usecase.getallpost.GetAllPostUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
   private final GetAllPostUseCase getAllPostUseCase;


    @GetMapping(path = "/posts")
    public List<Post> commandName() throws IOException {
        return getAllPostUseCase.getAll();
    }
}
