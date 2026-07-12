package Enumeradores;

/* ¿Para qué sirve?: Este enumerador guarda todos los estados posibles por los que puede pasar un vuelo, desde que se crea hasta que termina o se cancela
   Clases que la utilizan: Todas las clases del paquete Patrones/State, VueloOperativoBuilder, VueloOperativoDAO, ControlOOOIService, DespachoService, VueloOperativo, OficialOperaciones_GUI
   Índice de Métodos: No aplica, este archivo solo enumera valores fijos y no tiene métodos propios */

public enum EstadoVuelo {
	PENDIENTE_DESPACHO, //Ocupa este estado cuando el Oficial asigna el Vuelo + Tripulación
	EN_DEMORA,          //Se activa si el clima está mal (METAR en amarillo/rojo) o hay sobrepeso y el Oficial decide posponerlo. 
	APROBADO,           //Cambia a este estado cuando la validación de Pesos cuadra con el MTOW y el clima está en verde.
	EN_VUELO,           //Se activa automáticamente apenas el Oficial marca el tiempo "OUT" en los controles OOOI.
	EN_TIERRA,          //Se activa automáticamente apenas el Oficial marca el tiempo "ON" en los controles OOOI.
	COMPLETADO,         //Se activa cuando el Oficial cierra el Logbook firmando si hubo fallas o no.
	CANCELADO;          //Se activa si el Oficial presiona el botón "Cancelar Vuelo" (NO-GO) liberando a toda la tripulación.

}
