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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
			out.append("Digite um número válido");
			return;
		}
		
		//out.append("Peso: " + weight + " Altura: " + height);
		float bmi = calculateBMI(w,  h);
		out.printf("<br>IMC: %08.2f", bmi);
		out.print("<br>Categoria: " + getCategory(bmi));
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
	
	/**
	 * 
	 * Retorna a categoria em relação ao peso
	 * @param bmi
	 * @return
	 */
	private String getCategory(float bmi) {
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
