package co.com.astropay.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RestConsumerURL {

    @Value("${adapter.restconsumer.url}")
    private String base;

    public HttpUrl generateUrl(){
        return HttpUrl.parse(base);

    }

    public Request generateRequest(HttpUrl httpUrl){
        return new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url(httpUrl)
                .build();
    }

}
