package http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostDTO {
    @JsonProperty(value="userId")
    private long userID;
    private long id;
    private String title;
    private String body;
}
