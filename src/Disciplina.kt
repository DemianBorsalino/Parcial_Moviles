import tipoDisciplina
data class Disciplina(
    val nombre: String,
    val tipo: tipoDisciplina,
    val id: Int,
    val capacidadMaxima: Int,
    val sociosInscriptos: MutableList<Socio> = mutableListOf()
)