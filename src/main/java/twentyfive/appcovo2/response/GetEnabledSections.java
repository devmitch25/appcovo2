package twentyfive.appcovo2.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEnabledSections {
    private String serviceCode;
    private Boolean enabled;
}
