package http;

import com.fasterxml.jackson.databind.ObjectMapper;
import http.dto.PostDTO;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class PostClientService {

    private static PostClientService instance;
    private static HttpClient httpClient;
    private static ObjectMapper objectMapper;

    public static PostClientService getInstance() {
        if (instance == null) {
            synchronized (PostClientService.class) {
                if (instance == null) {
                    instance = new PostClientService();
                    httpClient = HttpClientBuilder.create().build();
                    objectMapper = new ObjectMapper();
                }
            }
        }
        return instance;
    }

    public List<PostDTO> getPosts() {
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts");
        httpGet.addHeader("Content-Type", "application/json");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            System.out.println("Response Code: " + httpResponse.getStatusLine().getStatusCode());
            String json = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8.name());
            List<PostDTO> posts =
                    objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, PostDTO.class));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpResponse);
        }
        return Collections.emptyList();
    }

    public PostDTO getSinglePost(long id) {
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts/" + id);
        httpGet.addHeader("Content-Type", "application/json");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            System.out.println("Response Code: " + httpResponse.getStatusLine().getStatusCode());
            String json = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8.name());
            return objectMapper.readValue(json, PostDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpResponse);
        }
        return null;
    }

}
