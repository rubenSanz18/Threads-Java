//Rubén Sanz Barrio ruben.sanz@alumnos.upm.es

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import ssoo.videos.Cola;

public class ColaVideos implements Cola{
	
	private LinkedBlockingQueue<Trabajo> cola;
	private ConcurrentHashMap<String, Trabajo> mapa;
	
	public ColaVideos() {
		cola = new LinkedBlockingQueue<Trabajo>();
		mapa = new ConcurrentHashMap<>();
	}

	public synchronized void meter(Trabajo t) throws InterruptedException{
		if(!mapa.containsKey(t.getVideo().getNombre())) {
			mapa.put(t.getVideo().getNombre(), t);
			cola.put(t);
		}
		else {
			Trabajo trabajo = mapa.get(t.getVideo().getNombre());
			trabajo.anadirMismoTrabajo(t);
		}
		
	}
	
	public Trabajo sacar() {
		Trabajo t = null;
		try {
			t = cola.take();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		return t;
	}

	@Override
	public int numTrabajos() {
		return cola.size();
	}
}
