package co.com.astropay.usecase.getallpost;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostConsumer;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
import co.com.astropay.usecase.getallposttitle.GetAllPostTitleUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetAllPostUseCaseTest {

    @Mock
    private PostConsumer postRepository;

    @Mock
    private GetAllPostTitleUseCase getAllPostTitleUseCase;

    @InjectMocks
    private GetAllPostUseCase getAllPostUseCase;

    @Test
    public void testValid() throws IOException {

        Optional<String> title =Optional.empty();

        Post p1 = Post.builder().title("klsdlsd").build();
        Post p2 = Post.builder().title("aaaidsdsc").build();
        List<Post> postList = List.of(p1, p2);

        Mockito.when(postRepository.getAll()).thenReturn(postList);
        List<Post> result = getAllPostUseCase.getAll(title);

        Assertions.assertEquals(postList.size(), result.size());
    }

    @Test
    public void testGetAllTitle() throws IOException {
        Optional<String> title = Optional.of("sunt");

        Post p1 = Post.builder().title("sunt 1").build();
        Post p2 = Post.builder().title("sunt 2").build();
        List<Post> postList = List.of(p1, p2);

        Mockito.when(getAllPostTitleUseCase.getAllFilterByTitle(title.get())).thenReturn(postList);

        List<Post> result = getAllPostUseCase.getAll(title);

        Assertions.assertEquals(postList.size(), result.size());
        Assertions.assertEquals(postList.get(0).getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testException() throws IOException {
        List<Post> postList = Collections.emptyList();
        Mockito.when(postRepository.getAll()).thenReturn(postList);
        Exception ex = Assertions.assertThrows(
                NoContentException.class, ()->{ getAllPostUseCase.getAll(Optional.empty());
        });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("No existen datos asociados a esta busqueda", ex.getMessage());
    }


}