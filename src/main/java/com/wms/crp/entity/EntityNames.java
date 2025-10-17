package com.wms.crp.entity;

public final class EntityNames {
    private EntityNames() {}

    public static final String MST_RISKPROFILES = "mst_riskprofiles";
    public static final String MST_QUESTIONNAIRES = "mst_questionnaires";
    public static final String MST_ANSWERS = "mst_answers";
    public static final String CUSTOMER_ANSWERS = "customer_answers";

    public static final class MstRiskProfile {
        private MstRiskProfile() {}
        public static final String RISK_PROFILE_ID = "risk_profile_id";
        public static final String PROFILE_TYPE = "profile_type";
        public static final String SCORE_MIN = "score_min";
        public static final String SCORE_MAX = "score_max";
    }

    public static final class MstQuestionnaire {
        private MstQuestionnaire() {}
        public static final String QUESTION_ID = "question_id";
        public static final String QUESTION_TEXT = "question_text";
        public static final String SEQ = "seq";
        public static final String IS_ACTIVE = "is_active";
    }

    public static final class MstAnswer {
        private MstAnswer() {}
        public static final String ANSWER_ID = "answer_id";
        public static final String ID_QUESTION = "id_question";
        public static final String ANSWER_TEXT = "answer_text";
        public static final String SCORE_VALUE = "score_value";
        public static final String SEQ = "seq";
    }

    public static final class CustomerAnswer {
        private CustomerAnswer() {}
        public static final String CUSTOMER_ANSWER_ID = "customer_answer_id";
        public static final String ID_CUSTOMER = "id_customer";
        public static final String ID_QUESTION = "id_question";
        public static final String ID_ANSWER = "id_answer";
    }

    // for cross-service fallback update when REST call to customer fails
    public static final String MST_CUSTOMER = "mst_customer";
    public static final class MstCustomer {
        private MstCustomer() {}
        public static final String CUSTOMER_ID = "customer_id";
        public static final String ID_RISK_PROFILE = "id_risk_profile";
    }
}
