package com.SantiagoMartinez.TPF.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.SantiagoMartinez.TPF.Model.Aspecto;
import com.SantiagoMartinez.TPF.Model.Auto;
import com.SantiagoMartinez.TPF.Model.DaoAspecto;
import com.SantiagoMartinez.TPF.Model.DaoAuto;
import com.SantiagoMartinez.TPF.Model.DaoDuenio;
import com.SantiagoMartinez.TPF.Model.DaoOrden;
import com.SantiagoMartinez.TPF.Model.Repuesto;
import com.SantiagoMartinez.TPF.Model.Reparacion;
import com.SantiagoMartinez.TPF.Model.DaoReparacion;
import com.SantiagoMartinez.TPF.Model.DaoRepuesto;
import com.SantiagoMartinez.TPF.Model.DaoSession;
import com.SantiagoMartinez.TPF.Model.Duenio;
import com.SantiagoMartinez.TPF.Model.Orden;
import com.SantiagoMartinez.TPF.Model.Sesiones;



//session atributes

@Controller
@SessionAttributes("sesion")
public class MainController {
	
		//variables auxiliares, guardan Ids para poder hacer refresh correctos de las listas
		private long f;
		boolean falsee = false;
		boolean truee=true;
		private long g;
		Sesiones user1;
		private Duenio z;
		private Auto d;
		private Orden w;
		private Reparacion e;
		private long x;
		private Aspecto jk;
		private long refeDuenio;
		private long refeOrden;
		private long refeAuto;
		private long refeAspecto;
		double totalFinal = 0;
		@Autowired
		DaoSession daoSession;

		@Autowired
		DaoAuto daoAuto;
		@Autowired
		DaoDuenio daoDuenio;
		
		@Autowired
		DaoReparacion daoReparacion;
		
		@Autowired
		DaoRepuesto daoRepuesto;
		

		@Autowired
		DaoOrden daoOrden;
		
		@Autowired
		DaoAspecto daoAspecto;
	
	
		//ingreso usuario y creo session en el get
		
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView logearGet(Model model){
			
			if (!model.containsAttribute("sesion")) {
				model.addAttribute("sesion", 
						new ArrayList<Sesiones>());
			}	
		
			
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("sesiones", new Sesiones());
			
			modelAndView.setViewName("login");
			return modelAndView;
			
		}
		//comprueba si existe usuario y pass en la base. en caso afirmativo guarda session para usar como id en los formularios
		//en otros casos devuelve error. nota: si no hay usuarios en la base de datos, el post no hace nada. falta una validacion en ese caso.
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public ModelAndView logearPost(Model model,@ModelAttribute Sesiones sesiones1, @ModelAttribute("sesion") ArrayList<Sesiones> sesionAct){
			
			Sesiones sesiones5 = new Sesiones();
			ModelAndView modelAndView = new ModelAndView();
			for (long i = daoSession.count() - 1;i>=0;i--) {
			String mensaje;
			String user = sesiones1.getUsuario();
			String user2 = sesiones1.getContrasenia();
				if (daoSession.exists(user)) {
					Sesiones validacion = daoSession.findOne(user);
					if (validacion.getContrasenia().equals(user2)){
					sesiones5.setUsuario(user);
					mensaje = ("Logueo exitoso. Binvenido " + user);
					user1 = sesiones5;
					modelAndView.addObject("mensaje", mensaje);
					model.addAttribute("sesion",sesiones5);
					modelAndView.setViewName("exitoso");
					return modelAndView;
					}else {
						mensaje = ("Contraseña incorrecta");
						modelAndView.addObject("mensaje", mensaje);
						modelAndView.setViewName("volver");
						return modelAndView;
					}
														
				} else {
					mensaje = ("Usuario inexistente");
					modelAndView.addObject("mensaje", mensaje);
					modelAndView.setViewName("volver");
					return modelAndView;
				}
		
			
			}
	

		
			
			return modelAndView;
			
			
		
		
		}
		
		//crea usuario. seguridad 0, puede sobrescribir usuarios.
		@RequestMapping(value = "/crearr", 
		method = RequestMethod.GET)
public ModelAndView crearGet(){
	ModelAndView modelAndView = new ModelAndView();

	modelAndView.addObject("session2", new Sesiones());
	
	modelAndView.setViewName("crear");
	return modelAndView;
	
}

