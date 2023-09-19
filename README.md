# Threads-Java
Aplicación Java que, mediante el uso de las clases de concurrencia de Java, transcodifica vídeos.
La aplicación está formada por diferentes clases:
  Principal.java: se encarga de iniciar los hilos encargados de transcodificar vídeos, y el hilo encargado de recibir encargos por parte de los usuarios.
  HiloEncargo.java: se encarga de extraer cada vídeo del encargo, crear un trabajo, y añadirlo a la cola para que posteriormente sea transcodificado. Además, cuando todos los vídeos de un encargo han sido transcodificados, genera un DVD y se lo envía al usuario.
  ColaVideos.java: se almacenan en ella los vídeos que han de ser transcodificados. Para evitar transcodificar dos veces el mismo vídeo, si ha sido solicitado por varios usuarios, solo se añadirá a la cola si es un vídeo nuevo. Por el contrario se guardará la   referencia a dicho vídeo para poder mandárselo a los usuarios en un futuro.
  Trabajo.java: clase donde se trata la concurrencia para la trancodificación de vídeos.
  HiloTranscodificador.java: se encarga de transcodificar los vídeos y mandar el vídeo transcodificado al trabajo al que pertence.
