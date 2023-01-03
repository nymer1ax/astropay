package co.com.astropay.model.post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
