package com.example.survey.controller;

import com.example.survey.model.FruitOption;
import com.example.survey.service.SurveyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 투표 입력 화면 요청과 투표 저장 요청을 처리하는 컨트롤러.
 */
@WebServlet("/vote")
public class VoteController extends HttpServlet {

    // 컨트롤러는 서비스에 요청을 위임하고, 뷰 연결에 집중한다.
    private final SurveyService surveyService = new SurveyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<FruitOption> options = surveyService.getOptionsForVote();
            request.setAttribute("options", options);
            request.getRequestDispatcher("/WEB-INF/views/vote.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("투표 페이지를 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int fruitId = Integer.parseInt(request.getParameter("fruitId"));

            // 비즈니스 로직은 서비스 계층에서 처리한다.
            surveyService.vote(fruitId);

            response.sendRedirect(request.getContextPath() + "/result?fruitId=" + fruitId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("투표 저장 중 오류가 발생했습니다.", e);
        }
    }
}
