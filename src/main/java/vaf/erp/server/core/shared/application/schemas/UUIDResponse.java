package vaf.erp.server.core.shared.application.schemas;

import java.util.UUID;

public record UUIDResponse(UUID id) {
    public static UUIDResponse of(UUID id) {
        return new UUIDResponse(id);
    }
}
