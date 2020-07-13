package br.com.cadastro.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import br.com.cadastro.util.ConnectionFactory;
import br.com.cadastro.visao.Price;
import br.com.cadastro.visao.Sac;

public class EmprestDAO {
	
	//incluir empréstimo
		public void insertEmprest(Emprestimo emprest){
			Connection connection = null;
			PreparedStatement ps = null;
			
			String sql = "INSERT INTO emprestimo (id, valor_emprest, qtde_parc, vl_parc) "
	                  + "VALUES (?, ?, ?, ?)";
			try {
				connection = ConnectionFactory.getConnection();
				ps = connection.prepareStatement(sql);
				ps.setInt(1, emprest.getId());
				ps.setDouble(2, emprest.getVl_emprest());
				ps.setInt(3, emprest.getQtde_parcela());
				ps.setDouble(4, emprest.getPmt());				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso !!!");
			}catch(Exception ex){
				System.err.println("Erro no insertEmprest");
				ex.printStackTrace();
			}finally{
				ConnectionFactory.encerrarConexao(connection, ps);
			}
		}
}
