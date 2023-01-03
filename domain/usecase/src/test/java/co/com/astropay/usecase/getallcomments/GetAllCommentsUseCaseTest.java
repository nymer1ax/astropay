package co.com.astropay.usecase.getallcomments;


import co.com.astropay.model.comments.Comments;
import co.com.astropay.model.comments.gateways.CommentsRepository;
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
class GetAllCommentsUseCaseTest {

    @Mock
    private CommentsRepository commentsRepository;

    @InjectMocks
    private GetAllCommentsUseCase getAllCommentsUseCase;

    @Test
    public void testCommentList() throws IOException {
        Comments comments1 = Comments.builder()
                .id(1)
                .name("Blog title")
                .postId(1)
                .email("blog@gmail.com")
                .body("Este blog bla bla")
                .build();

        Comments comments2 = Comments.builder()
                .id(2)
                .name("Blog title")
                .postId(1)
                .email("blog2@gmail.com")
                .body("Este blog bla bla no me gusta")
                .build();

        List<Comments> commentsList = List.of(comments1, comments2);

        Mockito.when(commentsRepository.getAll(1)).thenReturn(commentsList);

        List<Comments> result = getAllCommentsUseCase.commentsList(1);

        Assertions.assertEquals(commentsList.size(), result.size());
    }

    @Test
    public void testCommentListFails() throws IOException {
        Mockito.when(commentsRepository.getAll(1)).thenReturn(Collections.emptyList());

        Exception ex = Assertions.assertThrows(
                NoContentException.class,
                ()->{
                    getAllCommentsUseCase.commentsList(1);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("No existen datos asociados a esta busqueda", ex.getMessage());

    }

}