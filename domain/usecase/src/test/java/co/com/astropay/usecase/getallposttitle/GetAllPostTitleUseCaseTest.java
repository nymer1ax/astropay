package co.com.astropay.usecase.getallposttitle;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostConsumer;
import co.com.astropay.usecase.exceptions.custom.BadRequestException;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetAllPostTitleUseCaseTest {

    @Mock
    private PostConsumer postRepository;

    @InjectMocks
    GetAllPostTitleUseCase useCase;

    @Test
    public void testValid() throws IOException {
        String title = "sunt";

        Post p1 = Post.builder().title("sunt 1").build();
        Post p2 = Post.builder().title("sunt 2").build();
        List<Post> postList = List.of(p1, p2);

        Mockito.when(postRepository.getAll()).thenReturn(postList);

        List<Post> result = useCase.getAllFilterByTitle(title);

        Assertions.assertEquals(postList.size(), result.size());
        Assertions.assertEquals(postList.get(0).getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testPostEmpty() throws IOException {
        String title = "sunt";
        List<Post> posts = Collections.emptyList();

        Mockito.when(postRepository.getAll()).thenReturn(posts);

        Exception ex = Assertions.assertThrows(
                NoContentException.class,
                () -> {
                    useCase.getAllFilterByTitle(title);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("No existen datos asociados a esta busqueda", ex.getMessage());
    }

    @Test
    public void testTitleNullOrBlank() throws IOException {
        String title = "";
        Post p1 = Post.builder().title("sunt 1").build();
        Post p2 = Post.builder().title("sunt 2").build();
        List<Post> postList = List.of(p1, p2);
        Mockito.when(postRepository.getAll()).thenReturn(postList);
        Exception ex = Assertions.assertThrows(
                BadRequestException.class,
                () -> {
                    useCase.getAllFilterByTitle(title);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("No se ingreso el nombre del titulo correctamente.", ex.getMessage());
    }

    @Test
    public void testFilteredListEmpty() throws IOException {

        String title = "sunt";

        List<Post> posts = Collections.emptyList();

        Mockito.when(postRepository.getAll()).thenReturn(posts);
        Exception ex = Assertions.assertThrows(
                NoContentException.class,
                () -> {
                    useCase.getAllFilterByTitle(title);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("No existen datos asociados a esta busqueda", ex.getMessage());
    }
}