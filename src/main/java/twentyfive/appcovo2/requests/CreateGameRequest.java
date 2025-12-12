package twentyfive.appcovo2.requests;

import lombok.Data;

import java.util.List;

@Data
public class CreateGameRequest {
    private String name;
    private String image;
    private List<String> formats;
}
