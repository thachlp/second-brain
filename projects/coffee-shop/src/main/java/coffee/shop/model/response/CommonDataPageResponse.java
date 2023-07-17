package coffee.shop.model.response;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CommonDataPageResponse {
    private Object data;
    private int totalPages;
    private long totalElements;
    private int page;
    private int size;
}
