package albino.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import albino.dao.Conexao;
import albino.dao.PessoaDao;
import albino.entity.PessoaEntity;
import albino.utils.TextosUteis;

public class SistemaView {

	private static 		List<PessoaEntity> 	pessoas = new ArrayList<PessoaEntity>();
	private 			JFrame 				frmMinhaAgendaV;
	private	 			JDateChooser 		aniversarioDC;
	private final 		JTabbedPane 		tabbedPane;
	private				JTextField			nomePessoaTF;
	private				JTextField 			celularTF;
	private				JTextField 			telefoneTF;
	private				JTextField			emailTF;
	private				JTextField 			enderecoTF;
	private 			JTextField 			cidadeTF;
	private static		JTextField 			buscarPessoaTF;
	private 			JButton 			btnExcluir;
	private static 		JTable 				tablePessoas;
	private static 		JScrollPane 		scrollPessoas; 
	private static 		JScrollPane 		scrollCompet;
	private static		JComboBox<String>	UfCB;
	private static		JComboBox<String>	paisesCB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexao.conectar();
					SistemaView window = new SistemaView();
					window.frmMinhaAgendaV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();


				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SistemaView() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		initialize();
		carregarPessoas(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMinhaAgendaV = new JFrame();
		frmMinhaAgendaV.setTitle("Minha Agenda");
		frmMinhaAgendaV.setBounds(100, 100, 684, 476);
		frmMinhaAgendaV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinhaAgendaV.getContentPane().setLayout(null);
		tabbedPane.setBounds(0, 0, 658, 426);
		frmMinhaAgendaV.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Contatos", null, panel, null);
		panel.setLayout(null);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(12, 19, 61, 16);
		panel.add(lblPesquisar);

		buscarPessoaTF = new JTextField();
		buscarPessoaTF.setBounds(78, 17, 478, 20);
		panel.add(buscarPessoaTF);
		buscarPessoaTF.setColumns(10);

		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO EDITAR PESSOAS 

				if (tablePessoas.getSelectedRow()>=0) {
					PessoaEntity p = pessoas.get(tablePessoas.getSelectedRow());
					ContatoEditarView dialog = new ContatoEditarView(p);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);


					carregarPessoas(true);

					dialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Para editar, favor selecionar contato.");
				}

			}

		});
		btnNewButton.setBounds(479, 361, 77, 26);
		panel.add(btnNewButton);

		btnExcluir = new JButton("Excluir ");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO DELETAR PESSOA DA LISTA
				DefaultTableModel dtm = (DefaultTableModel)tablePessoas.getModel();

				int[] selectedRows = tablePessoas.getSelectedRows();
				
				if (selectedRows.length == 1){
					System.out.println("uma linha selecionada entrou");
					dtm.getValueAt(tablePessoas.getSelectedRow(),0);

					int x = tablePessoas.getSelectedRow();
					System.out.println(pessoas.get(x).getId());


					int status = JOptionPane.showConfirmDialog(null,"Deseja EXCLUIR o contato: \n"+pessoas.get(x).getNome()+"?","Atenção",JOptionPane.YES_NO_OPTION);
					if (status == JOptionPane.YES_OPTION) {

						dtm.removeRow(tablePessoas.getSelectedRow());
						tablePessoas.setModel(dtm);

						try {

							PessoaDao.remover(pessoas.get(x).getId());

							JOptionPane.showMessageDialog(null, "Pessoa removida com sucesso!");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} 
				}
					else if (selectedRows.length >= 2){
					System.out.println("mais de uma linha selecionada entrou");
					JOptionPane.showMessageDialog(null, "Favor selecionar apenas um contato para excluí-lo.");
				} 
				else{
					System.out.println("nenhuma linha selecionada entrou");
					JOptionPane.showMessageDialog(null, "Favor selecionar um contato.");
				} 

					carregarPessoas(true);
				}
			});
		btnExcluir.setBounds(566, 361, 77, 26);
		panel.add(btnExcluir);

		JButton btnNewButton_1 = new JButton("Ver Contato");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO VER CONTATO

				mostrarContato();

			}
		});
		btnNewButton_1.setBounds(353, 361, 116, 26);
		panel.add(btnNewButton_1);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//TODO BUSCAR PESSOAS

				carregarPessoas(false);

			}
		});
		btnBuscar.setBounds(566, 14, 77, 26);
		panel.add(btnBuscar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO BOTAO LIMPAR
				DefaultTableModel dtm = (DefaultTableModel)tablePessoas.getModel();

				if ((tablePessoas.getSelectedRow() >= 0)) { //LIMPA A SELEÇÃO

					dtm.removeRow(tablePessoas.getSelectedRow());

				} else { //LIMPA TODA A LISTA
					dtm.setRowCount( 0 );
					tablePessoas.setModel(dtm);
				}
			}
		});
		btnLimpar.setBounds(129, 361, 75, 26);
		panel.add(btnLimpar);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 46, 631, 304);
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		tablePessoas = new JTable();
		//		tablePessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePessoas.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel_3.add(tablePessoas, BorderLayout.CENTER);

		tablePessoas.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				tablePessoas.editingCanceled(null);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				System.out.println("Clicou!");
				if(evt.getClickCount() > 1){ 
					mostrarContato();

				}
			}

		});

		scrollPessoas = new JScrollPane(tablePessoas);
		panel_3.add(scrollPessoas, BorderLayout.CENTER);


		JButton btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO BOTAO LISTAR TODOS
				carregarPessoas(true);

			}
		});
		btnListarTodos.setBounds(12, 361, 107, 26);
		panel.add(btnListarTodos);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Cadastrar", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 25, 55, 16);
		panel_1.add(lblNome);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(12, 66, 55, 16);
		panel_1.add(lblCelular);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 107, 55, 16);
		panel_1.add(lblTelefone);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(12, 148, 55, 16);
		panel_1.add(lblEmail);

		JLabel lblAniversrio = new JLabel("Anivers\u00E1rio:");
		lblAniversrio.setBounds(12, 189, 68, 16);
		panel_1.add(lblAniversrio);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(12, 230, 68, 16);
		panel_1.add(lblEndereo);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 353, 55, 16);
		panel_1.add(lblCidade);

		JLabel lblUf = new JLabel("Pa\u00EDs:");
		lblUf.setBounds(12, 271, 39, 16);
		panel_1.add(lblUf);

		paisesCB = new JComboBox<String>();
		paisesCB.setModel(new DefaultComboBoxModel<String>(TextosUteis.paises()));
		paisesCB.setBackground(Color.WHITE);
		paisesCB.setBounds(350, 267, 282, 25);
		paisesCB.setVisible(false);
		panel_1.add(paisesCB);

		nomePessoaTF = new JTextField();
		nomePessoaTF.setBounds(85, 18, 558, 20);
		panel_1.add(nomePessoaTF);
		nomePessoaTF.setColumns(10);

		celularTF = new JTextField();
		celularTF.setColumns(10);
		celularTF.setBounds(85, 64, 112, 20);
		panel_1.add(celularTF);

		telefoneTF = new JTextField();
		telefoneTF.setColumns(10);
		telefoneTF.setBounds(85, 105, 112, 20);
		panel_1.add(telefoneTF);

		emailTF = new JTextField();
		emailTF.setColumns(10);
		emailTF.setBounds(85, 146, 558, 20);
		panel_1.add(emailTF);

		enderecoTF = new JTextField();
		enderecoTF.setColumns(10);
		enderecoTF.setBounds(85, 228, 558, 20);
		panel_1.add(enderecoTF);

		JCheckBox estrangeiroCheckBox = new JCheckBox("Estrangeiro(a)");
		estrangeiroCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paisesCB.setEnabled(estrangeiroCheckBox.isSelected());
				paisesCB.setVisible(estrangeiroCheckBox.isSelected());
				UfCB.setEnabled(!estrangeiroCheckBox.isSelected());

			}
		});
		estrangeiroCheckBox.setBounds(85, 267, 112, 24);
		panel_1.add(estrangeiroCheckBox);

		JLabel lblUf_1 = new JLabel("UF:");
		lblUf_1.setBounds(12, 312, 49, 16);
		panel_1.add(lblUf_1);

		UfCB = new JComboBox<String>();
		UfCB.setModel(new DefaultComboBoxModel<String>(TextosUteis.estados()));
		UfCB.setBackground(Color.WHITE);
		UfCB.setBounds(85, 308, 55, 25);
		panel_1.add(UfCB);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (nomePessoaTF.getText().equals("") || celularTF.getText().equals("") || aniversarioDC.getDate()==null) {

					JOptionPane.showMessageDialog(null, "O contato deve conter: NOME, CELULAR e ANIVERSÁRIO.");

				}else {


					//TODO SALVA PESSOAS NO SISTEMA
					PessoaEntity p = new PessoaEntity();


					p.setNome(nomePessoaTF.getText());
					p.setCelular(celularTF.getText());
					p.setTelefone(telefoneTF.getText());
					p.setEmail(emailTF.getText());
					p.setAniversario(aniversarioDC.getDate());
					if (estrangeiroCheckBox.isSelected()) {
						p.setPais(paisesCB.getSelectedItem().toString());
						p.setUf("");
					}else {
						p.setPais("Brasil");
						p.setUf(UfCB.getSelectedItem().toString());
					}					
					p.setEndereco(enderecoTF.getText());
					p.setCidade(cidadeTF.getText());

					try {
						PessoaDao.salvar(p);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					pessoas.add(p);
					JOptionPane.showMessageDialog(null, "Pessoa Adicionada com Sucesso:\n"+p);

					nomePessoaTF.setText("");
					celularTF.setText("");
					telefoneTF.setText("");
					emailTF.setText("");
					enderecoTF.setText("");
					cidadeTF.setText("");
					UfCB.setSelectedIndex(23);
					aniversarioDC.setDate(null);

					carregarPessoas(true);
				}
			}

		});


		btnIncluir.setBounds(545, 361, 98, 26);
		panel_1.add(btnIncluir);

		cidadeTF = new JTextField();
		cidadeTF.setColumns(10);
		cidadeTF.setBounds(85, 351, 436, 20);
		panel_1.add(cidadeTF);

		aniversarioDC = new JDateChooser();
		aniversarioDC.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		aniversarioDC.setBounds(85, 185, 112, 20);
		panel_1.add(aniversarioDC);

		JLabel lblBrasil = new JLabel("Brasil");
		lblBrasil.setBounds(361, 272, 46, 14);
		panel_1.add(lblBrasil);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Sobre", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("SENAI/TUBAR\u00C3O-SC");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 306, 45);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Curso:");
		lblNewLabel_1.setBounds(10, 67, 46, 14);
		panel_2.add(lblNewLabel_1);

		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(10, 92, 46, 14);
		panel_2.add(lblAluno);

		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(10, 117, 46, 14);
		panel_2.add(lblAno);

		JLabel lblProgramaoOrientadaA = new JLabel("Programa\u00E7\u00E3o Orientada a Objetos em Java");
		lblProgramaoOrientadaA.setBounds(76, 67, 577, 14);
		panel_2.add(lblProgramaoOrientadaA);

		JLabel lblGuilhermeAlbinoDe = new JLabel("Guilherme Albino de Andrade");
		lblGuilhermeAlbinoDe.setBounds(76, 92, 240, 14);
		panel_2.add(lblGuilhermeAlbinoDe);

		JLabel label = new JLabel("2019");
		label.setBounds(76, 117, 240, 14);
		panel_2.add(label);

		JLabel lblProfessor = new JLabel("Professor:");
		lblProfessor.setBounds(10, 142, 63, 14);
		panel_2.add(lblProfessor);

		JLabel lblRafaelMeyer = new JLabel("Rafael Meyer");
		lblRafaelMeyer.setBounds(76, 142, 240, 14);
		panel_2.add(lblRafaelMeyer);

		JLabel lblCompetnciasAprendidasNo = new JLabel("Compet\u00EAncias aprendidas no Curso:");
		lblCompetnciasAprendidasNo.setBounds(10, 167, 296, 14);
		panel_2.add(lblCompetnciasAprendidasNo);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 188, 633, 199);
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JTextArea competenciasTA = new JTextArea();
		competenciasTA.setBackground(Color.WHITE);
		competenciasTA.setFont(new Font("Arial", Font.PLAIN, 13));
		competenciasTA.setBounds(10, 188, 633, 199);
		competenciasTA.setEditable(false);
		competenciasTA.setText(TextosUteis.textoMetodos());

		//Define que o scroll inicie em 0 (início do textarea)
		competenciasTA.setSelectionStart(0);
		competenciasTA.setSelectionEnd(0);
		panel_4.add(competenciasTA, BorderLayout.CENTER);

		scrollCompet = new JScrollPane(competenciasTA);
		panel_4.add(scrollCompet, BorderLayout.CENTER);

		JButton emailAlunoBtn = new JButton("<html><font color='#0000ff'><u>guilhermealbino33@gmail.com</u></font></html>");
		emailAlunoBtn.setHorizontalAlignment(SwingConstants.LEFT);
		emailAlunoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("mailto:guilhermealbino33@gmail.com"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		emailAlunoBtn.setBackground(new Color(240,240,240));
		emailAlunoBtn.setBorderPainted(false);
		emailAlunoBtn.setBounds(393, 88, 250, 23);
		panel_2.add(emailAlunoBtn);

		JButton emailProfBtn = new JButton("<html><font color='#0000ff'><u>rafael.meyer@edu.sc.senai.br</u></font></html>");
		emailProfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent i) {

				try {
					Desktop.getDesktop().browse(new URI("mailto:rafael.meyer@edu.sc.senai.br"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		emailProfBtn.setHorizontalAlignment(SwingConstants.LEFT);
		emailProfBtn.setBorderPainted(false);
		emailProfBtn.setBackground(SystemColor.menu);
		emailProfBtn.setBounds(393, 138, 250, 23);
		panel_2.add(emailProfBtn);

	}

	public static void carregarListaPessoas() {
		pessoas = new ArrayList<PessoaEntity>();
		try {
			pessoas = PessoaDao.buscar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	public static void mostrarContato() {

		if ((tablePessoas.getSelectedRow() >= 0)) {

			PessoaEntity p = pessoas.get(tablePessoas.getSelectedRow());
			ContatoView dialog = new ContatoView(p);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);


		}else {
			JOptionPane.showMessageDialog(null, "Selecione um contato!");
		}
	}


	/*
	 * METODO PARA TABELA
	 */


	public static void carregarPessoas(Boolean todos) {

		if(todos) {
			carregarListaPessoas();
		}
		else {

			DefaultTableModel dtm = (DefaultTableModel)tablePessoas.getModel();
			dtm.setNumRows(0);

			String nome = buscarPessoaTF.getText();

			try {
				pessoas = PessoaDao.buscar(nome);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(pessoas);

		}
		if(pessoas.isEmpty()) {
			String[] colunas = new String[4];
			colunas[0]="ID";
			colunas[1]="NOME";
			colunas[2]="CELULAR";
			colunas[3]="EMAIL";


			tablePessoas.setModel(new DefaultTableModel(
					new String[][] {
					},
					colunas
					));


			tablePessoas.getColumnModel().getColumn(0).setPreferredWidth(25);
			tablePessoas.getColumnModel().getColumn(1).setPreferredWidth(270);
			tablePessoas.getColumnModel().getColumn(2).setPreferredWidth(120);
			tablePessoas.getColumnModel().getColumn(3).setPreferredWidth(200);
		}else {
			String[][] dados = new String[pessoas.size()][4];
			String[] colunas = new String[4];
			colunas[0]="ID";
			colunas[1]="NOME";
			colunas[2]="CELULAR";
			colunas[3]="EMAIL";

			for (int i = 0; i < pessoas.size(); i++) {
				dados[i][0]=pessoas.get(i).getId()+"";
				dados[i][1]=pessoas.get(i).getNome();
				dados[i][2]=pessoas.get(i).getCelular();
				dados[i][3]=pessoas.get(i).getEmail();
			}
			tablePessoas.setModel(new DefaultTableModel(
					dados,
					colunas
					)); //1029

			tablePessoas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			tablePessoas.setRowHeight(28);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();

			r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			tablePessoas.getColumnModel().getColumn(0).setCellRenderer(r);
			tablePessoas.getColumnModel().getColumn(0).setPreferredWidth(25);

			r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.LEFT);
			tablePessoas.getColumnModel().getColumn(1).setCellRenderer(r);
			tablePessoas.getColumnModel().getColumn(1).setPreferredWidth(270);

			r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.LEFT);
			tablePessoas.getColumnModel().getColumn(2).setCellRenderer(r);
			tablePessoas.getColumnModel().getColumn(2).setPreferredWidth(120);

			r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			tablePessoas.getColumnModel().getColumn(3).setCellRenderer(r);
			tablePessoas.getColumnModel().getColumn(3).setPreferredWidth(200);

		}
	}
}