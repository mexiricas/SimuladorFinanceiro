package br.com.cadastro.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setResizable(false);
		setTitle("BEM VINDO !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.setBounds(422, 76, 209, 64);
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManterCliente cadastraCli = new ManterCliente();
				cadastraCli.setVisible(true);				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCadastrarCliente);
		
		JButton btnFazerEmprest = new JButton("Fazer Empréstimo");
		btnFazerEmprest.setBounds(422, 157, 209, 64);
		btnFazerEmprest.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnFazerEmprest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManterEmprest mantEmprest = new ManterEmprest();
				mantEmprest.setVisible(true);
			}
		});
		contentPane.add(btnFazerEmprest);
		
		JLabel lblEscolhaAOpo = new JLabel("Escolha a opção desejada:");
		lblEscolhaAOpo.setBounds(249, 22, 171, 34);
		contentPane.add(lblEscolhaAOpo);
		
		JLabel lblParaCadastrarDeletar = new JLabel("Para cadastrar, deletar, modificar e pesquisar clientes:");
		lblParaCadastrarDeletar.setBounds(36, 82, 349, 52);
		contentPane.add(lblParaCadastrarDeletar);
		
		JLabel lblParaRealizaoDe = new JLabel("Para realização de empréstimos e maneiras de pagamento:");
		lblParaRealizaoDe.setBounds(36, 175, 349, 28);
		contentPane.add(lblParaRealizaoDe);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//default icon, custom title
		        int n = JOptionPane.showConfirmDialog(null, "Você deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);

		        if(true){
		            JOptionPane.showMessageDialog(null, "Até à Próxima!");
		        }	        

		        System.exit(0);
			}
		});
		btnSair.setBounds(296, 353, 89, 23);
		contentPane.add(btnSair);
	}

	public JTextPane getTextPane() {
		return getTextPane();
	}
}
