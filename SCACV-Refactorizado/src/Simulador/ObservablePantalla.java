package Simulador;
public class ObservablePantalla extends Observable {

	public ObservablePantalla(PanelEtiquetas Observador_panelEtiquetas) {
		super();
		agregarObservador(Observador_panelEtiquetas);
	}
}