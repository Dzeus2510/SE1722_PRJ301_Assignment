
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Menu</title>
    </head>
    <body>
        <h1 style="font-family: 'ＭＳ Ｐゴシック', 'MS PGothic', 'メイリオ', Meiryo, sans-serif; font-size: 30px" >ログインメニュー </h1>
        <form action="login" method="POST">
            <label for="email">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Submit</button>
        </form>
    </body>
</html>
