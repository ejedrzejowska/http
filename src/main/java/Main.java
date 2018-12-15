import http.PostClientService;
import http.dto.PostDTO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PostClientService postClientService = PostClientService.getInstance();
        List<PostDTO> posts = postClientService.getPosts();
        for (PostDTO post : posts) {
            System.out.println(post.toString());
        }

        PostDTO postDTO = postClientService.getSinglePost(1);
        System.out.println(postDTO.toString());
    }
}
