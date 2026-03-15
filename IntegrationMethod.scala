object SimpsonIntegration {

  // 1. Modelado de Datos Inmutable
  case class EjercicioIntegral(
      id: Int,
      funcion: Double => Double,
      a: Double,
      b: Double,
      valorEsperado: Double
  )

  // 2. Función de Orden Superior: Simpson 1/3 Simple
  def integracion(f: Double => Double, a: Double, b: Double): Double = {
    val x_medio = (a + b) / 2
    (b - a) * ((f(a) + 4 * f(x_medio) + f(b)) / 6)
  }

  // 3. Función de Orden Superior: Simpson 1/3 Compuesto (Para mayor precisión)
  def integracionCompuesta(f: Double => Double, a: Double, b: Double, n: Int): Double = {
    val h = (b - a) / n
    val sumaImpares = (1 until n by 2).map(i => f(a + i * h)).sum
    val sumaPares   = (2 until n by 2).map(i => f(a + i * h)).sum
    (h / 3) * (f(a) + 4 * sumaImpares + 2 * sumaPares + f(b))
  }

  def calcularError(esperado: Double, obtenido: Double): Double = {
    Math.abs(esperado - obtenido)
  }

  def main(args: Array[String]): Unit = {
    val ejercicios = List(
      EjercicioIntegral(1, x => -Math.pow(x, 2) + 8 * x - 12, 3, 5, 7.33),
      EjercicioIntegral(2, x => 3 * Math.pow(x, 2), 0, 2, 8.0),
      EjercicioIntegral(3, x => x + 2 * Math.pow(x, 2) - Math.pow(x, 3) + 5 * Math.pow(x, 4), -1, 1, 3.333),
      EjercicioIntegral(4, x => (2 * x + 1) / (Math.pow(x, 2) + x), 1, 2, 1.09861),
      EjercicioIntegral(5, x => Math.exp(x), 0, 1, 1.71828),
      EjercicioIntegral(6, x => 1 / Math.sqrt(x - 1), 2, 3, 0.828427),
      EjercicioIntegral(7, x => 1 / (1 + Math.pow(x, 2)), 0, 1, 0.785398)
    )

    println("--- Resultados Método Simple ---")
    val resultadosSimple = ejercicios.map { ej =>
      val valorObtenido = integracion(ej.funcion, ej.a, ej.b)
      val error = calcularError(ej.valorEsperado, valorObtenido)
      (ej.id, valorObtenido, ej.valorEsperado, error)
    }

    resultadosSimple.foreach { case (id, obt, esp, err) =>
      println(f"Ejercicio $id | Esperado: $esp%.5f | Obtenido: $obt%.5f | Error Absoluto: $err%.5f")
    }

    println("\n--- Solución a la Desviación (Método Compuesto) ---")
    val ej3 = ejercicios(2) // Extraemos el Ejercicio 3
    val resultadoCompuesto = integracionCompuesta(ej3.funcion, ej3.a, ej3.b, n = 10)
    val errorCompuesto = calcularError(ej3.valorEsperado, resultadoCompuesto)
    println(f"Ejercicio 3 (Compuesto 10 int.) | Obtenido: $resultadoCompuesto%.5f | Error Absoluto: $errorCompuesto%.5f")
  }
}
