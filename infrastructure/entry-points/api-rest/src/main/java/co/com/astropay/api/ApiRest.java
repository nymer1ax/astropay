package co.com.astropay.api;
import co.com.astropay.model.comments.Comments;
import co.com.astropay.model.post.Post;
import co.com.astropay.usecase.getallcomments.GetAllCommentsUseCase;
import co.com.astropay.usecase.getallpost.GetAllPostUseCase;
import co.com.astropay.usecase.getallposttitle.GetAllPostTitleUseCase;
import co.com.astropay.usecase.getpostbyid.GetPostByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
   private final GetAllPostUseCase getAllPostUseCase;

   private final GetPostByIdUseCase getPostByIdUseCase;

   private final GetAllCommentsUseCase getAllCommentsUseCase;


    @GetMapping(path = "/posts")
    public List<Post> getAll(@RequestParam(name = "title", required = false) Optional<String> title) throws IOException {
        return getAllPostUseCase.getAll(title);
    }

    @GetMapping(path = "/posts/{id}")
    public Post getPost(@PathVariable(name = "id") int id) throws IOException {
        return getPostByIdUseCase.getById(id);
    }

    @GetMapping(path = "/posts/{id}/comments")
    public List<Comments> getPostComments(@PathVariable(name = "id") int id) throws IOException {
        return getAllCommentsUseCase.commentsList(id);
    }




}
