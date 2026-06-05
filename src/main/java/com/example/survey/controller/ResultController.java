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
 * 투표 결과 화면에 필요한 데이터를 준비하는 컨트롤러.
 */
@WebServlet("/result")
public class ResultController extends HttpServlet {

    // 컨트롤러는 결과 데이터 조합을 서비스에 맡긴다.
    private final SurveyService surveyService = new SurveyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<FruitOption> options = surveyService.getOptionsForResult();
            int totalVotes = surveyService.getTotalVotes();

            request.setAttribute("options", options);
            request.setAttribute("totalVotes", totalVotes);

            String fruitIdParam = request.getParameter("fruitId");
            if (fruitIdParam != null && !fruitIdParam.isBlank()) {
                FruitOption selectedOption = surveyService.getSelectedOption(Integer.parseInt(fruitIdParam));
                request.setAttribute("selectedOption", selectedOption);
            }

            request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("결과 페이지를 불러오는 중 오류가 발생했습니다.", e);
        }
    }
}
