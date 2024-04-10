package com.example.projectwebinterface;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * ProjectServlet class
 * Servlet for the project
 * Handles all requests for the project
 * Contains methods to get, add, update and delete books and authors
 * Handles all api requests for books and authors
 * uses Unirest to make requests to the backend running on port 8080
 */
@WebServlet(name = "ProjectServlet", value = "/")
public class ProjectServlet extends HttpServlet {

    /**
     * doGet method
     * Handles get requests
     * displays the books or authors in a table based on the view parameter
     * detirmines which jsp page to display based on the view parameter
     * gets books or authors from the backend running on port 8080
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String view = request.getParameter("view");
        System.out.println("View: " + view);

        if (view != null) {
            if (view.equals("books")) {
                try{

                    Unirest.setTimeouts(0, 0);
                    HttpResponse<JsonNode> bodyResponse = Unirest.get("http://localhost:8080/books").asJson();

                    out.println("<html><head><title>Books</title><style>");
                    out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
                    out.println(".container { max-width: 800px; margin: 20px auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
                    out.println("h1 { color: #333; text-align: center; margin-bottom: 20px; }");
                    out.println("table { width: 100%; border-collapse: collapse; }");
                    out.println("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
                    out.println("tr:hover { background-color: #f2f2f2; }");
                    out.println("a { color: #007bff; text-decoration: none; }");
                    out.println("</style></head><body>");
                    out.println("<div class=\"container\">");
                    out.println("<h1>Books</h1>");
                    out.println("<table>");
                    out.println("<tr><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copy Right</th></tr>");
                    for (int i = 0; i < bodyResponse.getBody().getArray().length(); i++) {
                        out.println("<tr>");
                        out.println("<td><a href=\"http://localhost:8080/books/" + bodyResponse.getBody().getArray().getJSONObject(i).getString("isbn") + "\">" + bodyResponse.getBody().getArray().getJSONObject(i).getString("isbn") + "</a></td>");
                        out.println("<td>" + bodyResponse.getBody().getArray().getJSONObject(i).getString("title") + "</td>");
                        out.println("<td>" + bodyResponse.getBody().getArray().getJSONObject(i).getString("editionNumber") + "</td>");
                        out.println("<td>" + bodyResponse.getBody().getArray().getJSONObject(i).getString("copyRight") + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</div></body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (view.equals("authors")) {
                try{
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<JsonNode> bodyResponse = Unirest.get("http://localhost:8080/authors").asJson();

                    out.println("<html><head><title>Authors</title><style>");
                    out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
                    out.println(".container { max-width: 800px; margin: 20px auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
                    out.println("h1 { color: #333; text-align: center; margin-bottom: 20px; }");
                    out.println("table { width: 100%; border-collapse: collapse; }");
                    out.println("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
                    out.println("tr:hover { background-color: #f2f2f2; }");
                    out.println("a { color: #007bff; text-decoration: none; }");
                    out.println("</style></head><body>");
                    out.println("<div class=\"container\">");
                    out.println("<h1>Authors</h1>");
                    out.println("<table>");
                    out.println("<tr><th>Author ID</th><th>First Name</th><th>Last Name</th></tr>");
                    for (int i = 0; i < bodyResponse.getBody().getArray().length(); i++) {
                        out.println("<tr>");
                        out.println("<td><a href=\"http://localhost:8080/authors/" + bodyResponse.getBody().getArray().getJSONObject(i).getInt("authorID") + "\">" + bodyResponse.getBody().getArray().getJSONObject(i).getInt("authorID") + "</a></td>");
                        out.println("<td>" + bodyResponse.getBody().getArray().getJSONObject(i).getString("firstName") + "</td>");
                        out.println("<td>" + bodyResponse.getBody().getArray().getJSONObject(i).getString("lastName") + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</div></body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                out.println("<html><body>");
                out.println("<h1>Invalid view</h1>");
                out.println("</body></html>");
            }
        } else {
            out.println("<html><body>");
            out.println("<h1>Invalid view</h1>");
            out.println("</body></html>");
        }
    }


    /**
     * doPost method
     * Handles post requests
     * Adds a book or author to the database
     * this method also handles the update and delete requests for books and authors
     * uses Unirest to make requests to the backend running on port 8080
     * delete and put are called here due to the limitations of the Http form submissions
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        System.out.println("Action: " + action);

        if(action != null){
            if (action.equals("addBook")){
                String title = request.getParameter("title");
                String isbn = request.getParameter("ISBN");
                String copyRight = request.getParameter("copyRight");
                String editionNumber = request.getParameter("editionNumber");
                Integer authorID = Integer.parseInt(request.getParameter("authorID"));
                System.out.println("Title: " + title);
                System.out.println("ISBN: " + isbn);
                System.out.println("Edition: " + editionNumber);
                System.out.println("Copyright: " + copyRight);
                System.out.println("Author ID: " + authorID);

                // convert book title to a string with no spaces
                title = title.replace(" ", "%20");


                try{
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<String> bodyResponce = Unirest.post("http://localhost:8080/books?isbn=" + isbn + "&title=" + title + "&copyRight=" + copyRight + "&editionNumber=" + editionNumber + "&authorId=" + authorID).asString();

                    out.println("<html><body>");
                    out.println("<h1>Book added successfully</h1>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (action.equals("addAuthor")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);

                try{
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<JsonNode> bodyResponce = Unirest.post("http://localhost:8080/authors?firstName=" + firstName + "&lastName=" + lastName).asJson();
                    out.println("<html><body>");
                    out.println("<h1>Author added successfully</h1>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if (action.equals("updateBook")){
                String title = request.getParameter("title");
                String isbn = request.getParameter("isbn");
                String copyRight = request.getParameter("copyRight");
                String editionNumber = request.getParameter("editionNumber");
                Integer authorID = Integer.parseInt(request.getParameter("authorID"));
                System.out.println("Title: " + title);
                System.out.println("ISBN: " + isbn);
                System.out.println("Edition: " + editionNumber);
                System.out.println("Copyright: " + copyRight);
                System.out.println("Author ID: " + authorID);

                // convert book title to a string with no spaces
                title = title.replace(" ", "%20");

                try{
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<String> bodyResponce = Unirest.put("http://localhost:8080/books/" + isbn + "?title=" + title + "&copyRight=" + copyRight + "&editionNumber=" + editionNumber + "&authorId=" + authorID).asString();

                    System.out.println("http://localhost:8080/books/" + isbn + "?title=" + title + "&copyRight=" + copyRight + "&editionNumber=" + editionNumber + "&authorId=" + authorID);
                    out.println("<html><body>");
                    out.println("<h1>Book updated successfully</h1>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (action.equals("updateAuthor")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                Integer authorID = Integer.parseInt(request.getParameter("authorID"));
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Author ID: " + authorID);

                try{
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<JsonNode> bodyResponce = Unirest.put("http://localhost:8080/authors/" + authorID +"?firstName=" + firstName + "&lastName=" + lastName).asJson();
                    out.println("<html><body>");
                    out.println("<h1>Author updated successfully</h1>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (action.equals("deleteAuthor")){
                Integer authorID = Integer.parseInt(request.getParameter("authorId"));
                System.out.println("Author ID: " + authorID);

                try{
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<String> bodyResponce = Unirest.delete("http://localhost:8080/authors/" + authorID).asString();
                    out.println("<html><body>");
                    out.println("<h1>Author deleted successfully</h1>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (action.equals("deleteBook")){
                String isbn = request.getParameter("isbn");
                System.out.println("ISBN: " + isbn);

                try{
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<String> bodyResponce = Unirest.delete("http://localhost:8080/books/" + isbn).asString();
                    out.println("<html><body>");
                    out.println("<h1>Book deleted successfully</h1>");
                    out.println("<a href=\"http://localhost:8081/ProjectWebInterface_war_exploded\">Back</a>");
                    out.println("</body></html>");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            out.println("<html><body>");
            out.println("<h1>Invalid action</h1>");
            out.println("</body></html>");
        }
    }

    /**
     * destroy method
     * called when the servlet is destroyed
     */
    public void destroy() {
    }

}
