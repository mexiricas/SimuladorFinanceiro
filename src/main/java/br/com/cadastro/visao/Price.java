package br.com.cadastro.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.com.cadastro.controle.EmprestControl;
import br.com.cadastro.modelo.Cliente;
import br.com.cadastro.modelo.Emprestimo;

public class Price extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	static Emprestimo emprest = new Emprestimo();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Price frame = new Price(emprest);
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Price(Emprestimo emprest) {
		setTitle("Tabela Price");
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        jTablePrice = new javax.swing.JTable();
            
        jTablePrice = new JTable();
        jTablePrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
        jTablePrice.setBounds(44, 85, 533, 569);

        setSize(new Dimension(621, 646));
        setLocationRelativeTo(null);
        
        jTablePrice.setModel(new DefaultTableModel(
        	new Object[][] {},
        	new String[] {}
        )); 
        
        JScrollPane scrollPane = new JScrollPane(jTablePrice);
	    add(scrollPane, BorderLayout.CENTER);
	    this.emprest = emprest;
	}
	//Instanciando os objetos
		EmprestControl emprestControl 	= new EmprestControl();
		NumberFormat x 		= NumberFormat.getCurrencyInstance();
		Cliente cliente 	= new Cliente();
		
		
		//Variáveis
		private float salDevAttPrice;
		//Variável que recebe 30% da reda do cliente
		private double rendaCliente;
		private double pmt;
		private JTable jTablePrice;
		
		public void setValTablePrice() {
	        String[] nomeCol;
	        nomeCol = new String[]{"Mês", "Prestação", "Amortização", "Juros", "Saldo Devedor"};
	        Object[][] valor = new Object[emprest.getQtde_parcela()][5];

	        //SetarMesesTabela
	        for (int i = 0; i < emprest.getQtde_parcela(); i++) {
	            valor[i][0] = i + 1;
	        }
	        //--------------------//

	        for (int i = 0; i < emprest.getQtde_parcela(); i++) {
	            valor[i][1] = x.format(EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela()));
	            pmt = EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela());
	        }
	        
	        for (int i = 0; i < emprest.getQtde_parcela(); i++) {
	            if (i <= 0) {
	                valor[i][3] = x.format(EmprestControl.calcJurosPrice(emprest.getVl_emprest(), emprestControl.getJuros()));
	                valor[i][2] = x.format(EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela()), EmprestControl.calcJurosPrice(emprest.getVl_emprest(), emprestControl.getJuros())));
	                valor[i][4] = x.format(EmprestControl.calcSalDevPrice(emprest.getVl_emprest(), EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela()), EmprestControl.calcJurosPrice(emprest.getVl_emprest(), emprestControl.getJuros()))));
	                salDevAttPrice = (float) EmprestControl.calcSalDevPrice(emprestControl.getSalDev(), EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela()), EmprestControl.calcJurosPrice(emprest.getVl_emprest(), emprestControl.getJuros())));       
	            } else {
	                valor[i][3] 	= x.format(EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros()));
	                valor[i][2] 	= x.format(EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela()), EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros())));
	                valor[i][4] 	= x.format(EmprestControl.calcSalDevPrice(salDevAttPrice, EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela()), EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros()))));
	                salDevAttPrice 	= (float) EmprestControl.calcSalDevPrice(salDevAttPrice, EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(emprest.getVl_emprest(), emprestControl.getJuros(), emprest.getQtde_parcela()), EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros())));       
	                if(salDevAttPrice <= 0){
	                    valor[i][4] = x.format(0.00);
	                }	

	            }
	        }
	        
	        jTablePrice.setModel(new DefaultTableModel((Object[][]) valor, nomeCol));
	        
	        rendaCliente =  EmprestControl.comparaSalario(Cliente.getSal_liq());

	        
	        if(pmt <= rendaCliente){
	        	JOptionPane.showMessageDialog(null, "Emprestimo Autorizado");
	        }
	        else{
	        	JOptionPane.showMessageDialog(null, "O emprestimo não será autorizado, pois a parcela é maior ou equivalente a 30% da renda liquida do cliente!");        	
	        }

	    }
		

}
