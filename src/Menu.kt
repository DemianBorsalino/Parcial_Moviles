    class Menu {
        val gestorDisciplinas = GestorDisciplinas()
        val gestorSocios = GestorSocios()

        fun menuPrincipal() {
            println("Bienvenido al sistema de gestión")
            println("Seleccione una opción:")
            println("1. Agregar disciplina")
            println("2. Buscar disciplina")
            println("3. Mostrar disciplinas")
            println("0. Salir")

            when (readLine()?.toIntOrNull()) {
                1 -> agregarDisciplina()
                2 -> eliminarDisciplina()
                3 -> buscarDisciplina()
                4 -> gestorDisciplinas.mostrarDisciplinas()
                0 -> println("Saliendo...")
                else -> println("Opción inválida")
            }
        }

        private fun agregarDisciplina() {
            println("Ingrese nombre de la disciplina:")
            val nombre = readLine() ?: return

            println("Ingrese tipo de disciplina (EQUIPO o INDIVIDUAL):")
            val tipoInput = readLine() ?: return
            val tipo = tipoDisciplina.valueOf(tipoInput.uppercase())

            println("Ingrese capacidad máxima:")
            val capacidad = readLine()?.toIntOrNull() ?: return

            val nuevaDisciplina = Disciplina(
                nombre = nombre,
                tipo = tipo,
                id = gestorDisciplinas.generoID(),
                capacidadMaxima = capacidad
            )

            gestorDisciplinas.alta(nuevaDisciplina)
        }

        private fun eliminarDisciplina() {
            val id = getInputIntOrExit("Ingrese el ID de la Disciplina que desea eliminar (o 0 para volver atrás):")

            if (id == null) {
                println("Volviendo al menú...")
                return
            }

            gestorDisciplinas.baja(id)
        }

        private fun buscarDisciplina() {
        println("Ingrese dato para buscar:")
        val dato = readLine() ?: return

        val numero = dato.toIntOrNull()

        val resultado = if (numero != null) {
            gestorDisciplinas.buscarDisciplina(numero)
        } else if (dato.equals("EQUIPO", ignoreCase = true) || dato.equals("INDIVIDUAL", ignoreCase = true)) {
            gestorDisciplinas.buscarDisciplina(tipoDisciplina.valueOf(dato.uppercase()))
        } else {
            gestorDisciplinas.buscarDisciplina(dato)
        }

        if (resultado != null) {
            println("Disciplina encontrada: ${resultado.nombre}, ${resultado.id}")
        } else {
            println("No se encontró disciplina.")
        }


    }
        fun getInputIntOrExit(message: String): Int? {
            println(message)
            val input = readLine()

            val valor = input?.toIntOrNull()

            if (valor == null) {
                println("Entrada no válida. Por favor ingrese un número.")
                return getInputIntOrExit(message)
            }

            if (valor == 0) {
                return null // El usuario eligió salir
            }

            return valor
        }


}
