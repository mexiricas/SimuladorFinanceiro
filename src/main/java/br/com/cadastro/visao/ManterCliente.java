package br.com.cadastro.visao;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.cadastro.modelo.Cliente;
import br.com.cadastro.modelo.ClienteDAO;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ManterCliente extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField boxNome;
	private JTextField boxCpf;
	private JTextField boxSal_liq;
	private JTextField boxMargem;
	private JTextField boxValorEmprest;
	private JComboBox<String> boxCategoria;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManterCliente frame = new ManterCliente();
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
	public ManterCliente() {
		ClienteDAO cliDao = new ClienteDAO();
		Cliente cliente = new Cliente();
		
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//botão VOLTAR
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setBounds(275, 376, 89, 23);
		contentPane.add(btnVoltar);
		
		//CPF
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 77, 46, 14);
		contentPane.add(lblCpf);
		
		//caixa de texto CPF
		boxCpf = new JTextField();		
		boxCpf.setBounds(48, 74, 197, 20);
		contentPane.add(boxCpf);
		boxCpf.setColumns(10);	
		
		//PESQUISA pelo CPF		
		JButton btnPesqCpf = new JButton("Pesquisar");
		btnPesqCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lsCli = cliDao.selectByCpf(boxCpf.getText());
				String[] dados;				
				
				dados = lsCli.split(",");				
				
				boxCpf.setText(dados[0]);
				boxNome.setText(dados[1]);
				boxCategoria.setSelectedItem(dados[2]);	
				boxSal_liq.setText(dados[3]);
				boxMargem.setText(dados[4]);
			}
		});
		btnPesqCpf.setBounds(255, 74, 89, 23);
		contentPane.add(btnPesqCpf);
		
		//NOME
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 46, 46, 14);
		contentPane.add(lblNome);
		
		//caixa de texto NOME
		boxNome = new JTextField();	
		boxNome.setBounds(48, 43, 463, 20);	
		contentPane.add(boxNome);			
		boxNome.setColumns(10);
				
		//PESQUISA pelo NOME
		JButton btnPesqNome = new JButton("Pesquisar");
		btnPesqNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String lsCli = cliDao.selectByNome(boxNome.getText());
				String[] dados;				
				
				dados = lsCli.split(",");				
				
				boxCpf.setText(dados[0]);
				boxNome.setText(dados[1]);
				boxCategoria.setSelectedItem(dados[2]);				
				boxSal_liq.setText(dados[3]);
				boxMargem.setText(dados[4]);
			}
		});
		btnPesqNome.setBounds(521, 42, 89, 23);
		contentPane.add(btnPesqNome);
					
		//label Categoria
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 131, 78, 14);
		contentPane.add(lblCategoria);
		
		//combobox Categoria
		boxCategoria = new JComboBox<String>();
		boxCategoria.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){				
				if(boxCategoria.getSelectedItem().toString().equals("Aposentado")){
					cliente.setCategoria("Aposentado");
				}else{
					if(boxCategoria.getSelectedItem().toString().equals("Pensionista")){
						cliente.setCategoria("Pensionista");
					}else{
						if(boxCategoria.getSelectedItem().toString().equals("Funcionário Público")){
							cliente.setCategoria("Funcionário Público");
						}
					}
				}
			}
		});
		boxCategoria.setBounds(98, 128, 246, 20);
		boxCategoria.addItem("Aposentado");
		boxCategoria.addItem("Pensionista");
		boxCategoria.addItem("Funcionário Público");
		contentPane.add(boxCategoria);
		
		//label Sal_Liq
		JLabel lblSalrioLquido = new JLabel("Salário Líquido:");		
		lblSalrioLquido.setBounds(10, 199, 97, 14);
		contentPane.add(lblSalrioLquido);
		
		//caixa de texto Sal_liq
		boxSal_liq = new JTextField();		
		boxSal_liq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cliente.setSal_liq(Float.parseFloat(boxSal_liq.getText()));
			}
		});
		boxSal_liq.setBounds(96, 197, 137, 20);
		contentPane.add(boxSal_liq);		
		boxSal_liq.setColumns(10);
		
		//label MARGEM
		JLabel lblMargem = new JLabel("Margem*:");
		lblMargem.setBounds(285, 225, 61, 14);
		contentPane.add(lblMargem);
		
		//caixa de texto não editável da Margem
		boxMargem = new JTextField();
		boxMargem.setText("0");
		boxMargem.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				boxMargem.setText("" + cliente.getSal30());
			}
		});
		boxMargem.setEditable(false);		
		boxMargem.setBounds(371, 222, 239, 20);
		contentPane.add(boxMargem);
		boxMargem.setColumns(10);
		
		
		JLabel lblSeOMesmo = new JLabel("*Se o mesmo já possui empréstimos, \n"
				+ "\n aparecerá a margem RESTANTE no campo.");
		lblSeOMesmo.setBounds(288, 250, 325, 55);
		contentPane.add(lblSeOMesmo);	
		
		//label 
		JLabel lblOMesmoJ = new JLabel("Já possui empréstimo ?");
		lblOMesmoJ.setBounds(10, 238, 118, 14);
		contentPane.add(lblOMesmoJ);
		
		//resposta SIM ou NÃO
		JComboBox comboBoxSouN = new JComboBox();
		comboBoxSouN.setModel(new DefaultComboBoxModel(new String[] {"Sim", "Não"}));
		comboBoxSouN.setBounds(138, 235, 95, 20);
		contentPane.add(comboBoxSouN);
		
		//pergunta sobre o valor
		JLabel lblQualOValor = new JLabel("Qual o valor ?");
		lblQualOValor.setBounds(10, 300, 71, 14);
		contentPane.add(lblQualOValor);
		
		//valor do EMPREST JÁ realizado
		boxValorEmprest = new JTextField();
		boxValorEmprest.setText("000.00");
		boxValorEmprest.setBounds(96, 297, 137, 20);
		contentPane.add(boxValorEmprest);
		boxValorEmprest.setColumns(10);
		if(cliente.getEmprestFeito() == "Não") {
			boxValorEmprest.setEditable(false);
		}else{
			boxValorEmprest.setEditable(true);
			cliente.setEmp_feito(Float.parseFloat(boxValorEmprest.getText()));
		}		
		
		
		
		//botão SALVAR
		JButton btnCadastrarCli = new JButton("Cadastrar");
		btnCadastrarCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setCpf(boxCpf.getText());
				cliente.setNome(boxNome.getText());				
				cliente.setCategoria((String) boxCategoria.getSelectedItem());					
				cliente.setSal_liq(Float.parseFloat(boxSal_liq.getText()));
				cliente.setSal30(Float.parseFloat(boxMargem.getText()));
				if (comboBoxSouN.getSelectedItem() == "Sim"){
					cliente.setEmp_feito(Float.parseFloat(boxValorEmprest.getText()));
				}
				
				cliDao.insert(cliente);
				
				boxCpf.setText("");
				boxNome.setText("");
				boxSal_liq.setText("");
				boxCategoria.setSelectedItem("");
				boxMargem.setText("0");
			}
		});
		btnCadastrarCli.setBounds(162, 328, 101, 23);
		contentPane.add(btnCadastrarCli);
		
		//botão EXCLUIR
		JButton btnExcluirCli = new JButton("Excluir");
		btnExcluirCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliDao.deleteByCpf(boxCpf.getText());
				
				boxCpf.setText("");
				boxNome.setText("");
				boxSal_liq.setText("");
				boxCategoria.setSelectedItem("");
				boxMargem.setText("");
			}
		});
		btnExcluirCli.setBounds(381, 328, 89, 23);
		contentPane.add(btnExcluirCli);
		
		//botão ALTERAR
		JButton btnAlterarCli = new JButton("Alterar");
		btnAlterarCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setCpf(boxCpf.getText());
				cliente.setNome(boxNome.getText());				
				cliente.setCategoria((String) boxCategoria.getSelectedItem());				
				cliente.setSal_liq(Float.parseFloat(boxSal_liq.getText()));
				cliente.setSal30(Float.parseFloat(boxMargem.getText()));
				
				cliDao.updateByCpf(cliente);
				
				boxCpf.setText("");
				boxNome.setText("");
				boxSal_liq.setText("");
				boxCategoria.setSelectedItem("");
				boxMargem.setText("");
			}
		});
		btnAlterarCli.setBounds(275, 328, 89, 23);
		contentPane.add(btnAlterarCli);
				
	}
}
