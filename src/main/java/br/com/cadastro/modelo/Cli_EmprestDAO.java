package br.com.cadastro.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.cadastro.util.ConnectionFactory;

public class Cli_EmprestDAO {
		//incluir cli_emprest
		public void insertCliEmprest(Cliente cliente, Emprestimo emprest){
			Connection connection = null;
			PreparedStatement ps = null;
			
			String sql = "INSERT INTO cli_emprest (cpf, id) "
	                  + "VALUES (?, ?)";
			try {
				connection = ConnectionFactory.getConnection();
				ps = connection.prepareStatement(sql);
				ps.setString(1, cliente.getCpf());
				ps.setInt(2, emprest.getId());
				ps.executeUpdate();
			}catch(Exception ex){
				System.err.println("Erro no insertCliEmprest");
				ex.printStackTrace();
			}finally{
				ConnectionFactory.encerrarConexao(connection, ps);
			}
		}
		
		//lista o histórico de empréstimos
		public List<Cli_Emprest> selectAllCliEmprest(Cli_Emprest cliEmp){
			List<Cli_Emprest> lsClis = null;
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "SELECT c.nome, e.valor_emprest, e.qtde_parc, e.vl_parc FROM cliente c, emprestimo e, cli_emprest cp WHERE c.cpf = cp.cpf AND e.id = cp.id";
			try {
				connection = ConnectionFactory.getConnection();
				ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();
				lsClis = new ArrayList<Cli_Emprest>();
				while (rs.next()){
					cliEmp.setNome(rs.getString("nome"));
					cliEmp.setVl_emprest(rs.getDouble("valor_emprest"));
					cliEmp.setQtde_parc(rs.getInt("qtde_parc"));
					cliEmp.setVl_parc(rs.getDouble("vl_parc"));
					lsClis.add(cliEmp);
				}
			}catch(Exception ex){
				System.err.println("Erro no selectAllCliEmprest");
				ex.printStackTrace();
			}finally{
				ConnectionFactory.encerrarConexao(connection, ps, rs);
				
			}
			return lsClis;
		}

		private Cli_EmprestDAO toString(List<Cli_Emprest> lsClis) {
			// TODO Auto-generated method stub
			return null;
		}
}
