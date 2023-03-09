<%-- 
    Document   : loginfailed
    Created on : Feb 26, 2023, 7:46:14 PM
    Author     : Doan Ngoc Vu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Failed</h1>
        <form action="Loginfailed" method="POST">
        </form>
        <h1 style="font-family: 'ＭＳ Ｐゴシック', 'MS PGothic', 'メイリオ', Meiryo, sans-serif; font-size: 30px; color:red">
            YOUR ログイン INFORMATION IS WRONG, PLEASE CHECK AGAIN ユーザー名 AND パスワード
        </h1>
        You will be directed to /login after <span id="time"></span> seconds
        <script> 
        var count =5;
        var time = document.getElementById('time');
        time.innerHTML = count;
        function counting()
        {
            count --;
            time.innerHTML = count;
            if(count <= 0 )
            {
                window.location.href = 'login';
            }
        }
        setInterval(counting,1000);
        
        </script>
    </body>
</html>
