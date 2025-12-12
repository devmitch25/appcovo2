package twentyfive.appcovo2.dtos;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GameDto {
    private Long id;
    private String name;
    private String image;
    private List<FormatDto> gameFormats;
}
