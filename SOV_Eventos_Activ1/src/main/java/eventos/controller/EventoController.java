package eventos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.javabeans.Evento;
import lombok.RequiredArgsConstructor;
import net.unir.clientes.modelo.javabean.Cliente;



@Controller
@RequiredArgsConstructor
@RequestMapping("/eventos")
public class EventoController {

	
	private final EventoDao edao;

	
	//ALTA
	@GetMapping("/alta")
	public String mostrarAlta() {
		return "alta";
	}
	
	
	@PostMapping("/alta")
	public String procAlta(Evento evento) {
		if ((edao.insert(evento) == 1)) { 
			System.out.println(evento);
		}return "redirect:/";
	}
	
	
	//ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") int idEvento, Model model) {
		
		if (edao.delete(idEvento) == 1)
			model.addAttribute("mensaje", "Evento eliminado");
		return "forward:/";	
	}
	
	
	//VER DETALLES
	@GetMapping("/verDetalle/{id}")
	public String verEvento(@PathVariable("id") int idEvento , Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "verDetalle";
		}
		else {
			return "forward:/";
		}	
	}
	
	
	
	//CANCELAR
	@GetMapping("/cancelar/{id}")
	public String cancelarEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		evento.setEstado("Cancelado");
		edao.updateOne(evento);
		return "redirect:/";
	}
	
	
	

	//Formato fechas
	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	
	
	
}
