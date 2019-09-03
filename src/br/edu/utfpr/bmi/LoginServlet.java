package br.edu.utfpr.bmi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.bmi.util.Constants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(isLoggedIn(username, password)) {
			
			request.getSession().setAttribute(Constants.IS_LOGGED_IN, true);
			
			//cria o cookie
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Cookie c = new Cookie(Constants.DATE_LOGIN, sdf.format(now));
			response.addCookie(c);
			
			String address = "bmi-form.html";
			response.sendRedirect(address);
		}
		else {
			String address = "http://www.calculoimc.com.br";
			response.sendRedirect(address);
		}
		
	}
	
	private boolean isLoggedIn(String username, String password) {
		return (username.equals(getServletContext().getInitParameter("username")) &&
				password.equals(getServletContext().getInitParameter("password")));			
	}

}
