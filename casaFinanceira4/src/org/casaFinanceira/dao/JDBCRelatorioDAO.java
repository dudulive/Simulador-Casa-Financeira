package org.casaFinanceira.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.casaFinanceira.entidades.Relatorio;
import org.casaFinanceira.util.ConnectionFactory;

public class JDBCRelatorioDAO {

	private Connection connection;
	
	public JDBCRelatorioDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public List<Relatorio> listarTodos() {

		List<Relatorio> relatorios = null;
		String sql = "SELECT pessoas.id,pessoas.nome, pessoas.cpf, pessoas.salarioLiquido, pessoas.valorMaximoParcela, "
				+ " pessoas.situacao, financiamentos.valorFinanciado, financiamentos.taxaJuros, "
				+ " financiamentos.periodos, financiamentos.tipo , financiamentos.dataCadastro "
				+ " FROM financiamentos,pessoas WHERE financiamentos.id = pessoas.id ORDER BY id ";
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		try {
			connection = this.connection;
			psSelect = connection.prepareStatement(sql);
			rsSelect = psSelect.executeQuery();
			relatorios = new ArrayList<>();
			while (rsSelect.next()) {
				Relatorio relatorio = new Relatorio();
				relatorio.setId(rsSelect.getLong("id"));
				relatorio.setNome(rsSelect.getString("nome"));
				relatorio.setCpf(rsSelect.getString("cpf"));
				relatorio.setSalarioLiquido(rsSelect.getDouble("salarioLiquido"));
				relatorio.setValorMaximoParcela(rsSelect
						.getDouble("valorMaximoParcela"));
				relatorio.setSituacao(rsSelect.getString("situacao"));
				relatorio.setValorFinanciado(rsSelect
						.getDouble("valorFinanciado"));
				relatorio.setTaxaJuros(rsSelect.getDouble("taxaJuros"));
				relatorio.setPeriodos(rsSelect.getInt("periodos"));
				relatorio.setTipo(rsSelect.getString("tipo"));
				relatorio.setDataCadastro(rsSelect.getDate("dataCadastro"));
				relatorios.add(relatorio);
			}
		} catch (SQLException e) {
			System.out
					.println("Erro no método findByAll da classe JDBCFianciamentoDAO.");
			System.out.println("Descrição do erro: " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, psSelect, rsSelect);
		}

		return relatorios;
	}
	
	public List<Relatorio> listarTodosData(java.util.Date data) {
		List<Relatorio> relatorios = null;
		String sql = "SELECT pessoas.id, pessoas.nome, pessoas.cpf, pessoas.salarioLiquido, pessoas.valorMaximoParcela, "
				+ " pessoas.situacao, financiamentos.valorFinanciado, financiamentos.taxaJuros, "
				+ " financiamentos.periodos, financiamentos.tipo , financiamentos.dataCadastro "
				+ " FROM financiamentos,pessoas"
				+ " WHERE financiamentos.dataCadastro = ? "
				+ "AND financiamentos.id = pessoas.id ORDER BY id ";
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		try {
			connection = this.connection;
			psSelect = connection.prepareStatement(sql);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			psSelect.setDate(1,  sqlDate);
			rsSelect = psSelect.executeQuery();
			relatorios = new ArrayList<>();
			while (rsSelect.next()) {
				Relatorio relatorio = new Relatorio();
				relatorio.setId(rsSelect.getLong("id"));
				relatorio.setNome(rsSelect.getString("nome"));
				relatorio.setCpf(rsSelect.getString("cpf"));
				relatorio.setSalarioLiquido(rsSelect.getDouble("salarioLiquido"));
				relatorio.setValorMaximoParcela(rsSelect
						.getDouble("valorMaximoParcela"));
				relatorio.setSituacao(rsSelect.getString("situacao"));
				relatorio.setValorFinanciado(rsSelect
						.getDouble("valorFinanciado"));
				relatorio.setTaxaJuros(rsSelect.getDouble("taxaJuros"));
				relatorio.setPeriodos(rsSelect.getInt("periodos"));
				relatorio.setTipo(rsSelect.getString("tipo"));
				relatorio.setDataCadastro(rsSelect.getDate("dataCadastro"));
				relatorios.add(relatorio);
			}
		} catch (SQLException e) {
			System.out
					.println("Erro no método findByAll da classe JDBCFianciamentoDAO.");
			System.out.println("Descrição do erro: " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, psSelect, rsSelect);
		}
		return relatorios;
	}
	
