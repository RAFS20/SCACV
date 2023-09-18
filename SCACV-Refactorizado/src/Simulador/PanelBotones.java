package Simulador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import ControlVelocidad.*;
import Monitorizacion.*;

public class PanelBotones extends JPanel implements Observador {
    private Interfaz interfaz;
    private JButton botonAcelerar, botonParar, botonMantener, botonReiniciar, botonRepostar, botonRestear;
    private JButton botonMecanico1, botonMecanico2, botonMecanico3;
    private JToggleButton botonEncender, botonFreno;
    private Monitorizacion monitor;
    private ControlVelocidad control;

    public PanelBotones(Interfaz interfaz) {
        super();
        this.interfaz = interfaz;
        iniciarComponentes();
    }

    public void aniadirComponentes(Monitorizacion monitor, ControlVelocidad control) {
        this.monitor = monitor;
        this.control = control;
    }

    private void iniciarComponentes() {
        setLayout(null);

        // Botones de control
        botonAcelerar = crearBoton("Acelerar", 100, 250, 100, 30);
        botonParar = crearBoton("Parar", 25, 300, 100, 30);
        botonMantener = crearBoton("Mantener", 175, 300, 100, 30);
        botonReiniciar = crearBoton("Reiniciar", 100, 350, 100, 30);
        botonRepostar = crearBoton("Repostar", 300, 500, 100, 30);
        botonRestear = crearBoton("Resetear", 450, 500, 100, 30);

        // Botones de encendido y freno
        botonEncender = crearToggleButton("Arrancar", 450, 450);
        botonFreno = crearToggleButton("Frenar", 300, 450);

        // Botones mecánicos (más grandes y del mismo tamaño)
        int mecanicoButtonWidth = 180;
        int mecanicoButtonHeight = 40;
        botonMecanico1 = crearBoton("Mecanico Aceite", 600, 10, mecanicoButtonWidth, mecanicoButtonHeight);
        botonMecanico2 = crearBoton("Mecanico Pastillas", 600, 60, mecanicoButtonWidth, mecanicoButtonHeight);
        botonMecanico3 = crearBoton("Mecanico revision", 600, 110, mecanicoButtonWidth, mecanicoButtonHeight);

        // Agregar listeners a los botones
        botonAcelerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAcelerarActionPerformed(evt);
            }
        });
        botonParar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonPararActionPerformed(evt);
            }
        });
        botonMantener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonMantenerActionPerformed(evt);
            }
        });
        botonReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonReiniciarActionPerformed(evt);
            }
        });
        botonRepostar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonRepostarActionPerformed(evt);
            }
        });
        botonRestear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonRestearActionPerformed(evt);
            }
        });
        botonEncender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonEncenderActionPerformed(evt);
            }
        });
        botonFreno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonFrenoActionPerformed(evt);
            }
        });
        botonMecanico1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonMecanico1ActionPerformed(evt);
            }
        });
        botonMecanico2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonMecanico2ActionPerformed(evt);
            }
        });
        botonMecanico3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonMecanico3ActionPerformed(evt);
            }
        });

        // Agregar componentes al panel
        interfaz.add(botonAcelerar);
        interfaz.add(botonParar);
        interfaz.add(botonMantener);
        interfaz.add(botonReiniciar);
        interfaz.add(botonRepostar);
        interfaz.add(botonRestear);
        interfaz.add(botonEncender);
        interfaz.add(botonFreno);
        interfaz.add(botonMecanico1);
        interfaz.add(botonMecanico2);
        interfaz.add(botonMecanico3);
    }

    // Métodos de acción de los botones (separados para mayor claridad)
    private void botonAcelerarActionPerformed(ActionEvent evt) {
        if (!botonFreno.isSelected()) {
            interfaz.etiquetaEstado.setText("Acelerando");
            control.cambiarPalanca(Palanca.APAGADO);
            control.acelera.pisarAcelerador();
            control.freno.soltarFreno();
        } else {
            interfaz.etiquetaEstado.setText("Parado");
            control.freno.presionarFreno();
        }
    }

    private void botonMantenerActionPerformed(ActionEvent evt) {
        interfaz.etiquetaEstado.setText("Manteniendo");
        control.cambiarPalanca(Palanca.MANTENIENDO);
    }

    private void botonPararActionPerformed(ActionEvent evt) {
        interfaz.etiquetaEstado.setText("Para SCAV");
        control.cambiarPalanca(Palanca.APAGADO);
        control.freno.soltarFreno();
        control.acelera.soltarAcelerador();
    }

    private void botonReiniciarActionPerformed(ActionEvent evt) {
        interfaz.etiquetaEstado.setText("Reiniciando");
        control.cambiarPalanca(Palanca.REINICIANDO);
        control.freno.soltarFreno();
        control.acelera.soltarAcelerador();
    }

    private void botonFrenoActionPerformed(ActionEvent evt) {
        if (botonFreno.isSelected()) {
            interfaz.etiquetaEstado.setText("Frenando");
            botonFreno.setText("Soltar Freno");
            control.cambiarPalanca(Palanca.APAGADO);
            control.freno.presionarFreno();
            control.acelera.soltarAcelerador();
        } else {
            botonFreno.setText("Frenar");
            control.freno.soltarFreno();
            interfaz.etiquetaEstado.setText("Punto muerto");
        }
    }

    private void botonEncenderActionPerformed(ActionEvent evt) {
        control.motor.cambiarEstado();
        if (botonEncender.isSelected()) {
            botonEncender.setText("Apagar");
            interfaz.etiquetaEstado.setText("Arrancado");
        } else {
            botonEncender.setText("Arrancar");
            control.acelera.soltarAcelerador();
            interfaz.etiquetaEstado.setText("Parado");
        }
    }

    private void botonRepostarActionPerformed(ActionEvent evt) {
        monitor.cambiarANivelInicial();
    }

    private void botonRestearActionPerformed(ActionEvent evt) {
        monitor.reseteo();
    }

    private void botonMecanico1ActionPerformed(ActionEvent evt) {
        monitor.mecanicoAceite();
        monitor.NotificaMecanico();
    }

    private void botonMecanico2ActionPerformed(ActionEvent evt) {
        monitor.mecanicoPastillas();
        monitor.NotificaMecanico();
    }

    private void botonMecanico3ActionPerformed(ActionEvent evt) {
        monitor.mecanicoGeneral();
        monitor.NotificaMecanico();
    }

    // Método para activar/desactivar botones según el estado
    private void actualizarBotones() {
        if (control.motor.leerEstado()) {
            botonAcelerar.setEnabled(true);
            if (control.acelera.leerEstado() && !(control.estadoPalanca.leerEstado() == Palanca.MANTENIENDO || control.estadoPalanca.leerEstado() == Palanca.REINICIANDO)) {
                botonMantener.setEnabled(true);
                botonReiniciar.setEnabled(true);
            }
            if (control.estadoPalanca.leerEstado() == Palanca.MANTENIENDO || control.estadoPalanca.leerEstado() == Palanca.REINICIANDO) {
                botonAcelerar.setEnabled(true);
                botonParar.setEnabled(true);
            }
            if (control.estadoPalanca.leerEstado() == Palanca.APAGADO) {
                botonAcelerar.setEnabled(true);
                botonReiniciar.setEnabled(true);
            }
        } else {
            if (control.obtenerVel() == 0) {
                botonRepostar.setEnabled(true);
                botonRestear.setEnabled(true);
                botonMecanico1.setEnabled(true);
                botonMecanico2.setEnabled(true);
                botonMecanico3.setEnabled(true);
            }
        }
    }

    // Método privado auxiliar para crear botones con tamaño personalizado
    private JButton crearBoton(String texto, int x, int y, int width, int height) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, width, height);
        return boton;
    }

    private JToggleButton crearToggleButton(String texto, int x, int y) {
        JToggleButton toggleButton = new JToggleButton(texto);
        toggleButton.setBounds(x, y, 100, 30);
        return toggleButton;
    }

    // Implementación del método de la interfaz Observador
    @Override
    public void actualizar() {
        actualizarBotones();
    }
}

