package co.com.astropay.consumer.post;

import co.com.astropay.model.post.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class RestPostMapper {
    private final ObjectMapper mapper;

    public List<RestPostResponse> mapJSONArrayToPostResponse(JSONArray jsonArray){
        return StreamSupport.stream(jsonArray.spliterator(), false).map( m -> {
            try{
                return mapper.readValue(m.toString(), RestPostResponse.class);
            }catch (JsonProcessingException e){
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public Post restPostResponseToPost(RestPostResponse dto){
        return Post.builder()
                .userId(dto.getUserId())
                .id(dto.getId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }
    public List<Post> restPostResponseToPost(List<RestPostResponse> restPostResponses){
        return restPostResponses.stream().map(this::restPostResponseToPost).collect(Collectors.toList());
    }
}
