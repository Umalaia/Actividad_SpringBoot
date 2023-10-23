package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import eventos.modelo.javabeans.Evento;
import eventos.modelo.javabeans.Tipo;

@Repository
public class EventoDaoImplList implements EventoDao {
	
	public List<Evento> lista;
	private static int idAuto;
	
	static {
		idAuto = 0;
	}
	
	public EventoDaoImplList() {
		lista = new ArrayList<>();
		cargarLista();
	}

	private void cargarLista() {
		TipoDaoImplList tipoDao = new TipoDaoImplList();
		
		lista.add(new Evento(1, "Boda Castillo-Orejuela", "Celebración en salón El Coral", new Date() ,"Activo","S", 250, 70, 5980.00, tipoDao.findById(1) ));
		lista.add(new Evento(2, "Cumpleaños Miguel Gutierrez", "Cumpleaños infantil", new Date(), "Activo", "S", 40, 15, 700.50, tipoDao.findById(2)));
		lista.add(new Evento(3, "Despedida Marta Alamo", "Despedida en terraza/piscina", new Date(), "Cancelado", null, 45, 10, 900.00, tipoDao.findById(3)));
		lista.add(new Evento(4, "Concierto Luis Fonsi", "Concierto al aire libre", new Date(), "Activo", "S", 2000, 500, 150000.00, tipoDao.findById(4)));
		idAuto = 5;

	}


	@Override
	public Evento findById(int idEvento) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdEvento() == idEvento)
				return lista.get(i);
		}
		return null;
	}

	
	@Override
	public List<Evento> findAll() {
		return lista;
	}

	
	@Override
	public int insert(Evento evento) {
		if (!lista.contains(evento)) {
			evento.setIdEvento(idAuto++);
			lista.add(evento);
			
			return 1;
		}
		return 0;
	}

	
	@Override
	public int delete(int idEvento) {
		Evento evento = findById(idEvento);
		if (evento == null) 
			return 0;
		
		return lista.remove(evento) ? 1 : 0;
	}

	
	@Override
	public int updateOne(Evento evento) {
		int pos = lista.indexOf(evento);
		if (pos == -1)
			return 0;
		lista.set(pos, evento);
		return 1;
	}

}
