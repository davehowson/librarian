package com.library;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.library.data.DBConnectionManager;
import com.library.model.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(
        name = "searchServlet",
        urlPatterns = "/SearchServlet"
)
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String param = request.getParameter("param");

        String url = getServletContext().getInitParameter("dbUrl");
        String user = getServletContext().getInitParameter("dbUser");
        String pass = getServletContext().getInitParameter("dbPass");

        ArrayList<Book> books = new ArrayList<>();
        Connection conn;

        try {
            DBConnectionManager connectionManager = new DBConnectionManager(url, user, pass);
            conn = connectionManager.getConnection();
            try {
                BeanListHandler<Book> beanListHandler = new BeanListHandler<>(Book.class);
                QueryRunner runner = new QueryRunner();
                Object[] params = new Object[]{'%'+param+'%','%'+param+'%'};
                books = (ArrayList<Book>) runner.query(conn, "SELECT * FROM books WHERE title ILIKE ? OR author ILIKE ?", beanListHandler, params);
            }  catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionManager.closeDB();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (request.getParameter("isAjax").equalsIgnoreCase("false") ) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/result.jsp");
            request.setAttribute("bookList", books);
            dispatcher.forward(request, response);
        } else if (request.getParameter("isAjax").equalsIgnoreCase("true") ) {
            String json = new Gson().toJson(books);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }

    }
}
