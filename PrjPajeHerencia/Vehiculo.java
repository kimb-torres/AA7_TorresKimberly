public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int annio;

    public Vehiculo(String pPlaca, String pMarca, String pModelo, int pAnnio) {
        placa  = pPlaca;
        marca  = pMarca;
        modelo = pModelo;
        annio  = pAnnio;
    }

    public abstract double calcularMontoPeaje();

    public abstract String getTipo();

    public String toString() {
        return "Placa= " + placa + "\n" +
               "Tipo= " + getTipo() + "\n" +
               "Marca= " + marca + "\n" +
               "Modelo= " + modelo + "\n" +
               "Año= " + annio;
    }
}
