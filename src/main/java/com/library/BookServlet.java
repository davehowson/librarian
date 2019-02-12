package com.library;

import com.library.data.DBConnectionManager;
import com.library.data.GoodreadsReader;
import com.library.model.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(
        name = "BookServlet",
        urlPatterns = "/book"
)
public class BookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbnStr = request.getParameter("isbn");
        long isbn = Long.parseLong(isbnStr);

        String url = getServletContext().getInitParameter("dbUrl");
        String user = getServletContext().getInitParameter("dbUser");
        String pass = getServletContext().getInitParameter("dbPass");

        Connection conn;
        Book book = new Book();

        Book bookGR = null;

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

        try{
            String key = getServletContext().getInitParameter("grDevKey");
            String urlString = String.format("https://www.goodreads.com/book/isbn/%1$s?key=%2$s", isbnStr, key);
            URL url1 = new URL(urlString);
            URLConnection cn = url1.openConnection();

            GoodreadsReader reader = new GoodreadsReader();
            bookGR = reader.parseXML(cn);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/book.jsp");
        request.setAttribute("book", book);
        request.setAttribute("bookGR", bookGR);
        dispatcher.forward(request, response);
    }
}
