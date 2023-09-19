//Rubén Sanz Barrio ruben.sanz@alumnos.upm.es

import java.io.IOException;

import ssoo.videos.PanelVisualizador;
import ssoo.videos.servidor.Peticion;
import ssoo.videos.servidor.ReceptorPeticiones;

public class Principal{
	
	private final static int MAX_TRANS = 4;
	
	public static void main(String[] args){
		final Thread[] hilosTrans = new Thread[MAX_TRANS];
		final ColaVideos cola = new ColaVideos();
		PanelVisualizador.getPanel().registrarColaTrabajos(cola);
		try {
			for(int i = 0; i < MAX_TRANS; i++) {
				hilosTrans[i] = new Thread(new HiloTranscodificador(cola));
				hilosTrans[i].start();
			}
			
			ReceptorPeticiones receptor = new ReceptorPeticiones();
			
			while(true) {
				Peticion p = receptor.recibirPeticion();
				final Thread hiloEncargo = new Thread(new HiloEncargo(p, cola));
				hiloEncargo.start();
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}