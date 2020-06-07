package albino.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import albino.dao.PessoaDao;
import albino.entity.PessoaEntity;
import albino.utils.TextosUteis;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class ContatoEditarView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final 	JPanel 				contentPanel = new JPanel();
	private 		JTextField 			editarNomeTF;
	private 		JTextField 			editarCelularTF;
	private 		JTextField 			editarTelefoneTF;
	private 		JTextField			editarEmailTF;
	private 		JTextField 			editarEnderecoTF;
	private 		JTextField  		editarCidadeTF;
	private static	JComboBox<String> 	paisesEditarCB;
	private static	JComboBox<String>	ufEditarCB; 
	private static  JCheckBox 			checkBoxEstrangeiroEditar;

	/**
	 * Create the dialog.
	 */


	public ContatoEditarView(PessoaEntity pessoa) {

		setTitle("Editar Contato");
		setBounds(100, 100, 450, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 14, 63, 16);
		contentPanel.add(label);

		editarNomeTF = new JTextField();
		editarNomeTF.setColumns(10);
		editarNomeTF.setBounds(83, 12, 341, 20);
		contentPanel.add(editarNomeTF);

		editarNomeTF.setText(pessoa.getNome());

		JLabel label_1 = new JLabel("Celular:");
		label_1.setBounds(10, 44, 63, 16);
		contentPanel.add(label_1);

		editarCelularTF = new JTextField();
		editarCelularTF.setColumns(10);
		editarCelularTF.setBounds(83, 42, 341, 20);
		contentPanel.add(editarCelularTF);

		editarCelularTF.setText(pessoa.getCelular());

		JLabel label_2 = new JLabel("Telefone:");
		label_2.setBounds(10, 74, 63, 16);
		contentPanel.add(label_2);

		editarTelefoneTF = new JTextField();
		editarTelefoneTF.setColumns(10);
		editarTelefoneTF.setBounds(83, 72, 341, 20);
		contentPanel.add(editarTelefoneTF);

		editarTelefoneTF.setText(pessoa.getTelefone());

		JLabel label_3 = new JLabel("E-mail:");
		label_3.setBounds(10, 104, 63, 16);
		contentPanel.add(label_3);

		editarEmailTF = new JTextField();
		editarEmailTF.setColumns(10);
		editarEmailTF.setBounds(83, 102, 341, 20);
		contentPanel.add(editarEmailTF);

		editarEmailTF.setText(pessoa.getEmail());

		JLabel label_4 = new JLabel("Anivers\u00E1rio:");
		label_4.setBounds(10, 134, 72, 16);
		contentPanel.add(label_4);



		JLabel label_5 = new JLabel("Endere\u00E7o:");
		label_5.setBounds(10, 244, 63, 16);
		contentPanel.add(label_5);

		editarEnderecoTF = new JTextField();
		editarEnderecoTF.setColumns(10);
		editarEnderecoTF.setBounds(83, 242, 341, 20);
		contentPanel.add(editarEnderecoTF);

		editarEnderecoTF.setText(pessoa.getEndereco());

		JLabel labelUF = new JLabel("UF:");
		labelUF.setBounds(344, 273, 41, 16);
		contentPanel.add(labelUF);
		//		
		//		editarUfTF = new JTextField();
		//		editarUfTF.setColumns(10);
		//		editarUfTF.setBounds(383, 271, 41, 20);
		//		contentPanel.add(editarUfTF);
		//		
		//		editarUfTF.setText(pessoa.getUf());

		JLabel label_7 = new JLabel("Cidade:");
		label_7.setBounds(10, 271, 63, 16);
		contentPanel.add(label_7);

		editarCidadeTF = new JTextField();
		editarCidadeTF.setColumns(10);
		editarCidadeTF.setBounds(83, 269, 239, 20);
		contentPanel.add(editarCidadeTF);

		editarCidadeTF.setText(pessoa.getCidade());

		JDateChooser editarAniversarioDC = new JDateChooser();
		editarAniversarioDC.setBounds(83, 131, 112, 20);
		contentPanel.add(editarAniversarioDC);

		editarAniversarioDC.setDate(pessoa.getAniversario());

		JLabel lblPas = new JLabel("Pa\u00EDs:");
		lblPas.setBounds(10, 217, 63, 16);
		contentPanel.add(lblPas);

		paisesEditarCB = new JComboBox<String>();
		paisesEditarCB.setModel(new DefaultComboBoxModel<String>(TextosUteis.paises()));
		paisesEditarCB.setBackground(Color.WHITE);
		paisesEditarCB.setBounds(83, 215, 341, 20);
		paisesEditarCB.setEnabled(!pessoa.getPais().equals("Brasil"));
		paisesEditarCB.setVisible(!pessoa.getPais().equals("Brasil"));
		contentPanel.add(paisesEditarCB);
		paisesEditarCB.setSelectedItem(pessoa.getPais());

		checkBoxEstrangeiroEditar = new JCheckBox("Estrangeiro(a)");
		checkBoxEstrangeiroEditar.setSelected(!pessoa.getPais().equals("Brasil"));
		checkBoxEstrangeiroEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				verificarCheckBox();

			}
		});
		checkBoxEstrangeiroEditar.setBounds(6, 171, 112, 24);
		contentPanel.add(checkBoxEstrangeiroEditar);

		JLabel lblBrasil = new JLabel("Brasil");
		lblBrasil.setBounds(83, 217, 46, 14);
		contentPanel.add(lblBrasil);

		ufEditarCB = new JComboBox<String>();
		ufEditarCB.setModel(new DefaultComboBoxModel<String>(TextosUteis.estados()));
		ufEditarCB.setBounds(368, 271, 56, 20);
		ufEditarCB.setVisible(!checkBoxEstrangeiroEditar.isSelected());
		ufEditarCB.setSelectedItem(pessoa.getUf());
		contentPanel.add(ufEditarCB);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (editarNomeTF.getText().equals("") || editarCelularTF.getText().equals("") || editarAniversarioDC.getDate()==null) {

							JOptionPane.showMessageDialog(null, "O contato deve conter: NOME, CELULAR e ANIVERSÁRIO.");

						}else {

							//TODO BOTÃO OK 

							pessoa.setNome(editarNomeTF.getText());
							pessoa.setCelular(editarCelularTF.getText());
							pessoa.setTelefone(editarTelefoneTF.getText());
							pessoa.setEmail(editarEmailTF.getText());
							pessoa.setAniversario(editarAniversarioDC.getDate());
							if (pessoa.getPais().equals("Brasil") && checkBoxEstrangeiroEditar.isSelected()) {
								System.out.println("brasileiro para estrangeiro entrou");								
								pessoa.setPais(paisesEditarCB.getSelectedItem().toString());
								pessoa.setUf("");
							} else if (!checkBoxEstrangeiroEditar.isSelected()){

								System.out.println("brasileiro entrou");
								pessoa.setPais("Brasil");
								pessoa.setUf(ufEditarCB.getSelectedItem().toString());
							}else {
								System.out.println("estrangeiro entrou");
								pessoa.setPais(paisesEditarCB.getSelectedItem().toString());
								pessoa.setUf("");

							};			
							pessoa.setEndereco(editarEnderecoTF.getText());
							pessoa.setCidade(editarCidadeTF.getText());

							System.out.println(pessoa);

							try {
								PessoaDao.salvar(pessoa);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							SistemaView.carregarPessoas(true);
							JOptionPane.showMessageDialog(null, "Contato alterado com sucesso:\n"+pessoa);

							setVisible(false);

						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void verificarCheckBox() {

		paisesEditarCB.setEnabled(checkBoxEstrangeiroEditar.isSelected());
		paisesEditarCB.setVisible(checkBoxEstrangeiroEditar.isSelected());
		ufEditarCB.setVisible(!checkBoxEstrangeiroEditar.isSelected());

	}
}

