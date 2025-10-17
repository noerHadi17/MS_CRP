package com.wms.crp.kafka;

import lombok.*;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AuditEvent {
    private UUID eventId;
    private String occurredAt;
    private String serviceName;
    private String action;
    private String status;
    private UUID customerId;
    private String email;
    private String description;
}

