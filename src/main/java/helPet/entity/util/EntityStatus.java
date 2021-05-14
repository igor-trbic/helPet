package helPet.entity.util;

import helPet.constants.ApplicationConstants;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EntityStatus implements Serializable {

    ACTIVE      (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX),    // 100
    INACTIVE    (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  1),
    APPROVED    (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  2),
    PENDING     (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  3),
    SUSPENDED   (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  4),
    REJECTED    (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  5),
    TERMINATED  (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  6),
    STARTED     (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  7),
    COMPLETED   (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  8),
    EXPIRED     (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  9),
    DELETED     (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  10),
    FAILED      (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  11),
    READY       (ApplicationConstants.ENTITY_STATUS_ENUM_INDEX +  12);

    private static final Map<Integer, EntityStatus> statusMap = new HashMap<>();

    static {
        for (EntityStatus es : EnumSet.allOf(EntityStatus.class)) {
            statusMap.put(es.getCode(), es);
        }
    }

    private final Integer code;

    private EntityStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static EntityStatus get(Integer code) {
        return statusMap.get(code);
    }
}
