package Enumeradores;

/* ¿Para qué sirve?: Este enumerador guarda los niveles de prioridad que se le pueden dar a una falla reportada en el Logbook de un vuelo
   Clases que la utilizan: ReporteLogbook, TecnicoMantenimiento_GUI, OficialOperaciones_GUI
   Índice de Métodos: No aplica, este archivo solo enumera valores fijos y no tiene métodos propios */

public enum EstadoPrioridad {
	BAJA,   //Es la prioridad mas Baja de fallas reportadas
	MEDIA,  //Es la prioridad intermedia de fallas reportadas
	ALTA,    //Es la prioridad mas alta de fallas reportadas
        SIN_FALLAS
}
