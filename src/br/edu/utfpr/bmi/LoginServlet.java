package br.edu.utfpr.bmi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/login", initParams = {
		@WebInitParam(name="username-default", value="root"),
		@WebInitParam(name="password-default", value="qwerty")})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(isLoggedIn(username, password)) {
			String address = "/bmi-form.html";
			request.getRequestDispatcher(address).forward(request, response);
		}
		else {
			String address = "http://www.calculoimc.com.br";
			response.sendRedirect(address);
		}
		
	}
	
	private boolean isLoggedIn(String username, String password) {
		return (username.equals(getServletConfig().getInitParameter("username-default")) &&
				password.equals(getServletConfig().getInitParameter("password-default")));			
	}

}
