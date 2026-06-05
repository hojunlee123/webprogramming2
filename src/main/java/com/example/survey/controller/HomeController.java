package com.example.survey.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 첫 화면으로 이동시키는 컨트롤러.
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 홈 화면 JSP로 요청을 전달한다.
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}
