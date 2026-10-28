import java.util.ArrayList;

public abstract class Tour {
    public static final String PREFIJO_CODIGO = "TOUR-";
    public static final int CANTIDAD_PERSONAS_POR_OMISION = 1;
    public static final double PORCENTAJE_DESCUENTO_NACIONAL = 0.20;
    public static final String SEPARADOR_ATRACTIVOS = ", ";

    private String codigo;
    private String nombre;
    protected double precioBase;
    private int cupoMaximo;
    private Guia guia;
    protected ArrayList<Atractivo> atractivos;
    private ArrayList<Reserva> reservas;
    private static int cantidadTours;

    public Tour(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia) {
        nombre = pNombre;
        precioBase = pPrecioBase;
        cupoMaximo = pCupoMaximo;
        guia = pGuia;
        atractivos = new ArrayList<>();
        reservas = new ArrayList<>();
        cantidadTours++;
        codigo = PREFIJO_CODIGO + cantidadTours;
    }

    public abstract double calcularPrecioPorPersona();

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public static int getCantidadTours() {
        return cantidadTours;
    }

    public void cambiarGuia(Guia pGuia) {
        guia = pGuia;
    }

    public String consultarNombreGuia() {
        return guia.getNombreCompleto();
    }

    public void agregarAtractivo(Atractivo pAtractivo) {
        if (!formaParteDelRecorrido(pAtractivo)) {
            atractivos.add(pAtractivo);
        }
    }

    public String consultarNombresAtractivos() {
        String s = "";
        for (int i = 0; i < atractivos.size(); i++) {
            if (i > 0) {
                s = s + SEPARADOR_ATRACTIVOS;
            }
            s = s + atractivos.get(i).getNombre();
        }
        return s;
    }

    public final void registrarReserva(Cliente pCliente, int pCantidadPersonas) {
        if (pCantidadPersonas > 0 && pCantidadPersonas <= calcularCuposDisponibles()) {
            double monto = calcularMontoReserva(pCliente, pCantidadPersonas);
            reservas.add(new Reserva(reservas.size() + 1, pCliente.getNombre(),
                                     pCantidadPersonas, monto));
        }
    }

    public final void registrarReserva(Cliente pCliente) {
        registrarReserva(pCliente, CANTIDAD_PERSONAS_POR_OMISION);
    }

    public final int calcularCuposDisponibles() {
        return cupoMaximo - calcularPersonasReservadas();
    }

    public final double calcularIngresoTotal() {
        double total = 0;
        for (Reserva r : reservas) {
            total += r.getMonto();
        }
        return total;
    }

    private boolean formaParteDelRecorrido(Atractivo pAtractivo) {
        for (Atractivo a : atractivos) {
            if (a.getCodigo().equals(pAtractivo.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    private int calcularPersonasReservadas() {
        int total = 0;
        for (Reserva r : reservas) {
            total += r.getCantidadPersonas();
        }
        return total;
    }

    private double calcularMontoReserva(Cliente pCliente, int pCantidadPersonas) {
        double monto = calcularPrecioPorPersona() * pCantidadPersonas;
        if (pCliente.esNacional()) {
            monto = monto - (monto * PORCENTAJE_DESCUENTO_NACIONAL);
        }
        return monto;
    }

    public String toString() {
        String s = "Código= " + codigo + "\n" +
                   "Nombre= " + nombre + "\n" +
                   "Guía= " + consultarNombreGuia() + "\n" +
                   "Precio base= " + precioBase + "\n" +
                   "Precio por persona= " + calcularPrecioPorPersona() + "\n" +
                   "Cupo máximo= " + cupoMaximo + "\n" +
                   "Cupos disponibles= " + calcularCuposDisponibles() + "\n" +
                   "Recorrido= " + consultarNombresAtractivos() + "\n" +
                   "Reservas (" + reservas.size() + "):\n";
        for (Reserva r : reservas) {
            s = s + "  " + r + "\n";
        }
        s = s + "Ingreso total= " + calcularIngresoTotal() + "\n";
        return s;
    }
}
