public class TourAventura extends Tour {
    public static final String TIPO = "Aventura";
    public static final String NIVEL_POR_OMISION = "medio";
    public static final double PORCENTAJE_RECARGO_SEGURO = 0.15;

    private String nivelDificultad;

    public TourAventura(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia,
                        String pNivelDificultad) {
        super(pNombre, pPrecioBase, pCupoMaximo, pGuia);
        nivelDificultad = pNivelDificultad;
    }

    public TourAventura(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia) {
        this(pNombre, pPrecioBase, pCupoMaximo, pGuia, NIVEL_POR_OMISION);
    }

    public double calcularPrecioPorPersona() {
        return precioBase + (precioBase * PORCENTAJE_RECARGO_SEGURO);
    }

    public String toString() {
        String s = super.toString();
        s = s + "Tipo de tour= " + TIPO + "\n" +
                "Nivel de dificultad= " + nivelDificultad + "\n";
        return s;
    }
}
