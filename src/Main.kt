fun main() {
    // Variables que necesitamos para el programa
    val tablero = Array(3) { MutableList(3) { '_' } }
    var jugadas = 0
    var turno = 'X'

    // ======================================================================== \\
    // Bienvenida
    println("Bienvenido al Tres en Raya!")

    // Partida
    while (jugadas < 9) {
        imprimmirTablero(tablero)
        try {

            println("Es el turno de $turno")
            println("Introduce la fila (1-3): ")
            var fila = readln().toInt() - 1
            // Verificamos que la fila esté dentro de los parametros.
            while (fila !in 0..2) {
                println("Error en la fila. Introduce un número entre 1 y 3: ")
                fila = readln().toInt() - 1
            }

            println("Introduce la columna (1-3): ")
            var columna = readln().toInt() - 1
            // Verificamos que la columna esté dentro de los parámetros.
            while (columna !in 0..2){
                println("Error en la calumna. Introduce un número entre 1 y 3: ")
                columna = readln().toInt() - 1
            }

            if (tablero[fila][columna] == '_') {
                tablero[fila][columna] = turno
            }

            // Verificar ganador después de cada movimiento
            val simboloGanador = comprobarGanador(tablero, fila, columna)
            if (simboloGanador != null) {
                imprimmirTablero(tablero)
                println("¡El jugador $simboloGanador ha ganado!")
                return
            }

            // Se cambia de jugador e incrementa el número de jugadas
            when (turno) {
                'X' -> {
                    turno = 'O'
                    jugadas++
                }

                'O' -> {
                    turno = 'X'
                    jugadas++
                }

                else -> println("Posición inválida.")
            }
        } catch (e: IllegalArgumentException) {
            println("Entrada inválida. Por favor ingresa dos números separados por espacio.")
        }
        imprimmirTablero(tablero)
    }

}

/**
 * Imprimir tablero
 * @return Devuelve el tablero actual.
 */
fun imprimmirTablero(tablero: Array<MutableList<Char>>) {
    println()
    for (fila in tablero) {
        println(fila.joinToString(" "))
    }
    println()
}

/**
 * Comprobar ganador
 * @return Devuelve el símbolo del jugador ganador o '-' en caso de que ninguno gane.
 */
fun comprobarGanador(tablero: Array<MutableList<Char>>, fila: Int, columna: Int): String? {
    val simbolo = tablero[fila][columna]

    // Verificamos fila
    if (tablero[fila].all { it == simbolo }) {
        return simbolo.toString()
    }
    // Verificamos columna
    if ((0..<3).all { tablero[it][columna] == simbolo }) {
        return simbolo.toString()
    }
    // Verificamos diagonal izq-derch
    if ((0..<3).all { tablero[it][it] == simbolo }) {
        return simbolo.toString()
    }
    // Verificamos diagonal derch-izq
    if ((1..<3).all { tablero[it][2 - it] == simbolo }) {
        return simbolo.toString()
    }
    // Verificar si todas las casillas están llenas
    if (tablero.all { fila -> fila.all { it != '_' } }) {
        return "-"  // Empate
    }
    // Si no hay ganador devolveremos el ganador como nulo, habrá empate.
    return null
}