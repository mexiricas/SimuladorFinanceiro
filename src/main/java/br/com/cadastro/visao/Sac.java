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

public class Sac extends JFrame {

	
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
					Sac frame = new Sac(emprest);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Sac(Emprestimo emprest) {
	setTitle("Tabela SAC");
			
		    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setResizable(false);
	
	        jTableSAC1 = new JTable();
	        jTableSAC1.setBorder(new LineBorder(Color.LIGHT_GRAY));
	        jTableSAC1.setBounds(44, 85, 533, 569);
	        
	        
	
	        setSize(new Dimension(621, 694));
	        setLocationRelativeTo(null);
	        
	        jTableSAC1.setModel(new DefaultTableModel(
	        	new Object[][] {
	        			
	        	},
	        	new String[] {
	        		
	        	}
	        ));
	        JScrollPane scrollPane = new JScrollPane(jTableSAC1);
		    add(scrollPane, BorderLayout.CENTER);
		    this.emprest = emprest;
	}
////OBJETOS INSTANCIADOS PARA USO NESTA CLASSE
	EmprestControl emprestcontrol	= new EmprestControl();
	ManterEmprest mantEmprest = new ManterEmprest();
	NumberFormat x 		= NumberFormat.getCurrencyInstance();
	Cliente cli = new Cliente();
	
		
	private JTable jTableSAC1;
		
	////DECLARAÇÃO DAS VARIAVEIS
	double salDevAtt;
	private float pmt;
	private float rendaCliente;
	
	public void setValTableSAC() {
		 
		emprestcontrol.setSalDev(emprest.getVl_emprest());
		 
		String[] nomeColunas;
	    nomeColunas = new String[]{"Mês", "Prestações", "Amortização", "Juros", "Saldo Devedor"};
	    Object[][] data = new Object[emprest.getQtde_parcela()][5];

	    ////Inserir meses
	    for (int i = 0; i < emprest.getQtde_parcela(); i++) {
	    	data[i][0] = i + 1;
	    }
	        
	    /////////////////////////////////////////////////////////////////////

	    ////Inserir a amortização
	    for (int i = 0; i < emprest.getQtde_parcela(); i++) {
	        data[i][2] = x.format(EmprestControl.calcAmortSac(emprest.getQtde_parcela(), emprest.getVl_emprest()));
	    }
	    
	    /////////////////////////////////////////////////////////////////////    

	    ////Inserir o juros
	    for (int i = 0; i < emprest.getQtde_parcela(); i++) {
	        if (i <= 0) {
	            	
	        	salDevAtt =  EmprestControl.calcSalDevSac(emprestcontrol.getSalDev(), EmprestControl.calcAmortSac(emprest.getQtde_parcela(), emprest.getVl_emprest()));
	            data[i][4] = x.format(salDevAtt);
	            data[i][3] = x.format(EmprestControl.calcJurosSac(emprestcontrol.getSalDev(), emprestcontrol.getJuros()));
	            data[i][1] = x.format(EmprestControl.calcSacPMT(EmprestControl.calcAmortSac(emprest.getQtde_parcela(), emprest.getVl_emprest()), EmprestControl.calcJurosSac(emprestcontrol.getSalDev(), emprestcontrol.getJuros())));
	            pmt = (float) EmprestControl.calcSacPMT(EmprestControl.calcAmortSac(emprest.getQtde_parcela(), emprest.getVl_emprest()), EmprestControl.calcJurosSac(emprestcontrol.getSalDev(), emprestcontrol.getJuros()));
	            
	        } else {
	        	data[i][3] = x.format(EmprestControl.calcJurosSac(salDevAtt, emprestcontrol.getJuros()));
	            data[i][1] = x.format(EmprestControl.calcSacPMT(EmprestControl.calcAmortSac(emprest.getQtde_parcela(), emprest.getVl_emprest()), EmprestControl.calcJurosSac(salDevAtt, emprestcontrol.getJuros())));
	            salDevAtt =  EmprestControl.calcSalDevSac(salDevAtt, EmprestControl.calcAmortSac(emprest.getQtde_parcela(), emprest.getVl_emprest()));
	            if (salDevAtt >= 0) {
	            	data[i][4] = x.format(salDevAtt);
	            } else {
	                data[i][4] = x.format(0.00);
	            }

	        }

	    }
	    //----------------//
	       
	    jTableSAC1.setModel(new DefaultTableModel((Object[][]) data, nomeColunas));
        
	    rendaCliente = EmprestControl.comparaSalario(Cliente.getSal_liq());
	        
	    if(pmt <= rendaCliente){
	     	JOptionPane.showMessageDialog(null, "Emprestimo Autorizado!");
	    }else{
	      	JOptionPane.showMessageDialog(null, "O emprestimo não será autorizado, pois a parcela é maior ou equivalente a 30% da renda liquida do cliente!");
	    }
	        
	}

}
