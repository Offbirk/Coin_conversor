import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Temperatura extends JFrame implements ItemListener, ActionListener {
	private JComboBox<String> comboBox;
	private JTextField textField;
	private JButton botonAceptar;
	
	public Temperatura() {
		/**
		 * Constructor con los elementos para la recoleccion de datos y eleccion de conversion de temperatura
		 */
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Conversor de temperatura");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(78, 11, 278, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese el valor de la temperatura que desea convertir:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(43, 49, 347, 25);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(78, 85, 278, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		opcionesDeTemperatura();
		botonAceptar();
		
	}
	
	private void opcionesDeTemperatura() {
		/**
		 * Muestra las opciones de conversion de temperatura y la opcion inversa
		 */
		String[] opciones1 = {"De Celsius a Fahrenheit" , "De Celsius a Kelvin" , "De Fahrenheit a Celsius", 
				"De Fahrenheit a Kelvin", "De Kelvin a Celsius", "De Kelvin a Fahrenheit"};
		comboBox = new JComboBox<>(opciones1);
		comboBox.setBounds(132, 120, 169, 22);
		getContentPane().add(comboBox);
	}
	
	private double getValor() {
		/**
		 * Con este metodo se obtiene el valor ingresado en el campo textField y lo convierte a un valor tipo double
		 */
		String solicitud = textField.getText();
		double valor = Double.parseDouble(solicitud);
		return valor;
	}
	/**
	 * @param Los metodos a continuacion muestran la conversion de una unidad de temperatura a otra siguiendo las formulas establecidas en el sistema
	 * Internacional
	 * @return cada metodo retorno un resultado por ventana flotante que sera llamado en el ActionListener del boton Aceptar  
	 */
	
	private void DeCelsiusAFahrenheit() {
		double valor = getValor();
		double conversion = (valor * 9 / 5) + 32;
		JOptionPane.showMessageDialog(null, valor + " °C equivale a: " + conversion + " °F");
	}
	
	private void DeCelsiusAKelvin() {	
		double valor = getValor();
		double conversion = valor + 273.15;
		JOptionPane.showMessageDialog(null, valor + " °C equivale a: " + conversion + " °K");
	}
	
	private void DeFahrenheitACelsius() {
		double valor = getValor();		
		double conversion = (valor - 32) * 5/9;
		JOptionPane.showMessageDialog(null, valor + " °F equivale a: " + conversion + " °C");
	}
	
	private void DeFahrenheitAKelvin() {
		double valor = getValor();		
		double conversion = (valor - 32) * 5/9 + 273.15;
		JOptionPane.showMessageDialog(null, valor + " °F equivale a: " + conversion + " °K");
	}
	
	private void DeKelvinACelsius() {
		double valor = getValor();
		double conversion = valor - 273.15;
		JOptionPane.showMessageDialog(null, valor + " °K equivale a: " + conversion + " °C");
	}
	
	private void DeKelvinAFahrenheit() {
		double valor = getValor();
		double conversion = (valor - 273.15) * 9/5 + 32;
		JOptionPane.showMessageDialog(null, valor + " °K equivale a: " + conversion + " °F");
	}
	
	private void verificarValor() {
		/**
		 * Este metodo utiliza un ciclo while para verificar si el valor ingresado corresponde a un dato de tipo numerico, caso contrario lanza una excepcion
		 * y muestra una ventana flotante solicitando si quiere continuar o terminar el programa 
		 */
		boolean valido = false;
		
		while(!valido) {
			try {
				String input = textField.getText();
				ValidationUtils.isValidNumber(input);
				ValidationUtils.parseDouble(input);
			valido = true;
			} 		
			catch (NumberFormatException ex) {
				int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Error: Valor no válido. Debes ingresar un número válido.\n¿Deseas intentar de nuevo?",
                        "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                
                if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                	textField.setText(null);
                	textField.requestFocus();
                    valido = true;
			}
			}
		}
	}
	
	private void botonAceptar() {
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(172, 169, 89, 23);
		getContentPane().add(botonAceptar);
		
		botonAceptar.addActionListener(new ActionListener() {
			/**
			 * Con este evento se llama al metodo de verificacion de datos ingresados y realiza una verificacion de la opcion escogida en el comboBox
			 * de acuerdo a la opcion ejecuta los diferentes metodos de conversion y muestra por ventana flotante el resultado de la operacion
			 */
			public void actionPerformed(ActionEvent e) {
				verificarValor();
				String opcionSeleccionada = (String) comboBox.getSelectedItem();
				if (opcionSeleccionada.equals("De Celsius a Fahrenheit")) {
					DeCelsiusAFahrenheit();
				} else if (opcionSeleccionada.equals("De Celsius a Kelvin")) {
					DeCelsiusAKelvin();
				} else if (opcionSeleccionada.equals("De Fahrenheit a Celsius")) {
					DeFahrenheitACelsius();
				} else if (opcionSeleccionada.equals("De Fahrenheit a Kelvin")) {
					DeFahrenheitAKelvin();
				} else if (opcionSeleccionada.equals("De Kelvin a Celsius")) {
					DeKelvinACelsius();
				} else if (opcionSeleccionada.equals("De Kelvin a Fahrenheit")) {
					DeKelvinAFahrenheit();
				}	
				
			boolean continuarOption = false;
			while (!continuarOption) {
				int continuar = JOptionPane.showConfirmDialog(null, "Quieres continuar", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (continuar == 1 || continuar == 2) {
					JOptionPane.showMessageDialog(null, "Programa finalizado");
					System.exit(0);
				}
				textField.setText(null);
				textField.requestFocus();
				continuarOption = true;
			}
			}
		});
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {			
	}

	@Override
	public void itemStateChanged(ItemEvent e) {				
	}
}