		@RequestMapping(value = "/crearr", 
				method = RequestMethod.POST)
		public ModelAndView crearPost(@ModelAttribute Sesiones sessionHTML){
			
			ModelAndView modelAndView = new ModelAndView();

		
			Sesiones sesiones = new Sesiones();
		
			sesiones.setContrasenia(sessionHTML.getContrasenia());
			sesiones.setUsuario(sessionHTML.getUsuario());
			daoSession.save(sesiones);
			modelAndView.addObject("session1", new Sesiones());
			modelAndView.setViewName("login");
			return modelAndView;
			
		}
		@RequestMapping(value = "/principal", method = RequestMethod.GET)
		public ModelAndView principalGet(){
			
			ModelAndView modelAndView = new ModelAndView();
			
			modelAndView.addObject("duenio", daoDuenio.findAll());
			modelAndView.setViewName("principal");
			
			
			return modelAndView;
		}
		//lista los clientes y los manda para listar
		@RequestMapping(value = "/principal", method = RequestMethod.POST)
		public ModelAndView principalListar(@RequestParam("idDuenio") long idDuenio,  ArrayList<Auto> 
		listaAutos2){
			
			ModelAndView modelAndView = new ModelAndView();
			
				
			modelAndView.addObject("duenio", daoDuenio.findAll());
			
			
			refeDuenio = idDuenio;
			modelAndView.addObject("autos",daoAuto.findAll());	
			modelAndView.addObject("idDuenio",idDuenio);	
			modelAndView.setViewName("listarClientes");
			
			return modelAndView;
		}
		//get y post para ingresar clientes
		@RequestMapping(value = "/ingresarCliente", method = RequestMethod.GET)
		public ModelAndView ingresarCliente(){
			
			ModelAndView modelAndView = new ModelAndView();
			
			modelAndView.addObject("duenio", new Duenio());	
			modelAndView.setViewName("ingresarCliente");
			
			return modelAndView;
		}
		
		@RequestMapping(value = "/ingresarCliente", method = RequestMethod.POST)
		
		public ModelAndView crearLibroPost(@ModelAttribute Duenio duenio){
		
			ModelAndView modelAndView = new ModelAndView();
			Duenio duenio2 = new Duenio();
			
			daoDuenio.save(duenio2);
			duenio2.setNombre(duenio.getNombre());
			duenio2.setApellido(duenio.getApellido());
			duenio2.setTelefono(duenio.getTelefono());
			duenio2.setDireccion(duenio.getDireccion());
			daoDuenio.save(duenio2);
							
			modelAndView.addObject("duenio", daoDuenio.findAll());
		
			modelAndView.setViewName("principal");
			
			return modelAndView;
		}
	//para borrar clietnes
		@RequestMapping(value = "/borrarCliente", 
				method = RequestMethod.GET)
		public ModelAndView paraBorrar(@RequestParam("refe") long refe ){
			ModelAndView modelAndView = new ModelAndView();
		
			for (long i = daoDuenio.count() - 1;i>=0;i--) {
			
			
				if (daoDuenio.exists(refe)) {
					z = daoDuenio.findOne(refe);
				
										
				}
				
			
			}
			long s = z.getIdDuenio();
			daoDuenio.delete(z);
			modelAndView.addObject("duenio", daoDuenio.findAll());
			modelAndView.addObject("autos", daoAuto.findAll());
			
			modelAndView.setViewName("principal");
					return modelAndView;
		}
		
		//para modificarlos
		@RequestMapping(value = "/modificarCliente", 
				method = RequestMethod.GET)
		public ModelAndView paraEditar(@RequestParam("refe") long refe){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				modelAndView.addObject("duenio",daoDuenio.findOne(refe));		
	
				x= refe;
				modelAndView.setViewName("modificarCliente");
				return modelAndView;
		}
		@RequestMapping(value = "/modificarCliente", 
				method = RequestMethod.POST)
		public ModelAndView paraEditarPost(@ModelAttribute Duenio duenio){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				daoDuenio.save(duenio);
							
				
				modelAndView.addObject("duenio", daoDuenio.findAll());
				modelAndView.addObject("autos", daoAuto.findAll());
				modelAndView.setViewName("principal");
				return modelAndView;
		}
		
