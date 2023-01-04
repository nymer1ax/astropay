package co.com.astropay.jpa.post;

import co.com.astropay.jpa.helper.AdapterOperations;
import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostDataAdapter extends AdapterOperations<Post, PostData, Long, PostDataRepository> implements PostRepository {


    public PostDataAdapter(PostDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Post.class/* change for domain model */));
    }

    @Override
    public Post guardar(Post post) {

        PostData p = PostData.builder()
                .title(post.getTitle())
                .userId(post.getUserId())
                .body(post.getBody())
                .build();

        var a = this.repository.save(p);

        return post;
    }

    @Override
    public List<Post> getAll() {
        return this.repository.findAll().stream().map(m -> Post.builder().title(m.getTitle()).body(m.getBody()).build()).collect(Collectors.toList());
    }
}
