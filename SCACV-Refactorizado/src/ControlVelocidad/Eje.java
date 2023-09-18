package ControlVelocidad;

public final class Eje {
    public final double RADIO = 0.8;
    private int vueltas;
    private long vueltasTotales;
    private final CalculadorVel calculadorVel;
    public final int MAXVUELTAS = 10000;
    public int velAnterior;

    public Eje() {
        calculadorVel = new CalculadorVel();
        vueltas = 0;
        vueltasTotales = 0;
    }

    synchronized public void incrementarVueltas(int aumento) {
        if (vueltas <= MAXVUELTAS || aumento < 0) {
            vueltas += aumento;
            vueltas = Math.max(vueltas, 0);
        }
    }

    synchronized public void resetear() {
        vueltasTotales = 0;
    }

    synchronized public int leerRevoluciones() {
        return vueltas;
    }

    synchronized public long leerRevolucionesTotales() {
        return vueltasTotales;
    }

    synchronized public void calcularVelocidad(Almacenamiento almacena) {
        velAnterior = calculadorVel.calcularVelocidad(vueltas, RADIO, almacena);
        vueltasTotales += vueltas / 24;
    }
}