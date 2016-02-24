

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Sesion")
public class Sesion extends HttpServlet {
    //private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        // get request parameters for userID and password
        String sesion = request.getParameter("nuevo");
        String numeroUsuario = request.getParameter("numero");
        int numeroServicio = (int)(Math.random()*(100)+1);
        String mensaje="";
        String cadenaNumeros= "";
        
        if(sesion.equals("si")){
            Cookie user = new Cookie("user",sesion);
            Cookie numero = new Cookie("numero",numeroUsuario);
            Cookie aleatorio = new Cookie("aleatorio",String.valueOf(numeroServicio));
            //setting cookie to expiry in 30 mins
            user.setMaxAge(60*60);
            numero.setMaxAge(60*60);
            aleatorio.setMaxAge(60*60);
            response.addCookie(user);
            response.addCookie(numero);
            response.addCookie(aleatorio);
            
            mensaje = "Intenta con otro número";
            
            if(numeroUsuario.equals(String.valueOf(numeroServicio))){
                mensaje = "Has ganado en el primer intento";
            }
            
            cadenaNumeros += "Numeros utilizados: " + 
                    request.getParameter("numerosUtilizados") + " " + numeroUsuario;
            
            Cookie numerosUtilizados = new Cookie("numerosUtilizados", cadenaNumeros);
            numerosUtilizados.setMaxAge(60*60);
            response.addCookie(numerosUtilizados);
            
            Cookie mensajeCookie = new Cookie("mensaje", mensaje);
            mensajeCookie.setMaxAge(60*60);
            response.addCookie(mensajeCookie);
            
            response.sendRedirect("adivinanumero.jsp");
        } 
        
        if(sesion.equals("no")){
            Cookie numero = new Cookie("numero", numeroUsuario);
            numero.setMaxAge(60*60);
            response.addCookie(numero);
            Cookie user = new Cookie("user",sesion);
            user.setMaxAge(60*60);
            response.addCookie(user);
            
            String numeroComparar = request.getParameter("aleatorio");
            int numeroAleatorio = Integer.valueOf(numeroComparar);
            int numeroNuevo = Integer.valueOf(numeroUsuario);
//mayor o menor
            
            if (numeroNuevo==numeroAleatorio){
                mensaje = "<br>Has adivinado mi número: " + numeroUsuario + "<br>";
            } if (numeroNuevo<numeroAleatorio){
                mensaje = "<br>Intenta con un número más alto<br>";
            } if (numeroNuevo>numeroAleatorio){
                mensaje = "<br>Intenta con un número más bajo<br>";
            }

            cadenaNumeros += request.getParameter("numerosUtilizados") + ", " + numeroUsuario;
            Cookie numerosUtilizados = new Cookie("numerosUtilizados", cadenaNumeros);
            numerosUtilizados.setMaxAge(60*60);
            response.addCookie(numerosUtilizados);
            Cookie mensajeCookie = new Cookie("mensaje", mensaje);
            mensajeCookie.setMaxAge(60*60);
            response.addCookie(mensajeCookie);
     
            response.sendRedirect("adivinanumero.jsp");
        }
    }
}