<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            text-align: center;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        li a {
            display: block;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        li a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1><%= "Alex Phillips Java Project " %></h1>
    <ul>
        <li><a href="addBook.jsp">Add a new book</a></li>
        <li><a href="addAuthor.jsp">Add an author</a></li>
        <li><a href="updateAuthor.jsp">Update an Author</a></li>
        <li><a href="updateBook.jsp">Update a book</a></li>
        <li><a href="ProjectServlet?view=books">View books</a></li>
        <li><a href="ProjectServlet?view=authors">View authors</a></li>
        <li><a href="deleteAuthor.jsp">Delete Author</a></li>
        <li><a href="deleteBook.jsp">Delete Book</a></li>
    </ul>
</div>
</body>
</html>