		//borra autos
		@RequestMapping(value = "/borrarAuto", 
				method = RequestMethod.GET)
		public ModelAndView paraBorrarAuto(@RequestParam("refe1") long refe1, @RequestParam("refe2") long refe ){
			ModelAndView modelAndView = new ModelAndView();
		
			for (long i = daoAuto.count() - 1;i>=0;i--) {
			
			
				if (daoAuto.exists(refe)) {
					d = daoAuto.findOne(refe);
				
										
				}
				
			
			}
			
			long a = d.getIdAuto();
	
			daoAuto.delete(a);
			modelAndView.addObject("duenio", daoDuenio.findAll());
			modelAndView.addObject("autos", daoAuto.findAll());
			modelAndView.addObject("idDuenio1", refe1);
		
			modelAndView.setViewName("listarClientes");
					return modelAndView;
		}
		
		//modifica
		@RequestMapping(value = "/modificarAuto", 
				method = RequestMethod.GET)
		public ModelAndView paraEditarAuto(@RequestParam("refe") long refe, @RequestParam("refe1") long refe1){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				modelAndView.addObject("auto",daoAuto.findOne(refe));		
				modelAndView.addObject("duenio",daoDuenio.findOne(refe1));
				x= refe;
				f = refe1;
				modelAndView.setViewName("modificarAuto");
				return modelAndView;
		}
		@RequestMapping(value = "/modificarAuto", 
				method = RequestMethod.POST)
		public ModelAndView paraEditarAutoPost(@ModelAttribute Auto auto, @ModelAttribute Duenio duenio3){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				auto.setIdAuto(x);
				auto.getDuenio().setIdDuenio(f);
				daoAuto.save(auto);
				
				modelAndView.addObject("duenio", daoDuenio.findAll());
				modelAndView.addObject("autos", daoAuto.findAll());
				modelAndView.setViewName("principal");
				return modelAndView;
		}
		
		
		
		@RequestMapping(value = "/listarOrdenes", method = RequestMethod.GET)
		public ModelAndView listarOrdenes(@RequestParam("refe") long refe,  ArrayList<Auto> 
		listaAutos2){
			
			ModelAndView modelAndView = new ModelAndView();
			
		
				
	
		
			modelAndView.addObject("orden", daoOrden.findAll());
			refeAuto= refe;
			modelAndView.addObject("idAuto",refe);	
			modelAndView.addObject("aspectos",daoAspecto.findAll());	
			modelAndView.setViewName("listarOrdenes");
			
			return modelAndView;
		}
		
		@RequestMapping(value = "/borrarOrden", 
				method = RequestMethod.GET)
		public ModelAndView paraBorrarOrden(@RequestParam("refe1") long refe1,@RequestParam("refe2") long refe2 ){
			ModelAndView modelAndView = new ModelAndView();
		
			for (long i = daoOrden.count() - 1;i>=0;i--) {
			
			
				if (daoOrden.exists(refe2)) {
					w = daoOrden.findOne(refe2);
				
										
				}
				
			
			}
			
			long a = w.getIdOrden();
	
			daoOrden.delete(a);
			modelAndView.addObject("orden", daoOrden.findAll());
			modelAndView.addObject("aspectos", daoAspecto.findAll());
			modelAndView.addObject("idOrden", refe1);
		
			modelAndView.setViewName("listarOrdenes");
					return modelAndView;
		}
		@RequestMapping(value = "/modificarOrden", 
				method = RequestMethod.GET)
		public ModelAndView paraEditarOrden(@RequestParam("refe") long refe, @RequestParam("refe1") long refe1){
		
				
				ModelAndView modelAndView = new ModelAndView();
					Orden orden = daoOrden.findOne(refe);
					if (orden.isAbierta()==false){
						modelAndView.setViewName("errorr");
						return modelAndView;
					}
				

				modelAndView.addObject("orden",daoOrden.findOne(refe));		
				modelAndView.addObject("auto",daoAuto.findOne(refe1));
				x= refe;
				f = refe1;
				modelAndView.setViewName("modificarOrden");
				return modelAndView;
		}
		@RequestMapping(value = "/modificarOrden", 
				method = RequestMethod.POST)
		public ModelAndView paraEditarOrdenPost( @ModelAttribute Orden orden){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				orden.setIdOrden(x);
			
				orden.getAuto().setIdAuto(f);
				daoOrden.save(orden);
				
				modelAndView.addObject("orden", daoOrden.findAll());
				modelAndView.addObject("idAuto", f);
				
				modelAndView.addObject("auto", daoAuto.findAll());
				modelAndView.setViewName("listarOrdenes");
				return modelAndView;
		}
		

