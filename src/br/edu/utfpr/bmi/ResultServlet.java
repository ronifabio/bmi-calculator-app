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
 * Servlet implementation class ResultServlet
 */
@WebServlet({ "/resultado", "/result" })
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Boolean isEnglish = request.getParameter("is-english") != null && request.getParameter("is-english").equals("on");
				
		float bmi = (float)request.getAttribute("bmi");
		//include
		response.setContentType("text/html; charset=utf-8");
		
		if(isEnglish) {
			request.getRequestDispatcher("/header-en.html").include(request,response);		
			out.printf("<br>BMI: %08.2f", bmi);
			out.print("<br>Category: " + getCategory(bmi, isEnglish));
		}
		else {
			request.getRequestDispatcher("/header-pt.html").include(request,response);		
			out.printf("<br>IMC: %08.2f", bmi);
			out.print("<br>Categoria: " + getCategory(bmi, isEnglish));
		}
		
	}
	
	/**
	 * 
	 * Retorna a categoria em relação ao peso
	 * @param bmi
	 * @return
	 */
	private String getCategory(float bmi, boolean isEnglish) {
		if(isEnglish) {
			if(bmi < BMIConstants.BELOW_WEIGHT) {
				return "Under weight";
			}
			else if(bmi < BMIConstants.NORMAL_WEIGHT) {
				return "Normal weight";
			}
			else if(bmi < BMIConstants.ABOVE_WEIGHT) {
				return "Overweight";
			}
			else {
				return "Obese";
			}
		}
		else {
			if(bmi < BMIConstants.BELOW_WEIGHT) {
				return "Abaixo do peso";
			}
			else if(bmi < BMIConstants.NORMAL_WEIGHT) {
				return "Peso normal";
			}
			else if(bmi < BMIConstants.ABOVE_WEIGHT) {
				return "Acima do peso";
			}
			else {
				return "Obeso";
			}
		}
	}

}
