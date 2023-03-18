<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Menu</title>
        <style>
            body {
                background-image: url('img/fpt.jpg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            h1 {
                font-size: 36px;
                color: #ffffff;
                text-align: center;
                margin-top: 100px;
                margin-bottom: 50px;
                letter-spacing: 2px;
                text-shadow: 2px 2px #000000;
            }

            form {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px #000000;
                width: 400px;
                margin: 0 auto;
            }

            label {
                font-size: 18px;
                color: #000000;
                display: block;
                margin-bottom: 10px;
            }

            input[type="text"], input[type="password"] {
                font-size: 16px;
                padding: 10px;
                border-radius: 5px;
                border: none;
                width: 100%;
                margin-bottom: 20px;
            }

            button[type="submit"] {
                font-size: 18px;
                color: #ffffff;
                background-color: #f8c300;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            button[type="submit"]:hover {
                background-color: #fcb900;
            }
        </style>
    </head>
    <body>
        <h1>FPT大学の ログインメニュー</h1>
        <form action="login" method="POST">
            <label for="username">ユーザー名:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">パスワード:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">ログイン</button>
        </form>
    </body>
</html>