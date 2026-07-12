package Enumeradores;

/* ¿Para qué sirve?: Este enumerador guarda los posibles estados en los que puede estar un avión dentro del sistema
   Clases que la utilizan: AeronaveDAO, Aeronave, OficialOperaciones_GUI
   Índice de Métodos: No aplica, este archivo solo enumera valores fijos y no tiene métodos propios */

public enum EstadoAeronave {
	APTO,               //Es el estado base del avión, que permite la seleccion del vuelo 
	MANTENIMIENTO       //Es el estado que se ocupa cuando al cerrar el vuelo se reportan fallas en el LogBook

}
