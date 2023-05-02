package calculadora.guide;

import javax.swing.*;
import java.util.Stack;

import static calculadora.guide.Operaciones.aplicarOperacion;

public class EvaluadorExpresion extends JFrame  {

    static double evaluarExpresion(String expresion) {
        // Evaluar la expresión matemática
        Stack<Double> pilaOperandos = new Stack<>();
        Stack<Character> pilaOperadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            if (Character.isDigit(c)) {
                // Si el caracter es un dígito, agregar el número a la pila de operandos
                StringBuilder numero = new StringBuilder();
                while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                    numero.append(expresion.charAt(i));
                    i++;
                }
                i--;
                double valor = Double.parseDouble(numero.toString());
                pilaOperandos.push(valor);
            } else if (c == '(') {
                // Si el caracter es un paréntesis de apertura, agregarlo a la pila de operadores
                pilaOperadores.push(c);
            } else if (c == ')') {
                // Si el caracter es un paréntesis de cierre, resolver la expresión dentro de los paréntesis
                while (pilaOperadores.peek() != '(') {
                    double segundoOperando = pilaOperandos.pop();
                    double primerOperando = pilaOperandos.pop();
                    char operador = pilaOperadores.pop();
                    double resultado = aplicarOperacion(primerOperando, segundoOperando, operador);
                    pilaOperandos.push(resultado);
                }
                pilaOperadores.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // Si el caracter es un operador, resolver las operaciones pendientes de mayor precedencia
                while (!pilaOperadores.empty() && tienePrecedencia(c, pilaOperadores.peek())) {
                    double segundoOperando = pilaOperandos.pop();
                    double primerOperando = pilaOperandos.pop();
                    char operador = pilaOperadores.pop();
                    double resultado = aplicarOperacion(primerOperando, segundoOperando, operador);
                    pilaOperandos.push(resultado);
                }
                pilaOperadores.push(c);
            }
        }

        // Resolver las operaciones restantes
        while (!pilaOperadores.empty()) {
            double segundoOperando = pilaOperandos.pop();
            double primerOperando = pilaOperandos.pop();
            char operador = pilaOperadores.pop();
            double resultado = aplicarOperacion(primerOperando, segundoOperando, operador);
            pilaOperandos.push(resultado);
        }

        return pilaOperandos.pop();
    }

    private static boolean tienePrecedencia(char operador1, char operador2) {
        // Verificar si el primer operador tiene mayor precedencia que el segundo operador
        return (operador1 == '*' || operador1 == '/') && (operador2 == '+' || operador2 == '-');
    }
    static boolean verificarBalanceo(String expresion) {
        // Verificar si la expresión está balanceada en paréntesis o corchetes
        Stack<Character> pila = new Stack<>();
        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            if (c == '(' || c == '[') {
                pila.push(c);
            } else if (c == ')' || c == ']') {
                if (pila.empty()) {
                    return false;
                } else if ((c == ')' && pila.peek() == '(') || (c == ']' && pila.peek() == '[')) {
                    pila.pop();
                } else {
                    return false;
                }
            }
        }
        return pila.empty();
    }
}