		@RequestMapping(value = "/listarRepuestos", method = RequestMethod.GET)
		public ModelAndView listarRepuestosGet(@RequestParam("refe") long refe){
			
			ModelAndView modelAndView = new ModelAndView();
			
		
				
	
			modelAndView.addObject("reparacion", daoReparacion.findAll());
			modelAndView.addObject("repuesto", daoRepuesto.findAll());
			modelAndView.addObject("orden", daoOrden.findAll());
			refeAspecto = refe;
			modelAndView.addObject("idAspecto",refe);	
			modelAndView.addObject("aspectos",daoAspecto.findAll());	
			modelAndView.setViewName("listarRepuestos");
			
			return modelAndView;
		}
		//aspecto es una tabla intermedia que guarda la cantidad de cierto repuesto y la descripcion de problemas individuales del auto.
		
		@RequestMapping(value = "/borrarAspecto", 
				method = RequestMethod.GET)
		public ModelAndView paraBorrarAspecto(@RequestParam("refe1") long refe1,@RequestParam("refe2") long refe2 ){
			ModelAndView modelAndView = new ModelAndView();
		
			for (long i = daoAspecto.count() - 1;i>=0;i--) {
			
			
				if (daoAspecto.exists(refe1)) {
					jk = daoAspecto.findOne(refe1);
				
										
				}
				
			
			}
			
			long a = jk.getIdAspecto();
	
			daoAspecto.delete(a);
			modelAndView.addObject("reparacion", daoReparacion.findAll());
			modelAndView.addObject("orden", daoOrden.findAll());
			modelAndView.addObject("repuesto", daoRepuesto.findAll());
			modelAndView.addObject("idAspecto", refe1);
			modelAndView.addObject("idOrden", refe2);
			modelAndView.addObject("idAuto", refeAuto);
		
			modelAndView.setViewName("listarOrdenes");
					return modelAndView;
		}
		@RequestMapping(value = "/borrarTarea", 
				method = RequestMethod.GET)
		public ModelAndView paraBorrarTarea(@RequestParam("refe1") long refe1,@RequestParam("refe2") long refe2 ){
			ModelAndView modelAndView = new ModelAndView();
		
			for (long i = daoReparacion.count() - 1;i>=0;i--) {
			
			
				if (daoReparacion.exists(refe2)) {
					e = daoReparacion.findOne(refe2);
				
										
				}
				
			
			}
			
			long a = e.getIdReparacion();
	
			daoReparacion.delete(a);
			modelAndView.addObject("reparacion", daoReparacion.findAll());
			modelAndView.addObject("repuesto", daoRepuesto.findAll());
			modelAndView.addObject("idAspecto", refe1);
		
		
			modelAndView.setViewName("listarRepuestos");
					return modelAndView;
		}
		@RequestMapping(value = "/modificarTarea", 
				method = RequestMethod.GET)
		public ModelAndView paraEditarTareaGet(@RequestParam("refe1") long refe1,@RequestParam("refe") long refe, @RequestParam("refe2") long refe2){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				modelAndView.addObject("aspecto",daoAspecto.findOne(refe));		
				modelAndView.addObject("reparacion",daoReparacion.findOne(refe2));
				modelAndView.addObject("repuesto",daoRepuesto.findAll());
				x= refe1;
				f = refe2;
				
				modelAndView.setViewName("modificarTarea");
				return modelAndView;
		

		}
		@RequestMapping(value = "/modificarTarea", 
				method = RequestMethod.POST)
		public ModelAndView paraEditarTareaPost( @ModelAttribute Reparacion reparacion){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				reparacion.setIdReparacion(f);
				reparacion.getAspecto().setIdAspecto(x);
				reparacion.getRepuesto().setIdRepuesto(g);
			
				daoReparacion.save(reparacion);
				modelAndView.addObject("repuesto", daoRepuesto.findAll());
				modelAndView.addObject("reparacion", daoReparacion.findAll());
				modelAndView.addObject("idAspecto", refeAspecto);
				modelAndView.addObject("aspecto", daoAspecto.findAll());
				modelAndView.setViewName("listarRepuestos");
				return modelAndView;
		}
		
