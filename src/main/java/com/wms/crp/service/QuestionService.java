package com.wms.crp.service;

import com.wms.crp.dto.CrpDtos.*;
import com.wms.crp.entity.MstAnswer;
import com.wms.crp.entity.MstQuestionnaire;
import com.wms.crp.repository.MstAnswerRepository;
import com.wms.crp.repository.MstQuestionnaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final MstQuestionnaireRepository questionnaireRepository;
    private final MstAnswerRepository answerRepository;

    public QuestionListResponse getActive() {
        List<MstQuestionnaire> qs = questionnaireRepository.findAllByIsActiveTrueOrderBySeqAsc();
        List<UUID> qIds = qs.stream().map(MstQuestionnaire::getQuestionId).collect(Collectors.toList());
        List<MstAnswer> answers = qIds.isEmpty()? List.of(): answerRepository.findAllByIdQuestionIn(qIds);
        Map<UUID, List<MstAnswer>> byQ = answers.stream().sorted(Comparator.comparing(MstAnswer::getSeq)).collect(Collectors.groupingBy(MstAnswer::getIdQuestion));
        List<QuestionItem> items = qs.stream().map(q -> QuestionItem.builder()
                .questionId(q.getQuestionId())
                .questionText(q.getQuestionText())
                .seq(q.getSeq())
                .answers(byQ.getOrDefault(q.getQuestionId(), List.of()).stream()
                        .map(a -> AnswerItem.builder().answerId(a.getAnswerId()).answerText(a.getAnswerText()).seq(a.getSeq()).build())
                        .collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
        return QuestionListResponse.builder().items(items).build();
    }
}

