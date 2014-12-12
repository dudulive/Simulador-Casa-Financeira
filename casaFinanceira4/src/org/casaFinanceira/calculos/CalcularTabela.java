package org.casaFinanceira.calculos;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

/**
 * Utilit�rio para c�lculos de presta��es para financiamentos com amortiza��o
 * pela tabela Price.
 * 
 * @author Edu Rodrigues Braz
 * @since
 */
public class CalcularTabela {

	/**
	 * Obt�m o valor juros, que � o valor presente (inicial) ao final da
	 * opera��o, ap�s sofrer todos juros por todo per�odo indicado.
	 * 
	 * @param valorPresente
	 *            Valor sujeito a juros
	 * @param juros
	 *            Percentual de juros a que se sujeira o valor presente
	 * @param periodos
	 *            Quantidade de per�odos a que o valor presente se sujeita aos
	 *            juros
	 * @return Valor Futuro
	 */
	private static double obterValorJuros(double valorPresente, double juros,
			double periodos) {
		return valorPresente * (1 + (juros / 100) * periodos);
	}

	public static double converterDoubleDecimais(double valorDouble) {
		DecimalFormat fmt = new DecimalFormat("0.000");
		if (valorDouble < 0) {
			valorDouble = 0;
		}
		String string = fmt.format(valorDouble);
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		double preco = Double.parseDouble(string2);
		return preco;
	}

	/**
	 * Obt�m o acompanhamento do financiamento, presta��o a presta��o, com
	 * informa��es do saldo devedor e valor amortizado e juros da parcela pelo
	 * Sistema Price. F�rmula aplicada :
	 * 
	 * P = (p / j) * ( 1 - ( 1 / ( ( 1 + j ) ^ n ) ) ), onde:
	 * 
	 * P � o saldo devedor inicial p � o valor da parcela (amortiza��o + juros)
	 * j � o juro n � o n�mero de presta��es
	 * 
	 * @param valorFinanciado
	 *            Valor do financiamento, que � o saldo devedor inicial
	 * @param juros
	 *            Percentual de juros a que se sujeira o valor presente, pela
	 *            periodicidade do pagamento de parcelas. Se a parcela for
	 *            mensal, deve-se registrar os juros a.m.
	 * @param periodos
	 *            Quantidade de parcelas do financiamento
	 * @return Tabela representativa do financiamento
	 */
	public static double[][] obterTabelaPrice(double valorFinanciado,
			double juros, int periodos, double valorMaximoParcela) {
		double[][] resultado = new double[periodos + 1][4];
		double jurosPrestacao = 0D;
		double prestacao = (valorFinanciado * (juros / 100))
				/ (1 - (1 / Math.pow(1 + (juros / 100), periodos)));
		if (prestacao <= valorMaximoParcela) {
			resultado[0] = new double[] { 0, 0, 0, valorFinanciado };
			for (int indice = 1; indice <= periodos; indice++) {
				jurosPrestacao = obterValorJuros(resultado[indice - 1][3],
						juros, 1) - resultado[indice - 1][3];
				double amortizacao = prestacao - jurosPrestacao;
				double saldoDevedor = resultado[indice - 1][3] - amortizacao;
				resultado[periodos][2] = resultado[periodos][2]
						+ resultado[periodos][3];
				resultado[indice] = new double[] {
						converterDoubleDecimais(prestacao),
						converterDoubleDecimais(jurosPrestacao),
						converterDoubleDecimais(amortizacao),
						converterDoubleDecimais(saldoDevedor) };
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Valor da parcela � maior que o valor limite de 30% do salario liquido!"
							+ "\nValor Limite da parcela: "
							+ valorMaximoParcela
							+ "\nValor da Primeira Parcela: " + Math.round(prestacao));
		}
		return resultado;
	}

	/**
	 * Obt�m o acompanhamento do financiamento, presta��o a presta��o, com
	 * informa��es do saldo devedor e valor amortizado e juros da parcela pelo
	 * Sistema SAC.
	 * 
	 * @param valorFinanciado
	 *            Valor do financiamento, que � o saldo devedor inicial
	 * @param juros
	 *            Percentual de juros a que se sujeira o valor presente, pela
	 *            periodicidade do pagamento de parcelas. Se a parcela for
	 *            mensal, deve-se registrar os juros a.m.
	 * @param periodos
	 *            Quantidade de parcelas do financiamento
	 * @return Tabela representativa do financiamento
	 */
	public static double[][] obterTabelaSAC(double valorFinanciado,
			double juros, int periodos, double valorMaximoParcela) {
		double amortizacao = valorFinanciado / periodos;
		double[][] resultado = new double[periodos + 1][4];
		double jurosPrestacao = 0D;
		resultado[0] = new double[] { 0, 0, 0, valorFinanciado };
		for (int indice = 1; indice <= periodos; indice++) {
			jurosPrestacao = obterValorJuros(resultado[indice - 1][3], juros,
					1) - resultado[indice - 1][3];
			double prestacao = amortizacao + jurosPrestacao;
			if (prestacao > valorMaximoParcela && indice == 1) {
				JOptionPane.showMessageDialog(null,
						"Valor da parcela � maior que o valor limite de 30% do salario liquido!"
								+ "\nValor Limite da parcela: "
								+ valorMaximoParcela
								+ "\nValor da Primeira Parcela: " + Math.round(prestacao));
				break;
			} else {
				double saldoDevedor = resultado[indice - 1][3] - amortizacao;
				resultado[indice] = new double[] {
						converterDoubleDecimais(prestacao),
						converterDoubleDecimais(jurosPrestacao),
						converterDoubleDecimais(amortizacao),
						converterDoubleDecimais(saldoDevedor) };
			}
		}
		return resultado;
	}
}
