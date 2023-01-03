package co.com.astropay.consumer.comments;

import co.com.astropay.consumer.post.RestPostResponse;
import co.com.astropay.model.comments.Comments;
import co.com.astropay.model.post.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@RequiredArgsConstructor
@Component
public class RestCommentsMapper {
    private final ObjectMapper mapper;
    public List<RestCommentsResponse> mapJSONArrayToCommentsResponse(JSONArray jsonArray){
        return StreamSupport.stream(jsonArray.spliterator(), false).map(m -> {
            try{
                return mapper.readValue(m.toString(), RestCommentsResponse.class);
            }catch (JsonProcessingException e){
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public RestCommentsResponse mapJsonObjectToCommentsResponse(JSONObject jsonpObject) throws JsonProcessingException {
        return mapper.readValue(jsonpObject.toString(), RestCommentsResponse.class);
    }

    public Comments restCommentsResponseToComments(RestCommentsResponse dto){
        return Comments.builder()
                .postId(dto.getPostId())
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .body(dto.getBody())
                .build();
    }
    public List<Comments> restCommentResponseToCommmentsList(List<RestCommentsResponse> restPostResponses){
        return restPostResponses.stream().map(this::restCommentsResponseToComments).collect(Collectors.toList());
    }
}
