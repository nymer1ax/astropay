package co.com.astropay.jpa.post;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PostData")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPost;
    @Column(name = "userId")
    private int userId;
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
}
