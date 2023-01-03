package co.com.astropay.model.comments;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Comments {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
