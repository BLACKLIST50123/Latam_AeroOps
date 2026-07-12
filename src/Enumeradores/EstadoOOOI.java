package Enumeradores;

/* ¿Para qué sirve?: Este enumerador guarda los cuatro momentos del control OOOI, que sirven para saber en qué parte del viaje va un vuelo (antes de salir, cuando sale, cuando despega, cuando aterriza y cuando llega)
   Clases que la utilizan: VueloOperativoBuilder, VueloOperativoDAO, ControlOOOIService, VueloOperativo, OficialOperaciones_GUI
   Índice de Métodos: No aplica, este archivo solo enumera valores fijos y no tiene métodos propios */

public enum EstadoOOOI {
	PENDIENTE,  //Antes de salir de la puerta.
	OUT,        //(Salida): El avión hace Pushback (Retroceso) y se desconecta de la puerta de embarque.
	OFF,        //(Despegue): Las ruedas del avión dejan de tocar la pista de aterrizaje.
	ON,         //(Aterrizaje): Las ruedas del avión tocan la pista en la ciudad de destino.
	IN          //(Llegada): El avión se estaciona y apaga motores en la puerta de embarque del destino.

}
