import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class Longitud extends JFrame implements ItemListener, ActionListener {
	private JComboBox<String> comboBox_1;
	private JTextField textField;
	private JButton botonAceptar;
	
	public Longitud() {
		
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Conversor de unidades de longitud");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(56, 11, 365, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese el valor que desea convertir:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(120, 49, 237, 25);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(100, 85, 278, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(196, 190, 89, 23);
		getContentPane().add(botonAceptar);
		
		String[] opciones2 = {"De km a m" , "De km a hm" , "De dam a m" , "De m a dm" , "De dm a cm", "De cm a mm"};
		comboBox_1 = new JComboBox<>(opciones2);
		comboBox_1.setBounds(194, 132, 89, 22);
		getContentPane().add(comboBox_1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
