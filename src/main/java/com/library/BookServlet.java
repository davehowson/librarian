package com.library;

import com.library.data.DBConnectionManager;
import com.library.model.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(
        name = "BookServlet",
        urlPatterns = "/book"
)
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbnStr = request.getParameter("isbn");
        long isbn = Long.parseLong(isbnStr);

        String url = getServletContext().getInitParameter("dbUrl");
        String user = getServletContext().getInitParameter("dbUser");
        String pass = getServletContext().getInitParameter("dbPass");

        Connection conn;
        Book book = new Book();

        try {
            DBConnectionManager connectionManager = new DBConnectionManager(url, user, pass);
            conn = connectionManager.getConnection();
            try {
                QueryRunner runner = new QueryRunner();
                ResultSetHandler<Book> resultSetHandler = new BeanHandler<>(Book.class);
                book = runner.query(conn, "SELECT * FROM books WHERE isbn=?", resultSetHandler, isbn);
            }  catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionManager.closeDB();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/book.jsp");
        request.setAttribute("book", book);
        dispatcher.forward(request, response);
    }
}
