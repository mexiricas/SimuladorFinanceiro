package br.com.cadastro.modelo;

public class Emprestimo {
	private double vl_emprest;
	private int qtde_parcela;
	private String tpTab;
	private double pmt;
	private int id;
	
	
	public double getVl_emprest() {
		return vl_emprest;
	}
	public void setVl_emprest(double vl_emprest) {
		this.vl_emprest = vl_emprest;
	}
	public int getQtde_parcela() {
		return qtde_parcela;
	}
	public void setQtde_parcela(int qtde_parcela) {
		this.qtde_parcela = qtde_parcela;
	}
	public String getTpTab() {
		return tpTab;
	}
	public void setTpTab(String tpTab) {
		this.tpTab = tpTab;
	}
	public double getPmt() {
		return pmt;
	}
	public void setPmt(double pmt) {
		this.pmt = pmt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
