package ControlVelocidad;

public class CalculadorVel {

    protected void almacenarVelocidad(int Int_velocidad, Almacenamiento almacena) {
        almacena.almacenarVelocidad(Int_velocidad);
    }

    protected int calcularVelocidad(int vueltas, double radio, Almacenamiento almacena) {
        int v = (int) (vueltas * radio) / 40;
        almacena.almacenarVelocidad(v);
        return v;
    }
}