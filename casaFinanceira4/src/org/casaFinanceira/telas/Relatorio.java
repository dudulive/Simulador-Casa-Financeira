package org.casaFinanceira.telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.casaFinanceira.dao.JDBCRelatorioDAO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Relatorio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbPesquisa;
	private JTextField jtData;
	private JTextField jtCPF;

	private String converteData(java.util.Date dtData){   
			  String date = new SimpleDateFormat("dd/MM/yyyy").format(dtData); 
		      return date; 
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
						e1.printStackTrace();
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
					Relatorio frame = new Relatorio();
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
	public Relatorio() {
		setTitle("Relatorio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1156, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 1130, 190);
		contentPane.add(scrollPane);
		
		tbPesquisa = new JTable();
		tbPesquisa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Data Cadastro", "Nome", "CPF", "Salario Liquido", "Valor Max. Parcela", "Situa\u00E7\u00E3o", "Tipo", "Valor Financiado", "Taxa de Juros", "N. Parcelas"
			}
		));
		tbPesquisa.setEnabled(false);
		scrollPane.setViewportView(tbPesquisa);
		
		JButton btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel tabela = (DefaultTableModel) tbPesquisa.getModel(); 
				
				if(tabela.getRowCount() > 0){
					tabela.setNumRows(0);;
				}
				if(jtCPF.getText().equals("")  && jtData.getText().equals("  /  /    ")){
					JDBCRelatorioDAO pesquisaDAO = new JDBCRelatorioDAO();
					java.util.List<org.casaFinanceira.entidades.Relatorio> listPesquisa = pesquisaDAO.listarTodos();
					for (org.casaFinanceira.entidades.Relatorio relatorio : listPesquisa) {
						
						tabela.addRow(new Object[]{ relatorio.getId(),converteData(relatorio.getDataCadastro()),relatorio.getNome(),
								relatorio.getCpf(),relatorio.getSalarioLiquido(), relatorio.getValorMaximoParcela(),
								relatorio.getSituacao(), relatorio.getTipo(), relatorio.getValorFinanciado(),
								relatorio.getTaxaJuros(), relatorio.getPeriodos()});
					}
				}else if(jtCPF.getText().equals("")  && jtData.getText() != "  /  /    "){
					JDBCRelatorioDAO pesquisaDAO = new JDBCRelatorioDAO();
					java.util.List<org.casaFinanceira.entidades.Relatorio> listPesquisa = pesquisaDAO.listarTodosData(formataData(jtData.getText()));
					for (org.casaFinanceira.entidades.Relatorio relatorio : listPesquisa) {
						
						tabela.addRow(new Object[]{ relatorio.getId(),converteData(relatorio.getDataCadastro()),relatorio.getNome(),
								relatorio.getCpf(),relatorio.getSalarioLiquido(), relatorio.getValorMaximoParcela(),
								relatorio.getSituacao(), relatorio.getTipo(), relatorio.getValorFinanciado(),
								relatorio.getTaxaJuros(), relatorio.getPeriodos()});
					}
				}else if(jtCPF.getText() != null && jtData.getText().equals("  /  /    ")){
					JDBCRelatorioDAO pesquisaDAO = new JDBCRelatorioDAO();
					java.util.List<org.casaFinanceira.entidades.Relatorio> listPesquisa = pesquisaDAO.listarTodosCPF(jtCPF.getText());
					for (org.casaFinanceira.entidades.Relatorio relatorio : listPesquisa) {
						
						tabela.addRow(new Object[]{ relatorio.getId(),converteData(relatorio.getDataCadastro()),relatorio.getNome(),
								relatorio.getCpf(),relatorio.getSalarioLiquido(), relatorio.getValorMaximoParcela(),
								relatorio.getSituacao(), relatorio.getTipo(), relatorio.getValorFinanciado(),
								relatorio.getTaxaJuros(), relatorio.getPeriodos()});
					}
				}else if(jtCPF.getText() != null && jtData.getText() != "  /  /    "){
					JDBCRelatorioDAO pesquisaDAO = new JDBCRelatorioDAO();
					java.util.List<org.casaFinanceira.entidades.Relatorio> listPesquisa = pesquisaDAO.listarTodosCPFData(formataData(jtData.getText()), jtCPF.getText());
					for (org.casaFinanceira.entidades.Relatorio relatorio : listPesquisa) {
						
						tabela.addRow(new Object[]{ relatorio.getId(),converteData(relatorio.getDataCadastro()),relatorio.getNome(),
								relatorio.getCpf(),relatorio.getSalarioLiquido(), relatorio.getValorMaximoParcela(),
								relatorio.getSituacao(), relatorio.getTipo(), relatorio.getValorFinanciado(),
								relatorio.getTaxaJuros(), relatorio.getPeriodos()});
					}
				} 
				if(tabela.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Registro não encontrado!");
				}
				
			}
		});
		btnListarTodos.setBounds(403, 26, 106, 23);
		contentPane.add(btnListarTodos);
		
		JLabel lblDataCadastro = new JLabel("Data Cadastro:");
		lblDataCadastro.setBounds(20, 30, 86, 14);
		contentPane.add(lblDataCadastro);
		
		jtData = new  JFormattedTextField(setMascara("##/##/####"));
		jtData.setBounds(113, 27, 86, 20);
		contentPane.add(jtData);
		jtData.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(209, 30, 46, 14);
		contentPane.add(lblCpf);
		
		jtCPF = new JTextField();
		jtCPF.addKeyListener(new KeyAdapter() {
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
		jtCPF.setBounds(249, 27, 131, 20);
		contentPane.add(jtCPF);
		jtCPF.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal principal = new Principal();    
	            principal.setVisible(true);    
	            dispose();
			}
		});
		btnVoltar.setBounds(693, 26, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtCPF.setText("");
				jtData.setText("");
			}
		});
		btnLimparCampos.setBounds(526, 26, 144, 23);
		contentPane.add(btnLimparCampos);
	}
}
