package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private boolean success;
    private String message;
    private ResponseObject responseObject;
    private int statusCode;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private int refresh_expires_in;
    private String id_token;
    private String privToken;
    private int totalCount;
    private List<ClientInfo> clientInfo;
    private int clientCount;
    private String orgId;
    private String username;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseObject {
        private String lastUnsuccessfulLoginAt;
        private String lastLoginAt;
        private int totalUnsuccessfulAttempts;
        private boolean isFirstLogin;
        private String lastPasswordChange;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClientInfo {
        private String clientId;
        private String applicationUrl;
        private boolean privFileUploaded;
    }
}