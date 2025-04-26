interface Gestiones<SoDis> { //SoDis es para que tome en cuenta el tipo de clase que estamos queriendo implementar dentro de la interface
    fun alta(item: SoDis)    //Es decir, si yo le meto algo como Gestiones<Disciplina>, las funciones van a tomar los parametros como
    fun baja(id: Int)        //un objeto de disciplina, y usará correctamente los atributos
    //fun buscar(id: Int): SoDis?
}