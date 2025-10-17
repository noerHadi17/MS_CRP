package com.wms.crp.i18n;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum I18nMessageCollection {
    QUESTION_LIST_RETRIEVED(HttpStatus.OK.value(), "QUESTION_LIST_RETRIEVED"),
    QUESTIONNAIRE_PREVIEW_SUCCESS(HttpStatus.OK.value(), "QUESTIONNAIRE_PREVIEW_SUCCESS"),
    QUESTIONNAIRE_SUBMIT_SUCCESS(HttpStatus.OK.value(), "QUESTIONNAIRE_SUBMIT_SUCCESS"),
    QUESTIONNAIRE_INCOMPLETE(HttpStatus.BAD_REQUEST.value(), "QUESTIONNAIRE_INCOMPLETE"),
    ANSWER_INVALID(HttpStatus.BAD_REQUEST.value(), "ANSWER_INVALID"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_ERROR");

    private final Integer statusCode;
    private final String i18nMessage;
    public String localized(MessageSource ms, java.util.Locale locale){
        return ms.getMessage(i18nMessage, null, locale);
    }
}

