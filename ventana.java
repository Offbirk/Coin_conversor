import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana frame = new ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ventana() {
		/**
		 * @param Carga los datos a mostrar en la interfaz grafica
		 */
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Conversor Java ONE - Alura G5");
		lblNewLabel.setBounds(110, 31, 329, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Elija la opción de conversión que deseas utilizar:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(87, 76, 352, 14);
		contentPane.add(lblNewLabel_1);
		
		BotonMoneda();
		BotonTemperatura();				
		BotonMedidasLongitud();		
	}
	
	private void BotonMoneda() {
		/**
		 * Boton que instancia a la clase Conversor1 y su interfaz grafica
		 */
		JButton btnNewButton = new JButton("Conversor de moneda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conversor1 conversor = new Conversor1();
				conversor.setBounds(100, 100, 550, 400);
				conversor.setVisible(true);
			}
		});
		
		btnNewButton.setBounds(169, 126, 186, 23);
		contentPane.add(btnNewButton);
	}
	
	private void BotonTemperatura() {
		/**
		 * Boton que instancia a la clase Temperatura y su interfaz grafica
		 */
		JButton btnNewButton_1 = new JButton("Conversor de temperatura");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Temperatura temperatura = new Temperatura();
				temperatura.setBounds(100, 100, 550, 400);
				temperatura.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(169, 160, 186, 23);
		contentPane.add(btnNewButton_1);
	}
	
	private void BotonMedidasLongitud() {
		/**
		 * Boton que instancia a la clase Longitud y su interfaz grafica
		 */
		JButton btnNewButton_2 = new JButton("Conversor de longitud");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Longitud longitud = new Longitud();
				longitud.setBounds(100, 100, 550, 400);
				longitud.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(169, 194, 186, 23);
		contentPane.add(btnNewButton_2);
	}
}
