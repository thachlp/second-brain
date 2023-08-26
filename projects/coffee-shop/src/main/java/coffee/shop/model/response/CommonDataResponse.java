package coffee.shop.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonDataResponse {
    private Object data;
}
