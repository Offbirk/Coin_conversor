import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.lang.String;

/***
 * 
 * @author Brian
 * 
 */

public class Conversor1 extends JFrame implements ItemListener, ActionListener {
    private JComboBox<String> comboBox;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton botonVerificar;
	private JButton botonConsultar;
	public static JSONObject rates;
	private double USD;
	private double EUR = 1;
	private double GBP;
	private double COP;
	private double JPY;
	private double KRW;

	public Conversor1() {
		/**
		 * @param Aplicacion que convierte la divisa de un país en la divisa de otro según la opción seleccionada
		 * @return El valor retornado es el producto de la conversion de valores estaticos
		 */
		getContentPane().setLayout(null);
        JLabel label1 = new JLabel("Conversor de moneda");
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
		botonConsultar();
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

	/* {
		"success": true,
		"timestamp": 1519296206,
		"base": "USD",
		"date": "2025-01-18",
		"rates": {
					"GBP": 0.72007,
					"JPY": 107.346001,
					"EUR": 0.813399,
				 }
		}
	*/
	private URL consultarAPI() {
		URL url = null; //quitar la inicialización en null
		try {
			url = new URL("https://data.fixer.io/api/latest?access_key="+ System.getenv("API_KEY")+"&base=EUR&symbols=COP,USD,JPY,KRW,GBP");
			System.out.println("URL: " + url);
			// Abrir la conexión, enviarle el metodo de petición HTTP
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();

			//Verificar el estado de la respuesta de la API
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Http response code" + responseCode);
			} else {
				JSONObject json = getJsonObject(url);
				System.out.println("JSON:" + json);
				// Se utiliza el metodo getString() para obtener el valor de la clave x, cuando no se está seguro se usa optString() en su lugar.
				rates = json.optJSONObject("rates");
				System.out.println("RATES: " + rates);

				// Obtener las equivalencias de las monedas (USD, COP, JPY, KRW)
				if(rates != null) {
					USD = rates.optDouble("USD");
					GBP = rates.optDouble("GBP");
					COP = rates.optDouble("COP");
					JPY = rates.optDouble("JPY");
					KRW = rates.optDouble("KRW");
				} else {
					System.out.println("El valor del JSON rates no existen, retorna null en las monedas");
				}
			}
		} catch (IOException e) {
			System.err.println("Ocurrio un error: " + e.getMessage());
		}
		return url;
	}

	private static JSONObject getJsonObject(URL url) throws IOException {
		StringBuilder response = new StringBuilder();
		Scanner scanner = new Scanner(url.openStream());
		while (scanner.hasNext()) {
			response.append(scanner.nextLine());
		}
		scanner.close();

		JSONObject json = new JSONObject(response.toString());
		return json;
	}

	private void botonConsultar() {
		botonConsultar = new JButton("consultar API");
		botonConsultar.setBounds(158, 226, 180, 22);
		getContentPane().add(botonConsultar);

        botonConsultar.addActionListener(e -> {
			String opcionSeleccionada = (String) comboBox.getSelectedItem();
				if (opcionSeleccionada.equals("De Pesos a Dolar")) {
					consultarAPI();
					Convert(COP, USD);
				} else if (opcionSeleccionada.equals("De Pesos a Euro")) {
					consultarAPI();
					Convert(COP, EUR);
                }else if (opcionSeleccionada.equals("De Pesos a Libras")) {
					consultarAPI();
					Convert(COP, GBP);
				} else if (opcionSeleccionada.equals("De Pesos a Yen")) {
					consultarAPI();
					Convert(COP, JPY);
				} else if (opcionSeleccionada.equals("De Pesos a Won Coreano")) {
					consultarAPI();
					Convert(COP, KRW);
				} else if (opcionSeleccionada.equals("De Dolar a Pesos")) {
					consultarAPI();
					Convert(USD, COP);
				} else if (opcionSeleccionada.equals("De Euro a Pesos")) {
					consultarAPI();
					Convert(EUR, COP);
				} else if (opcionSeleccionada.equals("De Libras a Pesos")) {
					consultarAPI();
					Convert(GBP, COP);
				} else if (opcionSeleccionada.equals("De Yen a Pesos")) {
					consultarAPI();
					Convert(JPY, COP);
				} else if (opcionSeleccionada.equals("De Won Coreano a Pesos")) {
					consultarAPI();
					Convert(KRW, COP);
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
		});
	};

	public double getValor() {
		/**
		 * @param Este metodo permite obtener el valor registrado en el campo textField y convertirlo a un valor de tipo double
		 */
		String solicitud = textField.getText();
		double valor = Double.parseDouble(solicitud);
		return valor;
	}
	
	/**
	 * @param "Los metodos a continuacion realizan una conversion de un valor estatico obteniendo un valor ingresado en textField y ejecutando una
	 *             operacion de conversion"
	 * @return A traves de un mensaje muestran los resultados de cada una de las conversiones
	 */

	private void Convert(double initialValue, double target) {
		//valor inicial sea mayor que base y que base sea mayor que target = DIVIDIR ambas
		//valor inicial sea menor que base y que base sea menor que target = MULTIPLICAR ambas
		//valor inicial sea mayor que base y que base sea menor que target = 1°divido 2° multiplico
		//valor inicial sea menor que base y que base sea mayor que target = 1° multiplicar 2° dividir
		double base = 1;
		double resultado;
		double valor = getValor();
		if(initialValue > base & base > target) {
			resultado = (valor / initialValue)/ target;
			System.out.println("Ambos son mayores:" + resultado);
		} else if (initialValue < base & base < target) {
			resultado = (valor * initialValue) * target;
			System.out.println("Ambos son menores:" + resultado);
		} else if(initialValue > base & base < target) {
			resultado = (valor / initialValue) * target;
			System.out.println("Valor inicial es mayor y la base es menor que target:" + resultado);
		} else if (initialValue < base & base > target) {
			resultado = (valor * initialValue) / target;
			System.out.println("Valor inicial es menor y la base es mayor que target:" + resultado);
		} else {
			resultado = valor/initialValue;
			System.out.println("La base es igual a 1:" + resultado);
		}
		JOptionPane.showMessageDialog(null, "El valor ingresado corresponde a: $" + String.format("%.2f", resultado));
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
	                    botonConsultar.setEnabled(true);
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