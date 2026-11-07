package modelos;

import java.util.ArrayList;
import java.util.List;

public class Testadora {
	public static void main(String[] args) {
		//Categoria test = new Categoria("Testando", "Testando novamente");
		CategoriaDAO dao = new CategoriaDAO();
		//dao.salvar(test);
		Categoria cat = dao.buscarPorCod(1);
		List<Categoria> cats = new ArrayList<Categoria>();
		cats = dao.listarCategorias();
		for (Categoria categoria : cats) {
			System.out.println(categoria.getNome_categoria());
		}
	}
}
