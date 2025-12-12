package twentyfive.appcovo2.requests;

import lombok.Data;

import java.util.List;

@Data
public class CreateMoreFormatsRequest {
    private List<String> formats;
}
