package com.meiken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author glf
 * @Date 2021/1/15
 */
@WebServlet(urlPatterns = "/")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>Hello, World!</h1>");
        writer.flush();
    }
}
