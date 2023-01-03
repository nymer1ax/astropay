package co.com.astropay.consumer.post;

import co.com.astropay.consumer.RestConsumerURL;
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

    public List<RestPostResponse> findAllPost() throws IOException {
        HttpUrl httpUrl = restConsumerURL.generateUrl().newBuilder().addPathSegment("posts").build();

        Request request = restConsumerURL.generateRequest(httpUrl).newBuilder().get().build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();

        JSONArray jsonArray = new JSONArray(jsonData);

        return null;
    }


}
