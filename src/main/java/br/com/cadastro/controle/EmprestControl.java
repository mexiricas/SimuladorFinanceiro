package br.com.cadastro.controle;

public class EmprestControl {
	//Realizando os cálculos para montar a tabela SAC
	private static double salDev;
	private static double amort;
	private static int numParcela;
	public static double juros = 0.05;
	private static int mes;
	
	//Construtor da classe
	public EmprestControl() {

	}
		
	public double getJuros() {
		return juros;
	}
	
	public double getSalDev() {
		return salDev;
	}

	public void setSalDev(double salDev) {
		this.salDev = salDev;
	}

	public double getAmot() {
		return amort;
	}

	public void setAmort(double amort) {
		this.amort = amort;
	}
	
	public int getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(int numParcela) {
		this.numParcela = numParcela;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

///////////////////////////////////////////////////////////////
					//// TABELA SAC ////
//////////////////////////////////////////////////////////////
	
////Para o cálculo da amotização da tabela SAC
	public static double calcAmortSac(int nMeses, double valEmprestimo) {
	    return valEmprestimo / nMeses;
	}

////Para o cálculo do juros da tabela SAC	
	public static double calcJurosSac(double salDev, double juros) {
	        return (salDev * juros);
	}

////Para o cálculo do PMT da tabela SAC	
	public static double calcSacPMT(double amort, double juros) {
	    return (amort + juros);
	}

////Para o cálculo do Saldo Devedor da tabela SAC	
	public static double calcSalDevSac(double salDev, double amort) {
	    return salDev - amort;
	}

///////////////////////////////////////////////////////////////
					//// TABELA PRICE ////
//////////////////////////////////////////////////////////////	
		
////Para o cálculo do PMT da tabela PRICE
	public static double calcPricePMT(double emprestimo, double TaxaJuros, int mes) {
		return  (emprestimo * ((TaxaJuros * (Math.pow(TaxaJuros + 1, mes))) / ((Math.pow(TaxaJuros + 1, mes)) - 1)));
	}

////Para o cálculo do PMT da tabela PRICE	    
    public static double calcJurosPrice(double salDev, double TaxaJuros) {
        return (salDev * TaxaJuros);
    }
////Para o cálculo da amortização da tabela PRICE
	public static double calcAmortPrice(double PMT, double TaxaJuros) {
	    return PMT - TaxaJuros;
	}

////Para o cálculo do Saldo Devedor da tabela PRICE	
	public static double calcSalDevPrice(double salDev, double Amort) {
	    return (salDev - Amort);
	}
	
////////////////////////////////////////////////////////////
	public static Object calcParcela(double amortSac, float calcJuros) {
		// TODO Auto-generated method stub
		return null;
	}	
		
//////////////////////////////////////////////////////////////////
// Para calculo referente ao valor de 30% do salario do cliente //
//////////////////////////////////////////////////////////////////
	
	public static float comparaSalario(double salario){
		return (float)(salario * 0.3);
	}

}
