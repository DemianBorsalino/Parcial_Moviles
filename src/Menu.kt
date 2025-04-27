    class Menu {
        val gestorSocios = GestorSocios()
        val gestorDisciplinas = GestorDisciplinas()
        val gestorInscripciones = GestorInscripciones()
        val gestorPagos = GestorPagos()

        fun BaseDatos() {
            // Crear socios
            val socio1 = Socio("Ana López", 12345678, "ana@example.com", id = 1)
            val socio2 = Socio("Bruno Díaz", 87654321, null, id = 2)

            gestorSocios.alta(socio1)
            gestorSocios.alta(socio2)

            // Crear disciplinas
            val futbol = Disciplina("Fútbol", tipoDisciplina.EQUIPO, id = 1, capacidadMaxima = 2)
            val natacion = Disciplina("Natación", tipoDisciplina.INDIVIDUAL, id = 2, capacidadMaxima = 3, descuento = 10.0) // 10% de descuento

            gestorDisciplinas.alta(futbol)
            gestorDisciplinas.alta(natacion)

            // Registrar pagos
            gestorPagos.registrarPago(socio1, 5000.0, "Cuota Fútbol Abril 2024", futbol)
            gestorPagos.registrarPago(socio2, 4000.0, "Cuota Natación Abril 2024", natacion)

            // Inscribir socios (usando try/catch para manejar excepciones)
            try {
                gestorInscripciones.inscribirSocio(socio1, futbol)
                gestorInscripciones.inscribirSocio(socio2, natacion)
            } catch (e: Exception) {
                println("Error al inscribir: ${e.message}")
            }

            // Intentar inscribir de nuevo a mismo socio (forzar error InscripcionDuplicadaException)
            try {
                gestorInscripciones.inscribirSocio(socio1, futbol)
            } catch (e: InscripcionDuplicadaException) {
                println("Error controlado: ${e.message}")
            }

            // Reportes
            println("\n--- Socios inscriptos por disciplina ---")
            gestorDisciplinas.listarSociosPorDisciplina()

            println("\n--- Socios morosos ---")
            gestorSocios.mostrarMorosos()

            println("\n--- Recaudación por disciplina ---")
            gestorPagos.recaudacionPorDisciplina(futbol)
            gestorPagos.recaudacionPorDisciplina(natacion)

            println("\n--- Cantidad de socios por disciplina ---")
            gestorDisciplinas.cantidadDeSociosPorDisciplina()

            println("\n--- Disciplinas con cupo completo ---")
            gestorDisciplinas.disciplinasConCupoCompleto()

            println("\n--- Disciplinas sin inscriptos ---")
            gestorDisciplinas.disciplinasSinInscriptos()

        }

        fun menuPrincipal() {
            do {
                println("Bienvenido al sistema de gestion")
                println("Que desa gestionar?:")
                println("1. Disciplinas")
                println("2. Socios")
                println("3. Pagos")
                println("4. Inscripciones")
                println("0. Salir")


                when (readLine()?.toIntOrNull()) {
                    1 -> menuDisciplinas()
                    /*2 -> menuSocios()
                    3 -> menuPagos()
                    4 -> menuInscrpiciones()*/
                    0 -> println("Adios! Vuelva pronto!")
                    else -> println("Opción inválida")
                }
            }
        }

        fun menuDisciplinas() {
            println("Seleccionaste gestion de disciplinas")
            println("Ahora, eliga una opcion:")
            println("1. Agregar disciplina")
            println("2. Eliminar disciplina")
            println("3. Buscar disciplina")
            println("4. Mostrar disciplinas")
            println("0. Salir")

            when (readLine()?.toIntOrNull()) {
                1 -> agregarDisciplina()
                2 -> eliminarDisciplina()
                3 -> buscarDisciplina()
                4 -> gestorDisciplinas.mostrarDisciplinas()
                0 -> println("Adios! Vuelva pronto!")
                else -> println("Opción inválida")
            }
        }

        fun menuSocios() {
            println("Socios man")
        }

        fun menuPagos() {
            println("Pagos para hacer")
        }
        fun menuInscripciones() {
            println("Inscripciones aiuda")
        }


        private fun agregarDisciplina() {
            println("Ingrese nombre de la disciplina:")
            val nombre = readLine() ?: return

            var tipo: tipoDisciplina? = null
            do {
                println("Ingrese tipo de disciplina (EQUIPO o INDIVIDUAL):")
                val tipoInput = readLine()

                tipo = try {
                    tipoInput?.let { tipoDisciplina.valueOf(it.uppercase()) }
                } catch (e: IllegalArgumentException) {
                    println("Tipo inválido. Intente de nuevo.")
                    null
                }
            } while (tipo == null)


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
            do {
                val id = getInputIntOrExit("Ingrese el ID de la Disciplina que desea eliminar (o 0 para volver atrás):")

                if (id == null) {
                    println("Volviendo al menú...")
                    return
                }

                try {
                    gestorDisciplinas.baja(id)
                    break // Si se elimina correctamente, salimos del bucle
                } catch (e: IdInvalidoException) {
                    println(e.message)
                    // sigue el bucle y vuelve a pedir
                }
            } while (true)
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
