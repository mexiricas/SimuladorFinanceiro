package br.com.cadastro.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.cadastro.controle.EmprestControl;
import br.com.cadastro.modelo.Cli_Emprest;
import br.com.cadastro.modelo.Cli_EmprestDAO;
import br.com.cadastro.modelo.Cliente;
import br.com.cadastro.modelo.ClienteDAO;
import br.com.cadastro.modelo.EmprestDAO;
import br.com.cadastro.modelo.Emprestimo;
import br.com.cadastro.util.ConnectionFactory;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ManterEmprest extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField boxNomecli;
	private JTextField boxCpfcli;
	private JTextField boxCatcli;
	private JTextField boxSalcli;
	private JTextField boxMargemcli;
	private JTextField boxValorEmprest;
	private JTextField boxId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManterEmprest frame = new ManterEmprest();
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
	public ManterEmprest() {
		Emprestimo emprest = new Emprestimo();
		Cliente cli = new Cliente();
		ClienteDAO cliDao = new ClienteDAO();
		EmprestControl emprestControl = new EmprestControl();
		
		setTitle("Empréstimo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//btn VOLTAR
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(304, 365, 89, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnVoltar);
		
		//cliente
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(27, 57, 57, 14);
		contentPane.add(lblCliente);
		
		//caixa de nome
		boxNomecli = new JTextField();
		boxNomecli.setBounds(90, 54, 256, 20);
		contentPane.add(boxNomecli);
		boxNomecli.setColumns(10);
		
		//botão PESQUISAR por nome		
		JButton btnPesquisarNome = new JButton("Pesquisar");
		btnPesquisarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String lsCli = cliDao.selectByNome(boxNomecli.getText());
				String[] dados;
				
				dados = lsCli.split(",");				
				
				boxCpfcli.setText(dados[0]);
				boxNomecli.setText(dados[1]);
				boxCatcli.setText(dados[2]);
				boxSalcli.setText(dados[3]);
				boxMargemcli.setText(dados[4]);
			}
		});
		btnPesquisarNome.setBounds(356, 53, 89, 23);
		contentPane.add(btnPesquisarNome);
		
		//cpf
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(27, 87, 46, 14);
		contentPane.add(lblCpf);
		
		//caixa CPF
		boxCpfcli = new JTextField();
		boxCpfcli.setEditable(false);
		boxCpfcli.setBounds(90, 84, 137, 20);
		contentPane.add(boxCpfcli);
		boxCpfcli.setColumns(10);
		
		//categoria
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(27, 121, 67, 14);
		contentPane.add(lblCategoria);
		
		//caixa CATEGORIA		
		boxCatcli = new JTextField();
		boxCatcli.setEditable(false);
		boxCatcli.setBounds(90, 118, 137, 20);
		contentPane.add(boxCatcli);
		boxCatcli.setColumns(10);
		
		//salário
		JLabel lblSalrio = new JLabel("Salário:");
		lblSalrio.setBounds(237, 88, 46, 14);
		contentPane.add(lblSalrio);
		
		//caixa SALÁRIO
		boxSalcli = new JTextField();
		boxSalcli.setEditable(false);
		boxSalcli.setBounds(281, 85, 164, 20);
		contentPane.add(boxSalcli);
		boxSalcli.setColumns(10);
		
		//margem
		JLabel lblMargem = new JLabel("Margem:");
		lblMargem.setBounds(237, 120, 57, 14);
		contentPane.add(lblMargem);
		
		//caixa MARGEM
		boxMargemcli = new JTextField();
		boxMargemcli.setEditable(false);
		boxMargemcli.setBounds(304, 117, 141, 20);
		contentPane.add(boxMargemcli);
		boxMargemcli.setColumns(10);
		
		//label
		JLabel lblValorDoEmprstimo = new JLabel("Valor do Empréstimo:");
		lblValorDoEmprstimo.setBounds(27, 189, 111, 14);
		contentPane.add(lblValorDoEmprstimo);
		
		//caixa do VALOR do empréstimo
		boxValorEmprest = new JTextField();
		boxValorEmprest.setBounds(141, 186, 304, 20);
		contentPane.add(boxValorEmprest);
		boxValorEmprest.setColumns(10);		
		
		//label
		JLabel lblQuantidadeDeParcelas = new JLabel("Quantidade de parcelas:");
		lblQuantidadeDeParcelas.setBounds(27, 237, 145, 14);
		contentPane.add(lblQuantidadeDeParcelas);
		
		//combo box de quantidade de PARCELAS
		JComboBox<String> comboBoxQtdeParcelas = new JComboBox<String>();
		comboBoxQtdeParcelas.setModel(new DefaultComboBoxModel<String>(new String[] {"6", "12", "24", "36", "48", "60"}));
		comboBoxQtdeParcelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mes =  comboBoxQtdeParcelas.getSelectedItem().toString();
				
				if(mes.equals("6")){
					emprest.setQtde_parcela(6);
				}
				else if(mes.equals("12")){
					emprest.setQtde_parcela(12);
				}
				else if(mes.equals("24")){
					emprest.setQtde_parcela(24);
				}
				else if(mes.equals("36")){
					emprest.setQtde_parcela(36);
				}
				else if(mes.equals("48")){
					emprest.setQtde_parcela(48);
				}
				else if(mes.equals("60")){
					emprest.setQtde_parcela(60);
				}
			}
		});
		comboBoxQtdeParcelas.setBounds(182, 234, 164, 20);
		contentPane.add(comboBoxQtdeParcelas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 156, 652, 13);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 352, 650, 2);
		contentPane.add(separator_1);
		
		//label
		JLabel lblQualTipoDe = new JLabel("Qual tipo de parcelamento?");
		lblQualTipoDe.setBounds(489, 189, 188, 14);
		contentPane.add(lblQualTipoDe);
				
		//combo box de escolha entre Sac e Price 
		JComboBox<String> comboBoxSacOuPrice = new JComboBox<String>();
		comboBoxSacOuPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tipoEmprestimo =  comboBoxSacOuPrice.getSelectedItem().toString();
				
				if(tipoEmprestimo.equals("Sac")){
					emprest.setTpTab("Sac");
				}
				else{
					if(tipoEmprestimo.equals("Price")){
						emprest.setTpTab("Price");
					}
				}
			}
		});
		comboBoxSacOuPrice.setBounds(489, 234, 137, 20);
		comboBoxSacOuPrice.addItem("Sac");
		comboBoxSacOuPrice.addItem("Price");
		contentPane.add(comboBoxSacOuPrice);
			
		
		JButton btnSimular = new JButton("Simular");
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cli.setNome(boxNomecli.getText());
				cli.setCpf(boxCpfcli.getText());
				cli.setCategoria(boxCatcli.getText());
				cli.setSal_liq(Float.parseFloat(boxSalcli.getText()));
				cli.setSal30(Float.parseFloat(boxMargemcli.getText()));
				
				emprest.setVl_emprest(Double.parseDouble(boxValorEmprest.getText()));									
				emprest.setQtde_parcela(Integer.parseInt((String)(comboBoxQtdeParcelas.getSelectedItem())));					
				emprest.setTpTab((String) comboBoxSacOuPrice.getSelectedItem());				
				
				emprestControl.setSalDev(Double.parseDouble(boxValorEmprest.getText()));
				emprestControl.setNumParcela(Integer.parseInt((String) comboBoxQtdeParcelas.getSelectedItem()));
					
				if (comboBoxSacOuPrice.getSelectedItem() == "Sac"){
				
					Sac sac = new Sac(emprest);
					sac.setVisible(true);
					sac.setValTableSAC();
				}else{
					if(comboBoxSacOuPrice.getSelectedItem() == "Price"){
						
						Price price = new Price(emprest);
						price.setVisible(true);
						price.setValTablePrice();
					}
				}
			}
		});		
		btnSimular.setBounds(281, 285, 127, 31);
		contentPane.add(btnSimular);
				
		//btn SALVAR
		EmprestDAO emprestDao = new EmprestDAO();
		Cli_EmprestDAO cliEmpDao = new Cli_EmprestDAO();
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emprest.setId(Integer.parseInt(boxId.getText()));
				emprest.setVl_emprest(Double.parseDouble(boxValorEmprest.getText()));
				emprest.setQtde_parcela(Integer.parseInt((String) comboBoxQtdeParcelas.getSelectedItem()));
				if(comboBoxSacOuPrice.getSelectedItem() == "Sac"){
					emprest.setPmt(emprestControl.calcAmortSac(Integer.parseInt((String) comboBoxQtdeParcelas.getSelectedItem()), Double.parseDouble(boxValorEmprest.getText())));
				}else{
					if(comboBoxSacOuPrice.getSelectedItem() == "Price"){
						emprest.setPmt(emprestControl.calcPricePMT(Double.parseDouble(boxValorEmprest.getText()), emprestControl.getJuros(), emprestControl.getMes()));
					}
				}
				
				JOptionPane.showMessageDialog(null, emprest.getId());
				emprestDao.insertEmprest(emprest);				
				cliEmpDao.insertCliEmprest(cli, emprest);
				
				boxNomecli.setText("");
				boxCpfcli.setText("");
				boxSalcli.setText("");
				boxMargemcli.setText("0");
				boxValorEmprest.setText("");
			}
		});
		btnSalvar.setBounds(489, 285, 137, 31);
		contentPane.add(btnSalvar);
		
		boxId = new JTextField();
		boxId.setText("00");
		boxId.setBounds(57, 290, 86, 20);
		contentPane.add(boxId);
		boxId.setColumns(10);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(27, 293, 46, 14);
		contentPane.add(lblId);
		
		JButton btnHistricoDeEmprstimos = new JButton("Histórico de Empréstimos");
		btnHistricoDeEmprstimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cli_EmprestDAO cliEd = new Cli_EmprestDAO();
				Cli_Emprest cliEm = new Cli_Emprest();
				System.out.println(cliEd.selectAllCliEmprest(cliEm));
			}
		});
		btnHistricoDeEmprstimos.setBounds(462, 365, 198, 23);
		contentPane.add(btnHistricoDeEmprstimos);
		
	}	
}
