package Monitorizacion;

import ControlVelocidad.Eje;

public final class Deposito {
    
    private double _nivelActual;
    private final double NIVEL_INICIAL = 100;
    private final double RATIO_CONUSMO = 0.0000000005;

    protected Deposito() {
        _nivelActual = NIVEL_INICIAL;
    }

    protected double leerNivelActual() {
        return _nivelActual;
    }

    protected double leerNivelInicial() {
        return NIVEL_INICIAL;
    }

    protected void actualizarDeposito(Eje Eje_e) {
        double consumo = calcularConsumo(Eje_e.leerRevoluciones());
        _nivelActual -= consumo;
        asegurarNivelMinimo();
    }

    private double calcularConsumo(int revoluciones) {
        return (revoluciones * (revoluciones / 15)) * RATIO_CONUSMO;
    }

    private void asegurarNivelMinimo() {
        if (_nivelActual <= 0) {
            _nivelActual = 0;
        }
    }

    protected void cambiarANivelInicial() {
        _nivelActual = NIVEL_INICIAL;
    }
}
