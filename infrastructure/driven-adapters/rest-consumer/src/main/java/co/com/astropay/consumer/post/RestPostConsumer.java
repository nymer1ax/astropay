package co.com.astropay.consumer.post;

import co.com.astropay.consumer.RestConsumerURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestPostConsumer {

    private final OkHttpClient client;

    private final RestConsumerURL restConsumerURL;

    private final RestPostMapper restPostMapper;

    public List<RestPostResponse> findAllPost() throws IOException {
        HttpUrl httpUrl = restConsumerURL.generateUrl().newBuilder().addPathSegment("posts").build();

        Request request = restConsumerURL.generateRequest(httpUrl).newBuilder().get().build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();

        JSONArray jsonArray = new JSONArray(jsonData);

        return restPostMapper.mapJSONArrayToPostResponse(jsonArray);
    }

    public RestPostResponse findPostById(int id) throws IOException {

        HttpUrl httpUrl = restConsumerURL.generateUrl().newBuilder().addPathSegment("posts").addPathSegment(String.valueOf(id)).build();

        Request request = restConsumerURL.generateRequest(httpUrl).newBuilder().get().build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();

        JSONObject jsonpObject = new JSONObject(jsonData);

        return restPostMapper.mapJsonObjectToRestResponse(jsonpObject);
    }


}
