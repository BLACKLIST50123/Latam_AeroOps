package Enumeradores;

public enum EstadoOOOI {
	PENDIENTE,  //Antes de salir de la puerta.
	OUT,        //(Salida): El avión hace Pushback (Retroceso) y se desconecta de la puerta de embarque.
	OFF,        //(Despegue): Las ruedas del avión dejan de tocar la pista de aterrizaje.
	ON,         //(Aterrizaje): Las ruedas del avión tocan la pista en la ciudad de destino.
	IN          //(Llegada): El avión se estaciona y apaga motores en la puerta de embarque del destino.

}
