package Enumeradores;

public enum EstadoVuelo {
	PENDIENTE_DESPACHO, //Ocupa este estado cuando el Oficial asigna el Vuelo + Tripulación
	EN_DEMORA,          //Se activa si el clima está mal (METAR en amarillo/rojo) o hay sobrepeso y el Oficial decide posponerlo. 
	APROBADO,           //Cambia a este estado cuando la validación de Pesos cuadra con el MTOW y el clima está en verde.
	EN_VUELO,           //Se activa automáticamente apenas el Oficial marca el tiempo "OUT" en los controles OOOI.
	EN_TIERRA,          //Se activa automáticamente apenas el Oficial marca el tiempo "ON" en los controles OOOI.
	COMPLETADO,         //Se activa cuando el Oficial cierra el Logbook firmando si hubo fallas o no.
	CANCELADO;          //Se activa si el Oficial presiona el botón "Cancelar Vuelo" (NO-GO) liberando a toda la tripulación.

}
