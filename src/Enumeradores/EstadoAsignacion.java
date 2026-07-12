package Enumeradores;

/* ¿Para qué sirve?: Este enumerador guarda los posibles estados en los que puede estar un tripulante cuando se le asigna a un vuelo
   Clases que la utilizan: Capa de Modelos (pensado para usarse junto con TripulacionAsignada)
   Índice de Métodos: No aplica, este archivo solo enumera valores fijos y no tiene métodos propios */

public enum EstadoAsignacion {
	DISPONIBLE, //Es el estado base cuando estan libres a asignacion.
	ASIGNADO    //Cambia a este estado cuando el oficial lo selecciona para algun vuelo programado,

}
