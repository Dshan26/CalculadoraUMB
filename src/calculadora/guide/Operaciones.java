package calculadora.guide;

public class Operaciones {
    static double aplicarOperacion(double a, double b, char operador) {
        // Aplicar la operación matemática correspondiente
        if (operador == '+') {
            return a + b;
        } else {
            if (operador == '-') {
                return a - b;
            } else if (operador == '*') {
                return a * b;
            } else if (operador == '/') {
                return a / b;
            } else {
                return 0;
            }
        }
    }
}
