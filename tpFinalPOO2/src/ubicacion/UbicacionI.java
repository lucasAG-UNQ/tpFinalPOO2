package ubicacion;

public interface UbicacionI {

	double getLatitud();

	double getLongitud();

	double distanciaHasta(UbicacionI epicentro);

}
