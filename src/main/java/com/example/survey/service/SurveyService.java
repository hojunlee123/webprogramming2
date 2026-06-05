package com.example.survey.service;

import com.example.survey.dao.SurveyDAO;
import com.example.survey.model.FruitOption;

import java.sql.SQLException;
import java.util.List;

/**
 * 설문 기능의 비즈니스 로직을 담당하는 서비스 클래스.
 * 컨트롤러와 DAO 사이에서 처리 흐름을 분리해 MVC 구조를 더 명확하게 만든다.
 */
public class SurveyService {

    private final SurveyDAO surveyDAO;

    public SurveyService() {
        this.surveyDAO = new SurveyDAO();
    }

    /**
     * 투표 화면에 보여줄 과일 목록을 반환한다.
     */
    public List<FruitOption> getOptionsForVote() throws SQLException, ClassNotFoundException {
        return surveyDAO.findAllByVoteRateDesc();
    }

    /**
     * 사용자가 선택한 과일에 한 표를 추가한다.
     */
    public void vote(int fruitId) throws SQLException, ClassNotFoundException {
        surveyDAO.increaseVote(fruitId);
    }

    /**
     * 결과 화면에 표시할 정렬된 목록을 반환한다.
     */
    public List<FruitOption> getOptionsForResult() throws SQLException, ClassNotFoundException {
        return surveyDAO.findAllByVoteRateDesc();
    }

    /**
     * 전체 투표 수를 반환한다.
     */
    public int getTotalVotes() throws SQLException, ClassNotFoundException {
        return surveyDAO.getTotalVotes();
    }

    /**
     * 사용자가 선택한 과일의 상세 정보를 조회한다.
     */
    public FruitOption getSelectedOption(int fruitId) throws SQLException, ClassNotFoundException {
        return surveyDAO.findById(fruitId);
    }
}
