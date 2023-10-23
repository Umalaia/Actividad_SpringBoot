package eventos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.javabeans.Evento;

@Controller
public class HomeController {
	
	@Autowired
	private EventoDao edao;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Evento> evActivo = new ArrayList<>();
		
		for(Evento evento: edao.findAll()) {
			if(evento.getEstado()=="Activo")
				evActivo.add(evento);
			else
				System.out.println(evento);
		}
		model.addAttribute("eventosActivos", evActivo);
		return "index";
	}
	

}
