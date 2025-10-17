package com.wms.crp.i18n;

public enum RiskI18n {
    CONSERVATIVE("RISK_INSIGHT_CONSERVATIVE"),
    MODERATE("RISK_INSIGHT_MODERATE"),
    AGGRESSIVE("RISK_INSIGHT_AGGRESSIVE");

    private final String key;
    RiskI18n(String key){ this.key = key; }
    public String key(){ return key; }

    public static RiskI18n fromProfileType(String type) {
        if (type == null) return MODERATE;
        String t = type.trim().toUpperCase();
        if (t.startsWith("CONS")) return CONSERVATIVE;
        if (t.startsWith("AGGR")) return AGGRESSIVE;
        return MODERATE;
    }
}

