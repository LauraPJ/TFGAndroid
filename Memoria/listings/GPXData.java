public class GPXData implements Serializable{
    private String nombreSendero;
    private String descSendero;
    private Punto[] puntos;
    private double alturaMinima;
    private double alturaMaxima;
    private double gananciaAltitud;
    private double perdidaAltitud;
    private double distancia;

    public GPXData(String nS, String dS, Punto[] p) {
        setNombreSendero(nS);
        setDescSendero(dS);
        setPuntos(p);
        
        ...  // Calculo de altura minima y maxima, ganancia y perdida de gananciaAltitud
        
        ...  // Asignaciones de variables
    }
}
