import javax.swing.*;
import java.awt.event.*;
import java.io.ObjectInputStream.GetField;
import java.text.DecimalFormat;
import java.awt.Font;

/***
 * 
 * @author Brian
 * 
 */

public class Conversor1 extends JFrame implements ItemListener, ActionListener {
	private JLabel label1;
	private JComboBox<String> comboBox;
	private JButton botonAceptar;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton botonVerificar;

	public Conversor1() {
		/**
		 * @param Aplicacion que convierte la divisa de un país en la divisa de otro según la opción seleccionada
		 * @return El valor retornado es el producto de la conversion de valores estaticos
		 */
		getContentPane().setLayout(null);
		label1 = new JLabel("Conversor de moneda");
		label1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label1.setBounds(140, 20, 250, 20);
		getContentPane().add(label1);
		
		lblNewLabel = new JLabel("Ingrese la cantidad de dinero a convertir:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(57, 62, 262, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(57, 87, 257, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Escoja una opción de conversión de moneda:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(115, 132, 300, 14);
		getContentPane().add(lblNewLabel_1);
		
		opcionesDeMoneda();					
		
		botonVerificar();
		botonAceptar();		
	}		
	
	private void opcionesDeMoneda() {
		/**
		 * @param Componente con las diferentes opciones de conversion de monedas
		 */
		String[] opciones = { "De Pesos a Dolar", "De Pesos a Euro", "De Pesos a Libras", "De Pesos a Yen", "De Pesos a Won Coreano", "De Dolar a Pesos", 
				"De Euro a Pesos", "De Libras a Pesos", "De Yen a Pesos", "De Won Coreano a Pesos" };
		comboBox = new JComboBox<>(opciones);
		comboBox.setBounds(158, 157, 190, 22);
		comboBox.setEnabled(false);
		getContentPane().add(comboBox);		
	}
	
	public double getValor() {
		/**
		 * @param Este metodo permite obtener el valor registrado en el campo textField y convertirlo a un valor de tipo double
		 */
		String solicitud = textField.getText();
		double valor = Double.parseDouble(solicitud);
		return valor;
	}
	
	/**
	 * @param Los metodos a continuacion realizan una conversion de un valor estatico obteniendo un valor ingresado en textField y ejecutando una 
	 * operacion de conversion
	 * @return A traves de un mensaje muestran los resultados de cada una de las conversiones
	 */
	
	private void DePesosADolar() {		
		double valor = getValor();
		double dolar = 4051.22;
		double conversion = valor/dolar;		
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " USD");
	}
	
