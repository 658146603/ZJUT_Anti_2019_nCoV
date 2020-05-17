package servlet;

import conn.DatabaseProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = null, password = null;
        Connection conn = DatabaseProvider.getConn();
        if (request.getParameter("user") != null && request.getParameter("user").equals("admin")) {
            if (request.getParameter("id") != null) {
                id = request.getParameter("id");
                response.getWriter().println(id);
            }
            if (request.getParameter("password") != null) {
                password = request.getParameter("password");
                response.getWriter().println(password);

            }
            PreparedStatement sql = null;
            try {
                sql = conn.prepareStatement("select * from admin where id=? and password=?");
                sql.setString(1, id);
                sql.setString(2, password);
                ResultSet result = sql.executeQuery();
                result.next();
                response.getWriter().write(result.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            response.getWriter().write("OK");

        } else {

        }
    }
}
