package co.com.astropay.api;
import co.com.astropay.model.comments.Comments;
import co.com.astropay.model.post.Post;
import co.com.astropay.usecase.exceptions.Response;
import co.com.astropay.usecase.getallcomments.GetAllCommentsUseCase;
import co.com.astropay.usecase.getallpost.GetAllPostUseCase;
import co.com.astropay.usecase.getallpostrepo.GetAllPostRepoUseCase;
import co.com.astropay.usecase.getallposttitle.GetAllPostTitleUseCase;
import co.com.astropay.usecase.getpostbyid.GetPostByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
   private final GetAllPostUseCase getAllPostUseCase;

   private final GetPostByIdUseCase getPostByIdUseCase;

   private final GetAllCommentsUseCase getAllCommentsUseCase;

   private final GetAllPostRepoUseCase getAllPostRepoUseCase;


    @GetMapping(path = "/posts")
    public ResponseEntity<Response> getAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size, @RequestParam(name = "title", required = false) Optional<String> title) throws IOException {

        List<Post> posts = getAllPostUseCase.getAll(title);

        Page<Post> pageList = new PageImpl<Post>(posts, PageRequest.of(page,
                size), posts.size());

        Response response = Response.builder()
                .codigoResultado("200")
                .descripcionRespuesta("OK")
                .fecha(LocalDateTime.now().toString())
                .result(pageList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Response> getPost(@PathVariable(name = "id") int id) throws IOException {

        Response response = Response.builder()
                .codigoResultado("200")
                .descripcionRespuesta("OK")
                .fecha(LocalDateTime.now().toString())
                .result(getPostByIdUseCase.getById(id))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/posts/{id}/comments")
    public ResponseEntity<Response>  getPostComments(@PathVariable(name = "id") int id) throws IOException {

        Response response = Response.builder()
                .codigoResultado("200")
                .descripcionRespuesta("OK")
                .fecha(LocalDateTime.now().toString())
                .result(getAllCommentsUseCase.commentsList(id))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/posts/persisted")
    public List<Post> getPostPersisted(){
        return getAllPostRepoUseCase.getallpostpersisted();
    }





}
