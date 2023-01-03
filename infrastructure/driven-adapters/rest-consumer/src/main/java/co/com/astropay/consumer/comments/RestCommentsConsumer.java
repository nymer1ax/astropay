package co.com.astropay.consumer.comments;

import co.com.astropay.consumer.RestConsumerURL;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestCommentsConsumer {

    private final OkHttpClient client;

    private final RestConsumerURL restConsumerURL;

    private final RestCommentsMapper restCommentsMapper;

    public List<RestCommentsResponse> getCommentByPostId(int postId) throws IOException {

        HttpUrl httpUrl = restConsumerURL.generateUrl().newBuilder().addPathSegment("posts").addPathSegment(String.valueOf(postId)).addPathSegment("comments").build();

        Request request = restConsumerURL.generateRequest(httpUrl).newBuilder().get().build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();

        JSONArray jsonArray = new JSONArray(jsonData);

        return restCommentsMapper.mapJSONArrayToCommentsResponse(jsonArray);

    }


}