		//cierra/abre ordenes
		@RequestMapping(value = "/cerrarAbrir", 
				method = RequestMethod.GET)
		public ModelAndView paraCerrar( @RequestParam ("refe1") long refe1){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				Orden orden = daoOrden.findOne(refe1);
				if (orden.isAbierta()==true){
					
				orden.setAbierta(false);
		}else{
			orden.setAbierta(true);
		}
			
				daoOrden.save(orden);
				
				modelAndView.addObject("repuesto", daoRepuesto.findAll());
				modelAndView.addObject("aspectos", daoAspecto.findAll());
				modelAndView.addObject("reparacion", daoReparacion.findAll());
				modelAndView.addObject("orden", daoOrden.findAll());
				modelAndView.addObject("idAuto", refeAuto);
				modelAndView.addObject("auto", daoAuto.findAll());
				modelAndView.setViewName("listarOrdenes");
				return modelAndView;
		}
		
		//supercadena de fors para calcular el total de todas las cuentas cerrasdas
		@RequestMapping(value = "/calcular", 
				method = RequestMethod.GET)
		public ModelAndView paraCalcular(){
			ModelAndView modelAndView = new ModelAndView();{
				Iterable <Aspecto> aspectoI = daoAspecto.findAll();
				Iterable <Reparacion> repI = daoReparacion.findAll();
				long  xx = 0;
				long zz = 0;
				long dd = 0;
				double total = 0;
				double cantidad= 0;
				double valor = 0;
				double horas = 0;
				for (Orden orden : daoOrden.findAll()){
					if (orden.isAbierta()==false){
						
						xx= orden.getIdOrden();
						for (Aspecto aspecto: aspectoI){
							if (aspecto.getOrden().getIdOrden() == xx ){
								zz=aspecto.getIdAspecto();
								horas = horas + (aspecto.getHoras() * 350);
								for (Reparacion reparacion : repI){
									if (reparacion.getAspecto().getIdAspecto()== zz){
										dd = reparacion.getAspecto().getIdAspecto();
										cantidad = reparacion.getCantidad();
										
										valor = reparacion.getRepuesto().getCoste();
								
										total = total + (cantidad * valor );
									}
								}
								
							}
							
						}
				}
					
				
				}
				totalFinal = horas + total;
						modelAndView.addObject("total", totalFinal);
				
				modelAndView.addObject("orden", daoOrden.findAll());
				modelAndView.addObject("idAuto", refeAuto);
				modelAndView.addObject("aspectos", daoAspecto.findAll());
				modelAndView.addObject("auto", daoAuto.findAll());
				modelAndView.setViewName("listarOrdenes");
			}
			return modelAndView;
		}
		
