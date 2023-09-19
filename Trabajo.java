import java.util.ArrayList;
import java.util.List;

import ssoo.videos.Video;

public class Trabajo {
	
	private Video v;
	private boolean transcodificado;
	private Video vTranscodificado;
	private List<Trabajo> listaTrabajos;
	
	public Trabajo(Video v) {
		this.v = v;
		transcodificado = false;
		listaTrabajos = new ArrayList<>();
	}
	
	public Video getVideo() {
		return this.v;
	}
	
	public Video getVideoTranscodificado(){
		synchronized(this) {
			while(!transcodificado)
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			return vTranscodificado;
		}
	}
	
	public void mandarVideoTranscodificado(Video vTranscodificado) {
		synchronized (this) {
			this.vTranscodificado = vTranscodificado;
			transcodificado = true;
			this.notifyAll();
		}
	}
	
	public void anadirMismoTrabajo(Trabajo t) {
		listaTrabajos.add(t);
	}
	
	public void actualizarOtrosTrabajos(){
		for(Trabajo t : listaTrabajos) {
			t.mandarVideoTranscodificado(this.getVideoTranscodificado());
		}
	}
}
