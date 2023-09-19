//Rubén Sanz Barrio ruben.sanz@alumnos.upm.es

import java.util.ArrayList;
import java.util.List;

import ssoo.videos.Dvd;
import ssoo.videos.MenuRaiz;
import ssoo.videos.Video;
import ssoo.videos.servidor.Cliente;
import ssoo.videos.servidor.Peticion;

public class HiloEncargo implements Runnable{
	
	private Peticion p;
	private ColaVideos cola;
	private List<Video> listaVideosTrans;
	private List<Trabajo> listaTrabajos;
	
	public HiloEncargo(Peticion p, ColaVideos cola) {
		this.p = p;
		this.cola = cola;
		listaTrabajos = new ArrayList<>();
		listaVideosTrans = new ArrayList<Video>();
	}
	
	@Override
	public void run() {
		for(Video v : p.getEncargo().getVideos()) {
			Trabajo t = new Trabajo(v);
				try {
					cola.meter(t);
					listaTrabajos.add(t);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		for(Trabajo t : listaTrabajos) {
			listaVideosTrans.add(t.getVideoTranscodificado());
		}
		
		Dvd dvd = new Dvd(p.getEncargo().getTitulo(), new MenuRaiz(listaVideosTrans), listaVideosTrans);
		Cliente c = p.getCliente();
		c.enviar(dvd);
	}
}
