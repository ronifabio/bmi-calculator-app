package br.edu.utfpr.bmi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.bmi.util.BMIConstants;

/**
 * Servlet implementation class BMIServlet
 */
@WebServlet("/calculadora")
public class BMIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");		
		
		//trocar virgula por ponto
		weight = weight.replaceAll(",", ".");
		height = height.replaceAll(",", ".");
		
		float w, h;
		try {
			w = Float.parseFloat(weight);
			h = Float.parseFloat(height);
		}
		catch (NumberFormatException e) {
//			String address = "/error.html";
//			request.getRequestDispatcher(address).forward(request, response);
			
			String address = "bmi-form.html";
			response.sendRedirect(address);
			return;
		}
		
		//redirecionamento
		if(h > 2) {
			String address = "https://www.google.com";
			response.sendRedirect(address);			
		}
		
		//out.append("Peso: " + weight + " Altura: " + height);
		float bmi = calculateBMI(w,  h);
		String address = "/resultado";
		request.setAttribute("bmi", bmi);		
 		request.getRequestDispatcher(address).forward(request, response); 
	}
	
	/**
	 * 
	 * Calcula o IMC 
	 * @param weight
	 * @param height
	 * @return
	 */
	private float calculateBMI(float weight, float height) {
		return weight/(height * height);		
	}
	
	

}
