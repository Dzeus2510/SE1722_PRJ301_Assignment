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
        <style>
            body {
                background-color: #f5f5f5;
                font-family: 'Roboto', sans-serif;
            }
            .container {
                width: 80%;
                max-width: 600px;
                margin: 0 auto;
                text-align: center;
            }
            h1 {
                font-size: 36px;
                margin-top: 50px;
                margin-bottom: 30px;
                color: #ff6600;
                font-weight: 500;
                text-transform: uppercase;
            }
            p {
                font-size: 18px;
                margin-top: 20px;
                margin-bottom: 20px;
                color: #666;
                line-height: 1.5;
            }
            span {
                font-size: 24px;
                color: #ff6600;
                font-weight: 500;
            }
            form {
                margin-top: 30px;
            }
            label {
                display: block;
                font-size: 18px;
                margin-bottom: 10px;
                color: #666;
                font-weight: 500;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <h1>Login Failed</h1>
        <form action="Loginfailed" method="POST">
        </form>
        <h1 style="font-family: 'ＭＳ Ｐゴシック', 'MS PGothic', 'メイリオ', Meiryo, sans-serif; font-size: 30px; color:red">            
            YOUR ログイン INFORMATION IS WRONG, PLEASE CHECK AGAIN ユーザー名 AND パスワード <br/>
            REMEMBER THAT YOU HAVE TO ログイン TO BE ABLE TO ACCESS TO THE WEBSITE CONTENTS
        </h1>
        You will be directed to the login page after <span id="time"></span> seconds
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
