package co.com.astropay.consumer.blog;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestBlogConsumer {

    private final OkHttpClient client;


}
