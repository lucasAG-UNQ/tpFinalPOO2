package ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion implements UbicacionI{

	private double latitud;
	private double longitud;

	public Ubicacion(double latitud, double longitud) {
		this.latitud=latitud;
		this.longitud=longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public double distanciaHasta(UbicacionI ubicacion) {
		//double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(ubicacion.getLatitud() - this.getLatitud());  
        double dLng = Math.toRadians(ubicacion.getLongitud() - this.getLongitud());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(this.getLatitud())) * Math.cos(Math.toRadians(ubicacion.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia; 
	}

	public List<Ubicacion> ubicacionesAMenosDe(List<Ubicacion> ubicaciones, double kilometros) {
		return ubicaciones.stream().filter(x->this.distanciaHasta(x)<kilometros).toList();
	}
	
}
