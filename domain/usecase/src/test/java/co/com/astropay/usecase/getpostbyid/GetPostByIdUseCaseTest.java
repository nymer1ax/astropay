package co.com.astropay.usecase.getpostbyid;

import co.com.astropay.model.post.Post;
import co.com.astropay.model.post.gateways.PostConsumer;
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


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetPostByIdUseCaseTest {
    @Mock
    private PostConsumer postRepository;

    @InjectMocks
    private GetPostByIdUseCase useCase;

    @Test
    public void testValid() throws IOException {
        int postId = 1;

        Post post = Post.builder().id(1).build();

        Mockito.when(postRepository.getById(postId)).thenReturn(post);

        Post result = useCase.getById(postId);

        Assertions.assertEquals(post.getId(), result.getId());
    }

    @Test
    public void testException() throws IOException {
        int postId = 1;
        Mockito.when(postRepository.getById(postId)).thenReturn(null);

        Exception ex = Assertions.assertThrows(
                NoContentException.class,
                () -> {
                    useCase.getById(postId);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("No existen datos asociados a esta busqueda", ex.getMessage());

    }
}