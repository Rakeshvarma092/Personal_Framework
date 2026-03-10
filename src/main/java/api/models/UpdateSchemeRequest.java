package api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSchemeRequest {
    private RequestObject requestObject;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestObject {
        private String id;
        private String switchDestination;
        private String name;
        private String acquirerId;
        private String forwardingId;
        private String paymentFacilitatorId;
        private String groupSignOn;
        private String pinEncrytionKey;
        private String transportKEK;
        private Integer transportKEKIndex;
        private Boolean isSignOnAllowed;
        private Boolean isEchoAllowed;
        private Boolean isKeyXAllowed;
        private List<SchemeMessageType> schemeMessageType;
        private NetworkControls networkControls;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SchemeMessageType {
        private String txnAllow;
        private Boolean isSingle;
        private Boolean isDccAllowed;
        private String authMessageTypeEnum;
        private String mti;
        private String processingCode;
        private String txnSource;
        private Boolean isTxnAllowed;
        private Integer txnExpiry;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NetworkControls {
        private String participantId;
        private String acqBin;
        private String currency;
        private String processingId;
        private String fileCategory;
        private String acquirerReferanceData;
        private String originatingMessageFormat;
        private String acquirerDestinationId;
    }
}
