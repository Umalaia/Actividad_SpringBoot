package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import eventos.modelo.javabeans.Tipo;

@Repository
public class TipoDaoImplList implements TipoDao{

	private List<Tipo> lista;

	public TipoDaoImplList() {
		lista = new ArrayList<>();
		cargarLista();
	}
	
		
	
	private void cargarLista() {
		lista.add(new Tipo(1,"Boda","Enlace matrimonial"));
		lista.add(new Tipo(2,"Cumpleaños", "Fiesta de cumpleaños"));
		lista.add(new Tipo(3, "Despedida", "Despedida de soltero/a"));
		lista.add(new Tipo(4,"Concierto", "Conciertos de música"));
	}




	@Override
	public List<Tipo> findAll() {
		return lista;
	}

	

	@Override
	public Tipo findById(int idTipo) {
		for(int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdTipo() == idTipo)
				return lista.get(i);
		}
		return null;
	}

}