	public List<Relatorio> listarTodosCPF(String cpf) {
		List<Relatorio> relatorios = null;
		String sql = "SELECT pessoas.id, pessoas.nome, pessoas.cpf, pessoas.salarioLiquido, pessoas.valorMaximoParcela, "
				+ " pessoas.situacao, financiamentos.valorFinanciado, financiamentos.taxaJuros, "
				+ " financiamentos.periodos, financiamentos.tipo , financiamentos.dataCadastro "
				+ " FROM financiamentos,pessoas"
				+ " WHERE pessoas.cpf = ? "
				+ "AND financiamentos.id = pessoas.id ORDER BY id ";
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		try {
			connection = this.connection;
			psSelect = connection.prepareStatement(sql);
			psSelect.setString(1,  cpf);
			rsSelect = psSelect.executeQuery();
			relatorios = new ArrayList<>();
			while (rsSelect.next()) {
				Relatorio relatorio = new Relatorio();
				relatorio.setId(rsSelect.getLong("id"));
				relatorio.setNome(rsSelect.getString("nome"));
				relatorio.setCpf(rsSelect.getString("cpf"));
				relatorio.setSalarioLiquido(rsSelect.getDouble("salarioLiquido"));
				relatorio.setValorMaximoParcela(rsSelect
						.getDouble("valorMaximoParcela"));
				relatorio.setSituacao(rsSelect.getString("situacao"));
				relatorio.setValorFinanciado(rsSelect
						.getDouble("valorFinanciado"));
				relatorio.setTaxaJuros(rsSelect.getDouble("taxaJuros"));
				relatorio.setPeriodos(rsSelect.getInt("periodos"));
				relatorio.setTipo(rsSelect.getString("tipo"));
				relatorio.setDataCadastro(rsSelect.getDate("dataCadastro"));
				relatorios.add(relatorio);
			}
		} catch (SQLException e) {
			System.out
					.println("Erro no método findByAll da classe JDBCFianciamentoDAO.");
			System.out.println("Descrição do erro: " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, psSelect, rsSelect);
		}
		return relatorios;
	}
	
	public List<Relatorio> listarTodosCPFData(java.util.Date data ,String cpf) {
		List<Relatorio> relatorios = null;
		String sql = "SELECT pessoas.id, pessoas.nome, pessoas.cpf, pessoas.salarioLiquido, pessoas.valorMaximoParcela,  "
				+ " pessoas.situacao, financiamentos.valorFinanciado, financiamentos.taxaJuros,  financiamentos.periodos, "
				+ " financiamentos.tipo , financiamentos.dataCadastro  "
				+ " FROM financiamentos,pessoas "
				+ " WHERE financiamentos.dataCadastro = ? "
				+ " AND pessoas.cpf = ? "
				+ " AND financiamentos.id = pessoas.id ORDER BY id ";
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		try {
			connection = this.connection;
			psSelect = connection.prepareStatement(sql);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			psSelect.setDate(1,  sqlDate);
			psSelect.setString(2,  cpf);
			rsSelect = psSelect.executeQuery();
			relatorios = new ArrayList<>();
			while (rsSelect.next()) {
				Relatorio relatorio = new Relatorio();
				relatorio.setId(rsSelect.getLong("id"));
				relatorio.setNome(rsSelect.getString("nome"));
				relatorio.setCpf(rsSelect.getString("cpf"));
				relatorio.setSalarioLiquido(rsSelect.getDouble("salarioLiquido"));
				relatorio.setValorMaximoParcela(rsSelect
						.getDouble("valorMaximoParcela"));
				relatorio.setSituacao(rsSelect.getString("situacao"));
				relatorio.setValorFinanciado(rsSelect
						.getDouble("valorFinanciado"));
				relatorio.setTaxaJuros(rsSelect.getDouble("taxaJuros"));
				relatorio.setPeriodos(rsSelect.getInt("periodos"));
				relatorio.setTipo(rsSelect.getString("tipo"));
				relatorio.setDataCadastro(rsSelect.getDate("dataCadastro"));
				relatorios.add(relatorio);
			}
		} catch (SQLException e) {
			System.out
					.println("Erro no método findByAll da classe JDBCFianciamentoDAO.");
			System.out.println("Descrição do erro: " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, psSelect, rsSelect);
		}
		return relatorios;
	}
	
}
