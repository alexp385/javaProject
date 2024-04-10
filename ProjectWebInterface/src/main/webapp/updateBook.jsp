<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Existing Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        button[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        a {
            display: block;
            text-align: center;
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Update Existing Book</h1>
    <form action="ProjectServlet" method="post">
        <input type="hidden" name="action" value="updateBook" >
        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required>
        <label for="editionNumber">Edition Number:</label>
        <input type="text" id="editionNumber" name="editionNumber" required>
        <label for="copyRight">Copy Right:</label>
        <input type="text" id="copyRight" name="copyRight" required>
        <label for="authorID">Author ID:</label>
        <input type="text" id="authorID" name="authorID" required>
        <button type="submit">Update Book</button>
    </form>
    <a href="index.jsp">Back to Home</a>
</div>
</body>
</html>
