package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import modelo.Produto;

@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private List<Object> lista = new ArrayList<Object>();
	private List<Object> listares = new ArrayList<Object>();
	private JsonHelper jsonHelper = new JsonHelper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//capturar o que vem do produto
		String descricao = req.getParameter("descricao");
		Integer id = Integer.parseInt(req.getParameter("id"));
		Double valor = Double.parseDouble(req.getParameter("valor"));
		
		Produto produto01 = new Produto(id, descricao, valor);
		
		lista.add(produto01);
		
		try {
			resp.getWriter().println(jsonHelper.gerarJson(produto01));
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
//		resp.getWriter().println("{ Descricao do produto:" + produto01.getDescricao() +
//				"  , codigo ID: " + produto01.getId() + " , valor: R$"+produto01.getValor()+"} " );
		}
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//somar
		if(req.getParameter("op").equals("soma")){
			double total = 0;
			for(int i = 0; i < lista.size(); i++){
				total += ((Produto) lista.get(i)).getValor();
			}
			resp.getWriter().println("{ Soma R$"+total+" }");
		}
		else{
		String json = "[";
		for (int i = 0; i < lista.size(); i++) {
			
			//lendo lista com características do produto
			json += "{ Descricao: " +((Produto) lista.get(i)).getDescricao() + " , codigo ID: " +
			((Produto) lista.get(i)).getId() +" , valor R$"+ ((Produto) lista.get(i)).getValor()+" }";
			if (i < lista.size()-1)
				json += ",";
		}
		json += "]";

		resp.getWriter().print(json);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer i =Integer.parseInt(req.getParameter("i"));
		
		// Capturando dados a serem alterados
		Integer id = Integer.parseInt(req.getParameter("id"));
		String descricao = req.getParameter("descricao");
		Double valor = Double.parseDouble(req.getParameter("valor"));
		
		// Acessando o objeto e alterando os dados
		Produto produto01 = (Produto) lista.get(i);
		produto01.setId(id);
		produto01.setDescricao(descricao);
		produto01.setValor(valor);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//capturando o indice do objeto a ser excluido
			int id =Integer.parseInt(req.getParameter("id"));
		//removendo objeto do array
			
			for (int i = 0; i < lista.size(); i++) {
				if(id == ((Produto) lista.get(i)).getId()){
					lista.remove(i);
				}
			}
		
	}
	
}
