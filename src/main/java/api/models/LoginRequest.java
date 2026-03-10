package api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String messageID;
    private String requestType;
    private RequestObject requestObject;

    @Data
    @Builder
    public static class RequestObject {
        private String identifier;
        private String password;
        private String applicationUrl;
    }
}
