<%-- 
    Document   : adivinanumero
    Created on : 13/10/2015, 11:14:48 PM
    Author     : Ignacio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel='stylesheet' type='text/css' href='estilo.css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto:500' rel='stylesheet' type='text/css'>
    <title>Adivina el número</title>
     
    <%
        String user = null;
        String aleatorio = null;
        String mensaje = "";
        String numerosUtilizados = "";
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) user = cookie.getValue();
                if(cookie.getName().equals("aleatorio")) aleatorio = cookie.getValue();
                if(cookie.getName().equals("mensaje")) mensaje = cookie.getValue();
                if(cookie.getName().equals("numerosUtilizados")) numerosUtilizados = cookie.getValue();
            }
        }
        if(user == null) response.sendRedirect("index.html");

        int numeroAdivinar = Integer.parseInt(aleatorio);
    %>    
        
    </head>
    <body>  
        <h1>Juego de adivinar el número</h1>
        <i>Estoy pensando un número del 1 al 100, intenta adivinarlo</i>
        <br>

        <%= mensaje %>
         
        <form action="Sesion" method="POST"> 
            <fieldset>
                <legend> Introduce otro número:</legend>
                <%= numerosUtilizados %><br>
                <input type="text" name="numero">
                <br><br>
                <input type="submit" value="Enviar número">
                <input type="hidden" name="nuevo" value="no">
                <input type="hidden" name="aleatorio" value="<%=numeroAdivinar%>">
                <input type="hidden" name="numerosUtilizados" value="<%=numerosUtilizados%>">
            </fieldset>
        </form>
        <br><br>
        <a href="index.html">Volver a empezar</a>
        <br><br>
        <p>Nacho Betancourt, 131015</p>
    </body>
</html>
