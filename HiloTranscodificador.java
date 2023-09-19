import ssoo.videos.Transcodificador;
import ssoo.videos.Video;

public class HiloTranscodificador implements Runnable{
	
	private Trabajo trabajo;
	private ColaVideos cola;
	private Transcodificador t;
	private Video videoTranscodificado;
	
	public HiloTranscodificador(ColaVideos cola) {
		this.cola = cola;
		t = new Transcodificador();
	}

	@Override
	public void run() {
		do{
			trabajo = cola.sacar();
			videoTranscodificado = t.transcodificar(trabajo.getVideo());
			trabajo.mandarVideoTranscodificado(videoTranscodificado);
			trabajo.actualizarOtrosTrabajos();
		}while(true);
	
	}

}
