package br.com.cadastro.modelo;


public class Cliente {
	
	private String cpf;
	private String nome;
	private String categoria;
	private static float sal_liq;
	private float sal30;
	private String emprestFeito;
	private static float vl_emp_feito = 0;	
		
	
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setSal_liq(float sal_liq) {
		this.sal_liq = sal_liq;
	}
	public void setSal30(float sal30) {
		sal30 = (float)(sal_liq * 0.3);
		this.sal30 = sal30;
	}
	public void setEmprestFeito(String emprestFeito) {
		this.emprestFeito = emprestFeito;
	}
	public void setEmp_feito(float emp_feito) {
		this.vl_emp_feito = emp_feito;
	}	
	
	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public static float getSal_liq() {
		return sal_liq;
	}
	public float getSal30() {
		return sal30;
	}
	public String getEmprestFeito() {
		return emprestFeito;
	}
	public float getEmp_feito() {
		return vl_emp_feito;
	}
	
	@Override
	public String toString() {
		return cpf + "," + nome + ","
				+ categoria + "," + sal_liq + "," + sal30;
	}	
	
	
}
