#  Integración Numérica: Regla de Simpson 1/3 en Scala 3

![Scala 3](https://img.shields.io/badge/Scala_3-DC322F?style=for-the-badge&logo=scala&logoColor=white)
![Functional Programming](https://img.shields.io/badge/Paradigma-Funcional-blue?style=for-the-badge)

Este repositorio contiene la resolución del trabajo de consulta sobre **Funciones de Orden Superior (Higher Order Functions)**. El objetivo principal es implementar el método de integración numérica de Simpson 1/3 aplicando estrictamente el paradigma de Programación Funcional, evitando el uso de bucles imperativos y variables mutables.

##  Objetivos
* **Aplicar Funciones de Orden Superior:** Pasar funciones matemáticas como parámetros a otras funciones.
* **Modelado de Datos:** Utilizar `case classes` para estructurar la información de manera inmutable.
* **Transformación de Datos:** Utilizar operaciones funcionales como `.map` para procesar colecciones de ejercicios de forma declarativa.

##  Marco Teórico: Método de Simpson 1/3
La regla de Simpson 1/3 permite aproximar el área bajo una curva (integral definida). La fórmula utilizada es:

$$\int_{a}^{b} f(x) dx \approx \frac{b-a}{6} \left[ f(a) + 4f\left(\frac{a+b}{2}\right) + f(b) \right]$$

Adicionalmente, se calcula el **Error Absoluto** comparando la aproximación con el valor matemático esperado: $Error = |ValorEsperado - ValorObtenido|$

##  Arquitectura y Lógica de Implementación

La solución se ha diseñado bajo una arquitectura puramente funcional:

1. **Modelado Inmutable:** Se usa una `case class EjercicioIntegral` que encapsula la función matemática (`Double => Double`) junto con sus límites y el valor esperado.
2. **Higher Order Function:** La función `integracion(f: Double => Double, a: Double, b: Double)` recibe la ecuación a evaluar como parámetro, permitiendo reutilizar la lógica de integración para infinitas ecuaciones.
3. **Mapeo Funcional:** Se reemplazan los bucles tradicionales (`for`/`while`) por el método `.map`, transformando una lista de problemas en una lista de resultados de manera declarativa.

##  Resultados y Análisis de Error

| Ejercicio | Función | Valor Esperado | Valor Obtenido | Error Absoluto |
| :--- | :--- | :--- | :--- | :--- |
| 1 | $\int_{3}^{5} (-x^2+8x-12) dx$ | 7.33 | 7.3333 | 0.0033 |
| 2 | $\int_{0}^{2} 3x^2 dx$ | 8.0 | 8.0 | 0.0 |
| 3 | $\int_{-1}^{1} (x+2x^2-x^3+5x^4) dx$ | 3.333 | 4.6666 | 1.3336 |
| 4 | $\int_{1}^{2} \frac{2x+1}{x^2+x} dx$ | 1.09861 | 1.098612 | 2.288 \times 10^{-6} |
| 5 | $\int_{0}^{1} e^x dx$ | 1.71828 | 1.71886 | 5.811 \times 10^{-4} |
| 6 | $\int_{2}^{3} \frac{1}{\sqrt{x-1}} dx$ | 0.828427 | 0.83240 | 0.00397 |
| 7 | $\int_{0}^{1} \frac{1}{1+x^2} dx$ | 0.785398 | 0.78333 | 0.00206 |

###  Conclusiones del Análisis
* **Exactitud Perfecta:** El método muestra un error de `0.0` en polinomios de hasta tercer grado (ver Ejercicio 2).
* **Alta Precisión:** En funciones trascendentes, el margen de error es mínimo (orden de $10^{-3}$ a $10^{-6}$).
* **Desviación:** En el Ejercicio 3 se nota una desviación mayor (~1.33). Esto ocurre porque la función tiene variaciones bruscas y es de grado 4 ($5x^4$), lo que un solo segmento (Simpson 1/3 simple) no logra capturar. Esto sugiere el uso de **Simpson 1/3 Compuesto**.
