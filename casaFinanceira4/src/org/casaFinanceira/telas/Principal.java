package org.casaFinanceira.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.JButton;

import org.casaFinanceira.calculos.CalcularTabela;
import org.casaFinanceira.dao.FinanciamentoDAO;
import org.casaFinanceira.dao.JDBCFinanciamentoDAO;
import org.casaFinanceira.dao.JDBCPessoaDAO;
import org.casaFinanceira.dao.PessoaDAO;
import org.casaFinanceira.entidades.Financiamento;
import org.casaFinanceira.entidades.Pessoa;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pessoa objPessoa;
	Financiamento objFinanciamento;
	PessoaDAO objPessoaDAO;
	String acao = "";

	private JPanel contentPane;
	private JTextField jtTaxaJuros;
	private JTextField jtNumeroParcelas;
	private JTextField jtValorFinanciamento;
	private JTextField jtValorMaximoParcela;
	private JTextField jtSalarioLiquido;
	private JTextField jtCPF;
	private JTextField jtNome;
	private JTextField jtIdPessoa;
	private JTable jTabelaCalculo;
	private JFormattedTextField jtData;
	JLabel lbNomeTabela = new JLabel("");
	@SuppressWarnings("rawtypes")
	private JComboBox cbTipo = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox cbSituacao = new JComboBox();
	final JButton btSalvar = new JButton("Salvar");
	final JButton btNovo = new JButton("Novo");
	final JButton btCancelar = new JButton("Cancelar");
	final JButton btnEditar = new JButton("Editar");
	final JButton btnPesquisar = new JButton("Pesquisar");
	final JButton btnExcluir = new JButton("Excluir");
	private JTextField jtValorParcela;

	private boolean validarCampos() {
		if (jtNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o Nome da Pessoa!");
			jtNome.requestFocus();
			return false;
		}
		if (jtCPF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o CPF da Pessoa!");
			jtCPF.requestFocus();
			return false;
		}
		if (jtSalarioLiquido.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Digite o Salario Liquido da Pessoa!");
			jtSalarioLiquido.requestFocus();
			return false;
		}
		if (jtValorFinanciamento.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Digite o Valor do Financiamento!");
			jtValorFinanciamento.requestFocus();
			return false;
		}
		if (jtNumeroParcelas.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o Numero de Parcelas!");
			jtNumeroParcelas.requestFocus();
			return false;
		}
		if (jtTaxaJuros.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite a Taxa de Juros!");
			jtTaxaJuros.requestFocus();
			return false;
		}
		if (jtData.getText().equals("  /  /    ")) {
			JOptionPane.showMessageDialog(null, "Digite a Data!");
			jtData.requestFocus();
			return false;
		}
		if(cbTipo.getItemCount() == 0){
			JOptionPane.showMessageDialog(null, "Escolha o Tipo da Simulação!");
			cbTipo.requestFocus();
			return false;
		}
		if(cbSituacao.getItemCount() == 0){
			JOptionPane.showMessageDialog(null, "Escolha o Situação do Cliente!");
			cbSituacao.requestFocus();
			return false;
		}
		if(jtValorParcela.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Digite o valor da Parcela!");
			jtValorParcela.requestFocus();
			return false;
		}
		return true;
	}

	private boolean preencherObjetos() throws Exception {
		objPessoa = new Pessoa();
		objFinanciamento = new Financiamento();
		objPessoa.setNome(jtNome.getText());
		objPessoa.setCpf(jtCPF.getText());
		objPessoa.setSituacao(cbSituacao.getSelectedItem().toString());
		objFinanciamento.setTipo(cbTipo.getSelectedItem().toString());
		objFinanciamento.setDataCadastro(formataData(jtData.getText()));
		if(jtIdPessoa.getText().equals("")){   
		}else{
			objFinanciamento.setIdPessoa(Integer.parseInt(jtIdPessoa.getText()));
		    objPessoa.setId(Long.parseLong(jtIdPessoa.getText()));
		}
		
		try {
			jtSalarioLiquido.setText(jtSalarioLiquido.getText().replaceAll(",",
					"."));
			objPessoa.setSalarioLiquido(Double.parseDouble(jtSalarioLiquido
					.getText()));

			objPessoa.setValorMaximoParcela(Double.parseDouble(jtValorMaximoParcela.getText()));

			jtValorFinanciamento.setText(jtValorFinanciamento.getText()
					.replaceAll(",", "."));
			objFinanciamento.setValorFinanciado(Double.parseDouble(jtValorFinanciamento.getText()));

			jtTaxaJuros.setText(jtTaxaJuros.getText().replaceAll(",", "."));
			objFinanciamento.setTaxaJuros(Double.parseDouble(jtTaxaJuros
					.getText()));

			jtNumeroParcelas.setText(jtNumeroParcelas.getText().replaceAll(",",
					"."));
			objFinanciamento.setPeriodos(Integer.parseInt(jtNumeroParcelas
					.getText()));
		} catch (NumberFormatException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Campo de valores numericos recebeu um valor invalido\n Faça a correção e tente novamente!");
			return false;
		}
		return true;
	}

	private void limparCampos() {
		jtNome.setText("");
		jtCPF.setText("");
		jtSalarioLiquido.setText("");
		jtValorMaximoParcela.setText("");
		jtValorFinanciamento.setText("");
		jtTaxaJuros.setText("");
		jtNumeroParcelas.setText("");
		jtData.setText("");
		cbTipo.setSelectedIndex(0);
		cbSituacao.setSelectedIndex(0);
		jtValorParcela.setText("");
		jtData.requestFocus();
		DefaultTableModel tabela = (DefaultTableModel) jTabelaCalculo.getModel();
		tabela.setNumRows(0);
	}
	
	private void preencherTabela() {
	
		if (jtNumeroParcelas != null && jtSalarioLiquido != null && jtTaxaJuros != null && jtValorFinanciamento != null
				&& cbTipo.getSelectedIndex() != 0) {
			try {
				if (preencherObjetos()) {
					double valorFinanciado = objFinanciamento
							.getValorFinanciado();
					double juros = objFinanciamento.getTaxaJuros();
					int periodos = objFinanciamento.getPeriodos();
					double valorMaximoEmprestimo = objPessoa
							.getValorMaximoParcela();
				if(cbTipo.getSelectedItem().equals(" SAC")){
					try {
						lbNomeTabela.setText(" SAC");
						DefaultTableModel tabela = (DefaultTableModel) jTabelaCalculo
								.getModel();
						double[][] tabelaSAC = CalcularTabela
								.obterTabelaSAC(valorFinanciado, juros,
										periodos, valorMaximoEmprestimo);
						tabela.setNumRows(0);
						for (int i = 0; i < tabelaSAC.length; i++) {
							if (tabelaSAC[i + 1][0] <= objPessoa.getValorMaximoParcela() && tabelaSAC[i + 1][0] != 0) {
								tabela.addRow(new Double[] {
										(double) i + 1,
										tabelaSAC[i + 1][0],
										tabelaSAC[i + 1][1],
										tabelaSAC[i + 1][2],
										tabelaSAC[i + 1][3] });
								btSalvar.setEnabled(true);
							} else {
								tabela.setNumRows(0);
								break;
							}
						}
					} catch (IndexOutOfBoundsException e) {
					}
				}else if (cbTipo.getSelectedItem().equals(" Price")) {
					try {
						lbNomeTabela.setText(" Price");
						DefaultTableModel tabela = (DefaultTableModel) jTabelaCalculo
								.getModel();
						double[][] tabelaPrice = CalcularTabela
								.obterTabelaPrice(valorFinanciado,
										juros, periodos,
										valorMaximoEmprestimo);
						tabela.setNumRows(0);
						for (int i = 0; i < tabelaPrice.length; i++) {
							if (tabelaPrice[i + 1][0] <= objPessoa
									.getValorMaximoParcela()
									&& tabelaPrice[i + 1][0] != 0) {
								tabela.addRow(new Double[] {
										(double) i + 1,
										tabelaPrice[i + 1][0],
										tabelaPrice[i + 1][1],
										tabelaPrice[i + 1][2],
										tabelaPrice[i + 1][3] });
							} else {
								tabela.setNumRows(0);
								break;
							}
						}
					} catch (IndexOutOfBoundsException e) {

					}
				}else {
					lbNomeTabela.setText("");
					DefaultTableModel tabela = (DefaultTableModel) jTabelaCalculo
							.getModel();
					tabela.setNumRows(0);
				}	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	  }
	
	
	
	private String converteData(java.util.Date dtData){  
		   SimpleDateFormat formatBra;     
		   formatBra = new SimpleDateFormat("dd/MM/yyyy");  
		   try {  
		      java.util.Date newData = formatBra.parse(dtData.toString());  
		      return (formatBra.format(newData));  
		   } catch (ParseException Ex) {  
		      return "Erro na conversão da data";  
		   }  
		} 
	
	private MaskFormatter setMascara(String mascara){  
	    MaskFormatter mask = null;  
	    try{  
	        mask = new MaskFormatter(mascara);                        
	        }catch(java.text.ParseException ex){}  
	    return mask;  
	}
	
	 private static Date formataData(String data){   
	        if (data == null || data.equals(""))  
	            return null;  
	          
	        Date date = null;  
	        try {  
	            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	            date = (java.util.Date)formatter.parse(data);  
	        } catch (ParseException e) {              
					try {
						throw e;
					} catch (ParseException e1) {
						
					}
	        }  
	        return date;  
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Principal() {
		setResizable(false);
		setTitle("Casa Financeira");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setViewportView(panel);

		jtTaxaJuros = new JTextField();
		jtTaxaJuros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jtTaxaJuros.getText().length() <= 5 - 1) {
					// deixe passar
				} else {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		jtTaxaJuros.setEditable(false);
		jtTaxaJuros.setColumns(10);
		jtTaxaJuros.setBounds(166, 330, 64, 22);
		panel.add(jtTaxaJuros);

		JLabel lblTaxaDeJuros = new JLabel("Taxa de Juros Mensal:");
		lblTaxaDeJuros.setBounds(25, 333, 138, 16);
		panel.add(lblTaxaDeJuros);

		JLabel lblNumeroDeParcelas = new JLabel("Numero de Parcelas:");
		lblNumeroDeParcelas.setBounds(275, 306, 122, 16);
		panel.add(lblNumeroDeParcelas);

		jtNumeroParcelas = new JTextField();
		jtNumeroParcelas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jtNumeroParcelas.getText().length() <= 2 - 1) {
					// deixe passar
				} else {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		jtNumeroParcelas.setEditable(false);
		jtNumeroParcelas.setColumns(10);
		jtNumeroParcelas.setBounds(407, 303, 79, 22);
		panel.add(jtNumeroParcelas);

		JLabel label_2 = new JLabel("Valor do Financiamento:");
		label_2.setBounds(25, 306, 151, 16);
		panel.add(label_2);

		jtValorFinanciamento = new JTextField();
		jtValorFinanciamento.setEditable(false);
		jtValorFinanciamento.setColumns(10);
		jtValorFinanciamento.setBounds(166, 300, 99, 22);
		panel.add(jtValorFinanciamento);

		JLabel lblInfomaesDoEmprestimo = new JLabel("Infoma\u00E7\u00F5es do Emprestimo:");
		lblInfomaesDoEmprestimo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInfomaesDoEmprestimo.setBounds(10, 264, 261, 25);
		panel.add(lblInfomaesDoEmprestimo);

		JLabel label_4 = new JLabel("Valor Maximo da Parcela :");
		label_4.setBounds(25, 198, 151, 16);
		panel.add(label_4);

		jtValorMaximoParcela = new JTextField();
		jtValorMaximoParcela.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jtValorMaximoParcela.getText().length() <= 10 - 1) {
					// deixe passar
				} else {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		jtValorMaximoParcela.setEditable(false);
		jtValorMaximoParcela.setColumns(10);
		jtValorMaximoParcela.setBounds(187, 195, 78, 22);
		panel.add(jtValorMaximoParcela);

		jtSalarioLiquido = new JTextField();
		jtSalarioLiquido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jtSalarioLiquido.getText().length() <= 10 - 1) {
					// deixe passar
				} else {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		jtSalarioLiquido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (jtSalarioLiquido.getText().equals("")) {
				} else {
					try {
						Pessoa objPessoa = new Pessoa();
						jtSalarioLiquido.setText(jtSalarioLiquido.getText()
								.replaceAll(",", "."));
						objPessoa.setSalarioLiquido(Double
								.parseDouble(jtSalarioLiquido.getText()));
						jtValorMaximoParcela.setText(String.valueOf(objPessoa
								.getValorMaximoParcela()));
					} catch (NumberFormatException e1) {
						JOptionPane
								.showMessageDialog(
										null,
										"Foi digitado um valor invalido no campo de Salario liquido,\n Faça a correção!");
					}
				}
			}
		});

		jtSalarioLiquido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtSalarioLiquido.getText().equals("")) {
				} else {
					try {
						Pessoa objPessoa = new Pessoa();
						jtSalarioLiquido.setText(jtSalarioLiquido.getText()
								.replaceAll(",", "."));
						objPessoa.setSalarioLiquido(Double
								.parseDouble(jtSalarioLiquido.getText()));
						jtValorMaximoParcela.setText(String.valueOf(objPessoa
								.getValorMaximoParcela()));
					} catch (NumberFormatException e1) {
						JOptionPane
								.showMessageDialog(
										null,
										"Foi digitado um valor invalido no campo de Salario liquido,\n Faça a correção!");
					}
					
				}
			}
		});
		jtSalarioLiquido.setEditable(false);
		jtSalarioLiquido.setColumns(10);
		jtSalarioLiquido.setBounds(130, 166, 106, 22);
		panel.add(jtSalarioLiquido);

		JLabel label_5 = new JLabel("Salario Liquido:");
		label_5.setBounds(25, 169, 106, 16);
		panel.add(label_5);

		jtCPF = new JTextField();
		jtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jtCPF.getText().length() <= 14 - 1) {
					// deixe passar
				} else {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		jtCPF.setEditable(false);
		jtCPF.setColumns(10);
		jtCPF.setBounds(131, 137, 184, 22);
		panel.add(jtCPF);

		JLabel label_6 = new JLabel("Numero do CPF:");
		label_6.setBounds(25, 140, 106, 16);
		panel.add(label_6);

		JLabel label_7 = new JLabel("*Somente Numeros");
		label_7.setBounds(325, 139, 122, 16);
		panel.add(label_7);

		jtNome = new JTextField();
		jtNome.setEditable(false);
		jtNome.setColumns(10);
		jtNome.setBounds(131, 105, 290, 22);
		panel.add(jtNome);

		JLabel label_8 = new JLabel("Nome da Pessoa:");
		label_8.setBounds(25, 108, 106, 16);
		panel.add(label_8);

		JLabel lblInformaesPessoais = new JLabel("Informa\u00E7\u00F5es Pessoais:");
		lblInformaesPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInformaesPessoais.setBounds(10, 75, 220, 22);
		panel.add(lblInformaesPessoais);

		
		
		cbTipo.setEnabled(false);

		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				jtNome.setEditable(true);
				jtCPF.setEditable(true);
				jtSalarioLiquido.setEditable(true);
				jtTaxaJuros.setEditable(true);
				jtValorFinanciamento.setEditable(true);
				jtNumeroParcelas.setEditable(true);
				cbTipo.setEnabled(true);
				cbSituacao.setEnabled(true);
				jtData.setEnabled(true);
				btNovo.setEnabled(false);
				btCancelar.setEnabled(true);
				btSalvar.setEnabled(true);
				btnExcluir.setEnabled(false);
				jtIdPessoa.setEnabled(false);
				jtIdPessoa.setText("");
				lbNomeTabela.setText("");
				btnPesquisar.setEnabled(false);
				btnEditar.setEnabled(false);
				jtValorParcela.setEnabled(true);
				jtValorParcela.setEditable(true);
				acao = "incluir";
			}
		});
		btNovo.setBounds(574, 32, 99, 23);
		panel.add(btNovo);

		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (validarCampos()) {
						if (preencherObjetos()) {
							PessoaDAO objPessoaDAO = new JDBCPessoaDAO();
							FinanciamentoDAO objFinanciamentoDAO = new JDBCFinanciamentoDAO();
							if (acao.equalsIgnoreCase("incluir")) {
								objPessoaDAO.insert(objPessoa);
								objFinanciamentoDAO.insert(objFinanciamento);
								JOptionPane.showMessageDialog(null,
										"Resgistro Salvo com sucesso!");
								limparCampos();
								jtNome.setEditable(false);
								jtCPF.setEditable(false);
								jtSalarioLiquido.setEditable(false);
								jtTaxaJuros.setEditable(false);
								jtValorFinanciamento.setEditable(false);
								jtNumeroParcelas.setEditable(false);
								btNovo.setEnabled(true);
								btSalvar.setEnabled(false);
								btCancelar.setEnabled(false);
								btnExcluir.setEnabled(false);
								cbTipo.setEnabled(false);
								cbSituacao.setEnabled(false);
								DefaultTableModel tabela = (DefaultTableModel) jTabelaCalculo
										.getModel();
								tabela.setNumRows(0);
								btnPesquisar.setEnabled(true);
								jtIdPessoa.setText("");
								jtIdPessoa.setEnabled(true);
								jtData.setEnabled(true);
							}
							if (acao.equalsIgnoreCase("editar")) {
								objPessoaDAO.update(objPessoa);
								objFinanciamentoDAO.update(objFinanciamento);
								JOptionPane.showMessageDialog(null,
										"Resgistro Editado com sucesso!");
								limparCampos();
								jtNome.setEditable(false);
								jtCPF.setEditable(false);
								jtSalarioLiquido.setEditable(false);
								jtTaxaJuros.setEditable(false);
								jtValorFinanciamento.setEditable(false);
								jtNumeroParcelas.setEditable(false);
								btNovo.setEnabled(true);
								btSalvar.setEnabled(false);
								btCancelar.setEnabled(false);
								btnExcluir.setEnabled(false);
								cbTipo.setEnabled(false);
								cbSituacao.setEnabled(false);
								DefaultTableModel tabela = (DefaultTableModel) jTabelaCalculo
										.getModel();
								tabela.setNumRows(0);
								btnPesquisar.setEnabled(true);
								jtIdPessoa.setText("");
								jtIdPessoa.setEnabled(true);
								jtIdPessoa.setEditable(true);
								jtData.setEnabled(true);
							}
						}
					}
				} catch (Exception erro) {
					erro.printStackTrace();
				}
			}
		});
		btSalvar.setEnabled(false);
		btSalvar.setBounds(574, 60, 99, 23);
		panel.add(btSalvar);
		
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				jtNome.setEditable(false);
				jtCPF.setEditable(false);
				jtSalarioLiquido.setEditable(false);
				jtTaxaJuros.setEditable(false);
				jtValorFinanciamento.setEditable(false);
				jtNumeroParcelas.setEditable(false);
				jtIdPessoa.setEditable(true);
				btNovo.setEnabled(true);
				btSalvar.setEnabled(false);
				btCancelar.setEnabled(false);
				btnPesquisar.setEnabled(true);
				jtIdPessoa.setEnabled(true);
				jtIdPessoa.setText("");
				jtData.setText("");
				jtData.setEnabled(true);
				btnExcluir.setEnabled(false);
				cbTipo.setEnabled(false);
				jtValorParcela.setEditable(false);
				cbTipo.setSelectedIndex(0);
			}
		});
		btCancelar.setEnabled(false);
		btCancelar.setBounds(574, 90, 99, 23);
		panel.add(btCancelar);

		JLabel lblPesquisar = new JLabel("Codigo da Pessoa:");
		lblPesquisar.setBounds(25, 36, 106, 14);
		panel.add(lblPesquisar);

		jtIdPessoa = new JTextField();
		jtIdPessoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jtIdPessoa.getText().length() <= 6 - 1) {
					// deixe passar
				} else {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		jtIdPessoa.setBounds(144, 33, 86, 20);
		panel.add(jtIdPessoa);
		jtIdPessoa.setColumns(10);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtIdPessoa.setEditable(true);
				if (jtIdPessoa.getText().equals("") || jtData.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null,
							"Digite o codigo ou a Data!");
				} else {
					PessoaDAO dao = new JDBCPessoaDAO();
					FinanciamentoDAO finDao = new JDBCFinanciamentoDAO();
					
						objPessoa = dao.findByID(Long.parseLong(jtIdPessoa
								.getText())); 
							objFinanciamento = finDao.findByID(Long
									.parseLong(jtIdPessoa.getText()), formataData(jtData.getText()));
					
					if (objPessoa == null || objFinanciamento == null) {
						JOptionPane.showMessageDialog(null,
								"Registro Não Existe!");
					} else {
						jtNome.setText(objPessoa.getNome());
						jtCPF.setText(objPessoa.getCpf());
						jtSalarioLiquido.setText(String.valueOf(objPessoa
								.getSalarioLiquido()));
						jtValorMaximoParcela.setText(String.valueOf(objPessoa
								.getValorMaximoParcela()));
						cbSituacao.setSelectedItem(objPessoa.getSituacao());	
						jtValorFinanciamento.setText(String
								.valueOf(objFinanciamento.getValorFinanciado()));
						jtTaxaJuros.setText(String.valueOf(objFinanciamento
								.getTaxaJuros()));
						jtNumeroParcelas.setText(String
								.valueOf(objFinanciamento.getPeriodos()));
						cbTipo.setSelectedItem(objFinanciamento.getTipo());
						UIManager.put("cbTipo.disabledForeground", new ColorUIResource(Color.BLACK));  
						jtData.setText(converteData(objFinanciamento.getDataCadastro()));
						preencherTabela();
						btnEditar.setEnabled(true);
						btnExcluir.setEnabled(true);
						btCancelar.setEnabled(true);
						jtIdPessoa.setEnabled(true);
						jtData.setEnabled(true);
					}
				}
			}
		});
		btnPesquisar.setBounds(371, 32, 106, 23);
		panel.add(btnPesquisar);

		JLabel lblPesquisa = new JLabel("Pesquisar:");
		lblPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPesquisa.setBounds(10, 0, 106, 25);
		panel.add(lblPesquisa);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtIdPessoa.setEditable(false);
				jtNome.setEditable(true);
				jtCPF.setEditable(true);
				jtSalarioLiquido.setEditable(true);
				jtValorFinanciamento.setEditable(true);
				jtNumeroParcelas.setEditable(true);
				jtTaxaJuros.setEditable(true);
				cbTipo.setEnabled(true);
				cbSituacao.setEnabled(true);
				btSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btNovo.setEnabled(false);
				btCancelar.setEnabled(true);
				btnPesquisar.setEnabled(false);
				jtValorParcela.setEditable(true);
				acao = "editar";
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(371, 60, 106, 23);
		panel.add(btnEditar);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtIdPessoa.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Digite o codigo da Pessoa!");
				} else {
					PessoaDAO pesDao = new JDBCPessoaDAO();
					FinanciamentoDAO finDao = new JDBCFinanciamentoDAO();
					if (finDao.delete(Long.parseLong(jtIdPessoa.getText()))) {
						if (pesDao.delete(Long.parseLong(jtIdPessoa.getText()))) {
							JOptionPane.showMessageDialog(null,
									"Registro Excluido com sucesso!");
							limparCampos();
							jtNome.setEditable(false);
							jtCPF.setEditable(false);
							jtSalarioLiquido.setEditable(false);
							jtTaxaJuros.setEditable(false);
							jtValorFinanciamento.setEditable(false);
							jtNumeroParcelas.setEditable(false);
							btNovo.setEnabled(true);
							btSalvar.setEnabled(false);
							btCancelar.setEnabled(false);
							DefaultTableModel tabela = (DefaultTableModel) jTabelaCalculo
									.getModel();
							tabela.setNumRows(0);
							btnPesquisar.setEnabled(true);
							jtIdPessoa.setText("");
							jtIdPessoa.setEnabled(true);
							jtData.setText("");
							jtData.setEnabled(true);
							jtIdPessoa.setEditable(true);
							cbTipo.setEnabled(false);
							cbSituacao.setEnabled(false);
							btnExcluir.setEnabled(false);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possivel fazer a Exclusão!");
					}
				}
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(481, 60, 89, 23);
		panel.add(btnExcluir);

		JLabel lblTabela = new JLabel("Tabela:");
		lblTabela.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTabela.setBounds(25, 360, 89, 20);
		panel.add(lblTabela);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(10, 391, 663, 208);
		panel.add(scrollPane_1);

		jTabelaCalculo = new JTable();
		jTabelaCalculo.setEnabled(false);
		jTabelaCalculo.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "N. da Parcela", " Valor da  Parcela",
						"Juros da Parcela", "Amortiza\u00E7\u00E3o",
						"Saldo Devedor" }));
		scrollPane_1.setViewportView(jTabelaCalculo);
		JLabel label_10 = new JLabel("%");
		label_10.setBounds(236, 334, 17, 14);
		panel.add(label_10);

		JLabel lblMeses = new JLabel("Mensais");
		lblMeses.setBounds(496, 307, 64, 14);
		panel.add(lblMeses);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setBounds(25, 228, 56, 14);
		panel.add(lblSituao);
		cbSituacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (jtSalarioLiquido.getText() != null && jtValorParcela != null) {
						jtSalarioLiquido.setText(jtSalarioLiquido.getText()
								.replaceAll(",", "."));
					try{
						 double SalarioLiquido = Double.parseDouble(jtSalarioLiquido.getText());
						 double valorParcela = Double.parseDouble(jtValorParcela.getText()); 
						 double valorMaximoParcela = (SalarioLiquido * 0.3) - valorParcela;
						jtValorMaximoParcela.setText(String.valueOf(valorMaximoParcela));
					}catch(Exception e){
						
					}
				}else{
					JOptionPane
					.showMessageDialog(
							null,
							"Foi digitado um valor invalido no campo de Salario liquido,\n Faça a correção!");
				}
			}
		});
		
		cbSituacao.setEnabled(false);
		cbSituacao.setModel(new DefaultComboBoxModel(new String[] {"", " Aposentado", " Pensionista  ", " Funcion\u00E1rios P\u00FAblico"}));
		cbSituacao.setBounds(100, 225, 165, 20);
		panel.add(cbSituacao);
		
		JLabel lblDataDoEmprestimo = new JLabel("Data :");
		lblDataDoEmprestimo.setBounds(240, 36, 42, 14);
		panel.add(lblDataDoEmprestimo);
		
		jtData = new JFormattedTextField(setMascara("##/##/####"));
		jtData.setBounds(275, 33, 86, 20);
		panel.add(jtData);
		jtData.setColumns(10);
		
		
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencherTabela();
			}
		});
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {" ", " Price", " SAC"}));
		cbTipo.setBounds(407, 331, 79, 20);
		panel.add(cbTipo);
		
		JLabel lblTipo = new JLabel("Tipo Simula\u00E7\u00E3o:");
		lblTipo.setBounds(275, 334, 122, 14);
		panel.add(lblTipo);
		lbNomeTabela.setForeground(Color.BLACK);
		lbNomeTabela.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbNomeTabela.setEnabled(false);
		
		
		lbNomeTabela.setBounds(117, 360, 59, 20);
		panel.add(lbNomeTabela);
		
		JButton btnNewButton = new JButton("Relatorio");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Relatorio relatorio = new Relatorio();    
	            relatorio.setVisible(true);    
	            dispose();
			}
		});
		btnNewButton.setBounds(481, 32, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lbadever = new JLabel("Valor da Parcela que estava devendo:");
		lbadever.setBounds(253, 170, 220, 18);
		panel.add(lbadever);
		
		jtValorParcela = new JTextField();
		jtValorParcela.setEditable(false);
		jtValorParcela.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jtNumeroParcelas.getText().length() <= 5 - 1) {
					// deixe passar
				} else {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}	
			}
		});
		jtValorParcela.setBounds(472, 167, 138, 20);
		panel.add(jtValorParcela);
		jtValorParcela.setColumns(10);
	}
}
