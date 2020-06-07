package albino.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import albino.entity.PessoaEntity;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class ContatoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public ContatoView(PessoaEntity pessoa) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		setTitle("Contato: "+pessoa.getNome());
		setBounds(100, 100, 450, 315);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			JLabel label = new JLabel("Nome:");
			label.setBounds(10, 14, 69, 16);
			contentPanel.add(label);
			
		}
		{
			JLabel label = new JLabel("Celular:");
			label.setBounds(10, 44, 69, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Telefone:");
			label.setBounds(10, 74, 69, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("E-mail:");
			label.setBounds(10, 104, 69, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Anivers\u00E1rio:");
			label.setBounds(10, 134, 69, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Endere\u00E7o:");
			label.setBounds(10, 194, 69, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Cidade:");
			label.setBounds(10, 224, 69, 16);
			contentPanel.add(label);
		}
		{
			JLabel labelUF = new JLabel("UF:  "+pessoa.getUf());
			if (labelUF.getText().equals("UF:  ")) {
				labelUF.setVisible(false);				
			}
			labelUF.setBounds(342, 224, 82, 16);
			contentPanel.add(labelUF);
		}
		
		JLabel labelNome = new JLabel(pessoa.getNome());
		labelNome.setBounds(89, 15, 335, 14);
		contentPanel.add(labelNome);
		
		JLabel labelCelular = new JLabel(pessoa.getCelular());
		labelCelular.setBounds(89, 45, 335, 14);
		contentPanel.add(labelCelular);
		
		JLabel labelTelefone = new JLabel(pessoa.getTelefone());
		labelTelefone.setBounds(89, 75, 335, 14);
		contentPanel.add(labelTelefone);
		
		JLabel labelAniversario = new JLabel(sdf.format(pessoa.getAniversario()));
		labelAniversario.setBounds(89, 135, 335, 14);
		contentPanel.add(labelAniversario);
		
		JLabel labelEndereco = new JLabel(pessoa.getEndereco());
		labelEndereco.setBounds(89, 194, 335, 14);
		contentPanel.add(labelEndereco);
		
		JLabel labelCidade = new JLabel(pessoa.getCidade());
		labelCidade.setBounds(89, 224, 243, 14);
		contentPanel.add(labelCidade);
		{
			JButton mandarEmailBotao = new JButton("<html><font color='#0000ff'><u>"+pessoa.getEmail()+"</u></font></html>");
			mandarEmailBotao.setHorizontalAlignment(SwingConstants.LEFT);
			mandarEmailBotao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent i) {
					if (mandarEmailBotao.getText().equals("<html><font color='#0000ff'><u></u></font></html>")) {
						
						mandarEmailBotao.setEnabled(false);
						mandarEmailBotao.setVisible(false);
						
					}else {
						try {
							Desktop.getDesktop().browse(new URI("mailto:"+pessoa.getEmail()));
						} catch (IOException e) {
							e.printStackTrace();
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
					}
				}
			});
			mandarEmailBotao.setBounds(71, 101, 353, 23);
			mandarEmailBotao.setBackground(new Color(240,240,240));
			mandarEmailBotao.setBorderPainted(false);
			contentPanel.add(mandarEmailBotao);
		}
		{
			JLabel lblPas = new JLabel("Pa\u00EDs:");
			lblPas.setBounds(10, 164, 69, 16);
			contentPanel.add(lblPas);
		}
		{
			JLabel labelPais = new JLabel(pessoa.getPais());
			labelPais.setBounds(89, 164, 335, 16);
			contentPanel.add(labelPais);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
}
