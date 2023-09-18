package Simulador;

import java.awt.*;
import javax.swing.*;
import ControlVelocidad.*;
import Monitorizacion.*;

@SuppressWarnings("serial")
public class PanelEtiquetas extends JPanel implements Observador {
    private Interfaz interfaz;
    private JLabel etiquetaKmsV, etiquetaKmtV, etiquetaRotacionV, etiquetaRotaciontV, etiquetaVelMediaV, etiquetaCombustibleV, etiquetaCombustibleMV;
    private JLabel etiquetaNotifi1, etiquetaNotifi2, etiquetaNotifi3;
    private Monitorizacion monitor;
    private ControlVelocidad control;

    public PanelEtiquetas(Interfaz interfaz) {
        super();
        this.interfaz = interfaz;
        iniciarComponentes();
    }

    public void aniadirComponentes(Monitorizacion monitor, ControlVelocidad control) {
        this.monitor = monitor;
        this.control = control;
    }

    private void iniciarComponentes() {
        setBackground(new Color(200, 255, 255));
        setLayout(null);

        // Etiquetas relacionadas a la velocidad
        JLabel etiquetaKms = crearEtiqueta("Km/h", 15, 15, 100, 20, 25);
        etiquetaKmsV = crearEtiqueta("------", 125, 15, 100, 20, 20);

        JLabel etiquetaKmT = crearEtiqueta("Km total", 15, 60, 100, 20, 15);
        etiquetaKmtV = crearEtiqueta("------", 125, 60, 70, 20, 15);

        JLabel etiquetaVelMedia = crearEtiqueta("Vel media", 15, 185, 100, 20, 15);
        etiquetaVelMediaV = crearEtiqueta("------", 125, 185, 100, 20, 15);

        // Etiquetas relacionadas a las revoluciones
        JLabel etiquetaRotacion = crearEtiqueta("Rev/min", 15, 100, 100, 20, 20);
        etiquetaRotacionV = crearEtiqueta("------", 125, 100, 100, 20, 20);

        JLabel etiquetaRotacionT = crearEtiqueta("Rev total", 15, 140, 100, 20, 15);
        etiquetaRotaciontV = crearEtiqueta("------", 125, 140, 100, 20, 15);

        // Etiquetas relacionadas al combustible
        JLabel etiquetaConbustible = crearEtiqueta("Gas", 15, 450, 100, 20, 25);
        etiquetaCombustibleV = crearEtiqueta("------", 125, 450, 60, 20, 15);

        JLabel etiquetaConbustibleM = crearEtiqueta("Gas medio", 15, 500, 100, 20, 15);
        etiquetaCombustibleMV = crearEtiqueta("------", 125, 500, 60, 20, 15);

        // Otras etiquetas
        interfaz.etiquetaEstado = crearEtiqueta("Estado", 400, 20, 100, 30, 15, Color.RED);
        JLabel etiquetaTextoVel = crearEtiqueta("Velocidad Automatica", 380, 65, 200, 30, 15);
        interfaz.etiquetaVelAuto = crearEtiqueta("---", 430, 100, 100, 30, 25);
        JLabel etiquetaTextoNotifi = crearEtiqueta("Notificaciones:", 400, 135, 200, 30, 15);
        etiquetaNotifi1 = crearEtiqueta("1---", 400, 170, 100, 30, 15);
        etiquetaNotifi2 = crearEtiqueta("2---", 400, 230, 100, 30, 15);
        etiquetaNotifi3 = crearEtiqueta("3---", 400, 280, 100, 30, 15);
    }

    private JLabel crearEtiqueta(String texto, int x, int y, int width, int height, int fontSize) {
        return crearEtiqueta(texto, x, y, width, height, fontSize, Color.BLACK);
    }

    private JLabel crearEtiqueta(String texto, int x, int y, int width, int height, int fontSize, Color color) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        etiqueta.setBounds(x, y, width, height);
        etiqueta.setForeground(color);
        add(etiqueta);
        return etiqueta;
    }

    synchronized void mostrarVelocidad() {
        etiquetaKmsV.setText(Integer.toString(control.obtenerVel()));
        etiquetaKmtV.setText(Double.toString(control.obtenerDist()));
        interfaz.etiquetaVelAuto.setText(Integer.toString(control.leerVelSeleccionada()));
        etiquetaVelMediaV.setText(Double.toString(monitor.comprobarvelMed()));
    }

    synchronized void mostrarRevoluciones() {
        etiquetaRotacionV.setText(Integer.toString(control.obtenerRev()));
        etiquetaRotaciontV.setText(Long.toString(control.obtenerRevtotal()));
    }

    synchronized private void mostrarNotificaciones() {
        String notificacionAceite = monitor.comprobarNotificacionesAceite();
        String notificacionPastillas = monitor.comprobarNotificacionesPastillas();
        String notificacionRev = monitor.comprobarNotificacionesGeneral();

        etiquetaNotifi1.setText(notificacionAceite != null ? notificacionAceite : "1---");
        etiquetaNotifi2.setText(notificacionPastillas != null ? notificacionPastillas : "2---");
        etiquetaNotifi3.setText(notificacionRev != null ? notificacionRev : "3---");
    }

    private void mostrarGasolina() {
        etiquetaCombustibleV.setText(Double.toString(monitor.comprobarCombustible()));
        etiquetaCombustibleMV.setText(Double.toString(monitor.comprobarCombustibleMedio()));
    }

    @Override
    public void actualizar() {
        mostrarVelocidad();
        mostrarRevoluciones();
        mostrarGasolina();
        mostrarNotificaciones();
        interfaz.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRoundRect(5, 5, 225, 220, 20, 20);
        g.drawRect(100, 10, 100, 30);
        g.drawRect(100, 55, 100, 30);
        g.drawRect(100, 95, 100, 30);
        g.drawRect(100, 135, 100, 30);
        g.drawRect(100, 180, 100, 30);
        g.drawRect(100, 445, 100, 30);
        g.drawRect(100, 495, 100, 30);
        g.drawRoundRect(345, 5, 225, 400, 20, 20);
        g.drawRect(370, 15, 175, 40);
        g.drawRect(400, 100, 100, 30);
        g.drawRect(370, 165, 175, 40);
        g.drawRect(370, 225, 175, 40);
        g.drawRect(370, 275, 175, 40);
    }
}