		@RequestMapping(value = "/ingresarAuto", 
				method = RequestMethod.GET)
		public ModelAndView paraIngresarAuto(@RequestParam("refe") long refe){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				modelAndView.addObject("auto",new Auto());		
				modelAndView.addObject("duenio",daoDuenio.findOne(refe));
				x= refe;
				modelAndView.setViewName("nuevoAuto");
				return modelAndView;
		}
		@RequestMapping(value = "/ingresarAuto", 
				method = RequestMethod.POST)
		public ModelAndView paraIngresarAutoPost(@ModelAttribute Auto auto){
		
				
				ModelAndView modelAndView = new ModelAndView();
					
				Auto auto1 = new Auto();
				Duenio duenio = new Duenio();
				daoAuto.save(auto1);
				auto1.setModelo(auto.getModelo());
				auto1.setPatente(auto.getPatente());
				duenio.setIdDuenio(x);
				auto1.setDuenio(duenio);
				daoAuto.save(auto1);
										
				modelAndView.addObject("duenio",daoDuenio.findAll());
				modelAndView.addObject("idDuenio", x);
				modelAndView.addObject("autos", daoAuto.findAll());
				modelAndView.setViewName("listarClientes");
				return modelAndView;
		}
		@RequestMapping(value = "/ingresarOrden", 
				method = RequestMethod.GET)
		public ModelAndView paraIngresarOrden(@RequestParam("refe") long refe){
		
				
				ModelAndView modelAndView = new ModelAndView();
							
				
				modelAndView.addObject("orden",new Orden());		
				modelAndView.addObject("sesioness", daoSession.findAll());	
				x= refe;
				modelAndView.setViewName("nuevaOrden");
				return modelAndView;
		}
		@RequestMapping(value = "/ingresarOrden", 
				method = RequestMethod.POST)
		public ModelAndView paraIngresarOrdenPost(@ModelAttribute Orden orden){
		
				
				ModelAndView modelAndView = new ModelAndView();
					
				Auto auto1 = new Auto();
				Sesiones sesiones = new Sesiones();
				Orden orden1 = new Orden();
				daoOrden.save(orden1);
				orden1.setDescripcion(orden.getDescripcion());
				orden1.setFecha(orden.getFecha());
				orden1.setAbierta(true);
				auto1.setIdAuto(x);
				sesiones = user1;
				orden1.setSesiones(sesiones);
				orden1.setAuto(auto1);
				daoOrden.save(orden1);
				refeAuto=x;	
				modelAndView.addObject("orden",daoOrden.findAll());
				modelAndView.addObject("idAuto", refeAuto);
			
				modelAndView.addObject("aspectos", daoAspecto.findAll());
				modelAndView.setViewName("listarOrdenes");
				return modelAndView;
		}

@RequestMapping(value = "/ingresarTarea", 
		method = RequestMethod.GET)
public ModelAndView paraIngresarTarea(@RequestParam("refe") long refe){

		
		ModelAndView modelAndView = new ModelAndView();
					
		
		modelAndView.addObject("aspecto",new Aspecto());		
		x= refe;
		modelAndView.setViewName("nuevaTarea");
		return modelAndView;
}
@RequestMapping(value = "/ingresarTarea", 
		method = RequestMethod.POST)
public ModelAndView paraIngresarTareaPost(@ModelAttribute Aspecto aspecto){

		
		ModelAndView modelAndView = new ModelAndView();
			
		Orden orden = new Orden();
		Aspecto aspecto1 = new Aspecto();
		daoAspecto.save(aspecto1);
		aspecto1.setDescripcion(aspecto.getDescripcion());
		aspecto1.setHoras(aspecto.getHoras());
		orden.setIdOrden(x);
		aspecto1.setOrden(orden);		
		daoAspecto.save(aspecto1);
		
		
		modelAndView.addObject("orden",daoOrden.findAll());				
		modelAndView.addObject("aspectos",daoAspecto.findAll());
		modelAndView.addObject("idOrden", x);
		modelAndView.addObject("idAuto", refeAuto);
		modelAndView.setViewName("listarOrdenes");
		return modelAndView;
}
@RequestMapping(value = "/ingresarRepuesto", 
method = RequestMethod.GET)
public ModelAndView paraIngresarRepuesto(@RequestParam("refe") long refe, @RequestParam("refe1") long refe1){


ModelAndView modelAndView = new ModelAndView();
			

modelAndView.addObject("reparacion",new Reparacion());		
modelAndView.addObject("repuesto",daoRepuesto.findAll());	
modelAndView.addObject("aspecto",daoAspecto.findAll());	
x= refe;
f=refe1;
modelAndView.setViewName("nuevoRepuesto");
return modelAndView;
}
@RequestMapping(value = "/ingresarRepuesto", 
method = RequestMethod.POST)
public ModelAndView paraIngresarRepuestoPost(@ModelAttribute Reparacion reparacion, @ModelAttribute Repuesto repuesto){


ModelAndView modelAndView = new ModelAndView();
Repuesto repuesto2= new Repuesto();
Reparacion reparacion1 = new Reparacion();
Aspecto aspecto1 = new Aspecto();
daoReparacion.save(reparacion1);
long e = repuesto.getIdRepuesto();
long z = x;
repuesto2.setIdRepuesto(x);
aspecto1.setIdAspecto(z);
reparacion1.setCantidad(reparacion.getCantidad());
reparacion1.setRepuesto(repuesto2);
reparacion1.setAspecto(aspecto1);

daoReparacion.save(reparacion1);
refeAspecto = x;

modelAndView.addObject("reparacion",daoReparacion.findAll());	

modelAndView.addObject("repuesto",daoRepuesto.findAll());
modelAndView.addObject("aspectos",daoAspecto.findAll());
modelAndView.addObject("idAspecto", refeAspecto);
modelAndView.addObject("idOrden", f);
modelAndView.setViewName("listarRepuestos");
return modelAndView;
}
}