	private void DePesosAEuro() {
		double valor = getValor();
		double euro = 4443.14;
		double conversion = valor/euro;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " EUR");
	}
	
	private void DePesosALibras() {
		double valor = getValor();
		double libras = 5166.52;
		double conversion = valor/libras;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " LIB");
	}
	
	private void DePesosAYen() {
		double valor = getValor();
		double yen = 28.28;
		double conversion = valor/yen;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " YEN");
	}
	
	private void DePesosAWonCoreano() {
		double valor = getValor();
		double wonCoreano = 3.07;
		double conversion = valor/wonCoreano;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " WON");
	}
	
	private void DeDolarAPesos() {
		double valor = getValor();
		double pesos = 4051.22;
		double conversion = valor * pesos;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " COP");		
	}
	
	private void DeEuroAPesos() {
		double valor = getValor();
		double pesos = 4443.14;
		double conversion = valor * pesos;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " COP");
	}
	
	private void DeLibrasAPesos() {
		double valor = getValor();
		double pesos = 5166.52;
		double conversion = valor * pesos;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " COP");
	}
	
	private void DeYenAPesos() {
		double valor = getValor();
		double pesos = 28.28;
		double conversion = valor * pesos;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " COP");
	}
	
	private void DeWonCoreanoAPesos() {
		double valor = getValor();
		double pesos = 3.07;
		double conversion = valor * pesos;
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + conversion + " COP");
	}
	
	private void botonVerificar() {		
		botonVerificar = new JButton("Continuar");
		botonVerificar.setBounds(334, 86, 100, 23);
		getContentPane().add(botonVerificar);
		
		botonVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * @param Con este evento se verifica que la entrada en el textField solo contenga numeros, caso contrario lanzara excepciones y se muestra por
				 * salida un showConfirmDialog para continuar o terminar el programa, mediante un ciclo while se verifica si el valor es valido, cuando cumple la
				 * condicion habilita las opciones de conversion y el boton Aceptar
				 */
				boolean valido = false;
	            double valor = 0.0;

	            while (!valido) {
	                try {	                	
	                    String input = textField.getText();
	                    if (input.isEmpty() || !input.matches("-?\\d+(\\.\\d+)?")) {
	                        throw new NumberFormatException();
	                    }

	                    valor = Double.parseDouble(input);

	                    if (valor < 0) {
	                        throw new ValorNoValidoException("El valor ingresado no puede ser negativo");
	                    }
	                    valido = true;
	                    comboBox.setEnabled(true);
	                    botonAceptar.setEnabled(true);
	                } catch (NumberFormatException ex) {
	                    int choice = JOptionPane.showConfirmDialog(
	                            null,
	                            "Error: Valor no válido. Debes ingresar un número válido.\n¿Deseas intentar de nuevo?",
	                            "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
	                    
	                    if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
	                    	textField.setText(null);
	                    	textField.requestFocus();
	                        valido = true; // Exit the loop if the user chooses not to continue
	                    }
	                } catch (ValorNoValidoException e1) {
	                    int choice = JOptionPane.showConfirmDialog(
	                            null,
	                            "Error: Valor no válido. " + e1.getMessage() + "\n¿Deseas intentar de nuevo?",
	                            "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

	                    if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
	                    	textField.setText(null);
	                    	textField.requestFocus();
	                        valido = true; // Exit the loop if the user chooses not to continue
	                    }
	                }
	            }
	        }
	    });
	}
	
	private void botonAceptar() {
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(215, 206, 100, 30);
		botonAceptar.setEnabled(false);
		getContentPane().add(botonAceptar);
		
		botonAceptar.addActionListener(new ActionListener() {
			/**
			 * @param Con este evento se identifica que opcion fue elegida del listado de opciones y llama al metodo de conversion correspondiente
			 */
			public void actionPerformed(ActionEvent e) {
				String opcionSeleccionada = (String) comboBox.getSelectedItem(); 
				if (opcionSeleccionada.equals("De Pesos a Dolar")){
					DePesosADolar();
				} else if (opcionSeleccionada.equals("De Pesos a Euro")) {
					DePesosAEuro(); 
				} else if (opcionSeleccionada.equals("De Pesos a Libras")) {
					DePesosALibras(); 
				} else if (opcionSeleccionada.equals("De Pesos a Yen")) {
					DePesosAYen(); 
				} else if (opcionSeleccionada.equals("De Pesos a Won Coreano")) {
					DePesosAWonCoreano(); 
				} else if (opcionSeleccionada.equals("De Dolar a Pesos")) {
					DeDolarAPesos(); 
				} else if (opcionSeleccionada.equals("De Euro a Pesos")) {
					DeEuroAPesos(); 
				} else if (opcionSeleccionada.equals("De Libras a Pesos")) {
					DeLibrasAPesos();
				} else if (opcionSeleccionada.equals("De Yen a Pesos")) {
					DeYenAPesos();
				} else if (opcionSeleccionada.equals("De Won Coreano a Pesos")) {
					DeWonCoreanoAPesos();
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
	public void itemStateChanged(ItemEvent e) {
		
	}

	public static void main(String[] args) {
		/**
		 * Metodo principal que carga la ventana con la interfaz grafica
		 */
		SwingUtilities.invokeLater(() -> {
		Conversor1 ventana = new Conversor1();
		ventana.setTitle("Conversor de moneda");
		// ventana.setLocationRelativeTo(null); define la posicion en que siempre aparecera la aplicacion.
		ventana.setBounds(200, 120, 550, 400); // configura la ventana con medidas en columna, fila, ancho y largo.
		ventana.setResizable(false); // impide redimensionar la ventana
		ventana.setVisible(true); // hace visible la ventana.
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	});
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}