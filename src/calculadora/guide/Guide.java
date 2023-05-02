package calculadora.guide;

import calculadora.guide.EvaluadorExpresion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class Guide extends JFrame  {

    public void returnar(){
        // Crear la interfaz gráfica
        JFrame ventana = new JFrame("Calculadora");
        ventana.setSize(300, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        // Crear el campo de texto para la expresión
        JTextField campoTexto = new JTextField();
        campoTexto.setHorizontalAlignment(JTextField.RIGHT);
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 18));
        ventana.add(campoTexto, BorderLayout.NORTH);

        // Crear el panel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(4, 4));
        ventana.add(panelBotones, BorderLayout.CENTER);

        // Agregar los botones numéricos
        IntStream.rangeClosed(1, 9).mapToObj(i -> new JButton(Integer.toString(i))).forEachOrdered(boton -> {
            boton.setFont(new Font("Arial", Font.PLAIN, 18));
            boton.addActionListener(e -> campoTexto.setText(campoTexto.getText() + boton.getText()));
            panelBotones.add(boton);
        });

        // Agregar el botón de cero
        JButton botonCero = new JButton("0");
        botonCero.setFont(new Font("Arial", Font.PLAIN, 18));
        botonCero.addActionListener(e -> campoTexto.setText(campoTexto.getText() + "0"));
        panelBotones.add(botonCero);

        // Agregar el botón de punto decimal
        JButton botonDecimal = new JButton(".");
        botonDecimal.setFont(new Font("Arial", Font.PLAIN, 18));
        botonDecimal.addActionListener(e -> campoTexto.setText(campoTexto.getText() + "."));
        panelBotones.add(botonDecimal);

        JButton botonSuma = new JButton("+");
        botonSuma.setFont(new Font("Arial", Font.PLAIN, 18));
        botonSuma.addActionListener(e -> campoTexto.setText(campoTexto.getText() + "+"));
        panelBotones.add(botonSuma);

        JButton botonResta = new JButton("-");
        botonResta.setFont(new Font("Arial", Font.PLAIN, 18));
        botonResta.addActionListener(e -> campoTexto.setText(campoTexto.getText() + "-"));
        panelBotones.add(botonResta);

        JButton botonMultiplicacion = new JButton("*");
        botonMultiplicacion.setFont(new Font("Arial", Font.PLAIN, 18));
        botonMultiplicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campoTexto.setText(campoTexto.getText() + "*");
            }
        });
        panelBotones.add(botonMultiplicacion);

        JButton botonDivision = new JButton("/");
        botonDivision.setFont(new Font("Arial", Font.PLAIN, 18));
        botonDivision.addActionListener(e -> campoTexto.setText(campoTexto.getText() + "/"));
        panelBotones.add(botonDivision);

        JButton botonIgual = new JButton("=");
        botonIgual.setFont(new Font("Arial", Font.PLAIN, 18));
        botonIgual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener la expresión del campo de texto
                String expresion = campoTexto.getText();
                // Verificar si la expresión está balanceada en paréntesis o corchetes
                if (!EvaluadorExpresion.verificarBalanceo(expresion)) {
                    JOptionPane.showMessageDialog(null, "La expresión no está balanceada en paréntesis o corchetes", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    JOptionPane.showMessageDialog(null, "La expresión esta balaneceada", "Ok", JOptionPane.INFORMATION_MESSAGE);
                }
                // Evaluar la expresión
                double resultado = EvaluadorExpresion.evaluarExpresion(expresion);
                // Mostrar el resultado en el campo de texto
                campoTexto.setText(Double.toString(resultado));
            }
        });
        panelBotones.add(botonIgual);

        // Mostrar la ventana
        ventana.setVisible(true);
    }
}
