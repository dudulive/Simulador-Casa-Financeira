package org.casaFinanceira.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.casaFinanceira.entidades.Financiamento;
import org.casaFinanceira.util.ConnectionFactory;

public class JDBCFinanciamentoDAO implements FinanciamentoDAO {

	private Connection connection;
	private Financiamento financiamento;

	public JDBCFinanciamentoDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public void insert(Financiamento financiamento) {
		PreparedStatement psInsert = null;
		try {
			psInsert = connection
					.prepareStatement("insert into financiamentos (valorFinanciado, taxaJuros, periodos, tipo , dataCadastro ,idPessoa) values (?,?,?,?,?,?)");
			psInsert.setDouble(1, financiamento.getValorFinanciado());
			psInsert.setDouble(2, financiamento.getTaxaJuros());
			psInsert.setInt(3, financiamento.getPeriodos());
			psInsert.setString(4, financiamento.getTipo());
			java.sql.Date sqlDate = new java.sql.Date(financiamento.getDataCadastro().getTime());
			psInsert.setDate(5, sqlDate);
			psInsert.setInt(6, this.idPessoa());
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.fecharConexao(connection, psInsert);
		}

	}

	public Integer idPessoa() {
		Integer idPessoa = null;
		String sql = "SELECT Max(id) FROM pessoas";
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;

		try {
			connection = this.connection;
			psSelect = connection.prepareStatement(sql);
			rsSelect = psSelect.executeQuery();
			if (rsSelect.next()) {
				Object result = rsSelect.getObject(1);
				idPessoa = ((Integer) result);
			}

		} catch (SQLException e) {
			System.out.println("Erro no método da classe JDBCPessoaDAO.");
			System.out.println("Descrição do erro: " + e.getMessage());
		}
		return idPessoa;
	}

	@Override
	public void update(Financiamento financiamento) {
		PreparedStatement psUpdate = null;
		try {
			psUpdate = connection
					.prepareStatement("UPDATE financiamentos SET valorFinanciado = ?, taxaJuros = ?, periodos = ? , tipo = ? WHERE idPessoa = ?");
			psUpdate.setDouble(1, financiamento.getValorFinanciado());
			psUpdate.setDouble(2, financiamento.getTaxaJuros());
			psUpdate.setInt(3, financiamento.getPeriodos());
			psUpdate.setString(4, financiamento.getTipo());
			psUpdate.setLong(5, financiamento.getIdPessoa());
			psUpdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.fecharConexao(connection, psUpdate);
		}
	}

	@Override
	public boolean delete(Long id) {
		PreparedStatement psDelete = null;
		try {
			psDelete = connection
					.prepareStatement("delete from financiamentos where idPessoa = ?");
			psDelete.setLong(1, id);
			psDelete.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.fecharConexao(connection, psDelete);
		}
	}

	@Override
	public Financiamento findByID (Long id , java.util.Date data) {
		financiamento = null;
		String sql = "SELECT pessoas.id,financiamentos.id,financiamentos.valorFinanciado, financiamentos.taxaJuros, "
				+ " financiamentos.periodos , financiamentos.tipo , financiamentos.dataCadastro "
				+ " FROM financiamentos,pessoas WHERE pessoas.id = ? "
				+ " AND financiamentos.dataCadastro = ? "
				+ " AND financiamentos.id = pessoas.id ";
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		try {
			connection = this.connection;
			psSelect = connection.prepareStatement(sql);
			psSelect.setLong(1, id);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			psSelect.setDate(2,  sqlDate);
			rsSelect = psSelect.executeQuery();
			if (rsSelect.next()) {
				financiamento = new Financiamento();
				financiamento.setValorFinanciado(rsSelect
						.getDouble("valorFinanciado"));
				financiamento.setTaxaJuros(rsSelect.getDouble("taxaJuros"));
				financiamento.setPeriodos(rsSelect.getInt("periodos"));
				financiamento.setTipo(rsSelect.getString("tipo"));
				financiamento.setDataCadastro(rsSelect.getDate("dataCadastro"));
			}

		} catch (SQLException e) {
			System.out
					.println("Erro no método findByAll da classe JDBCPessoaDAO.");
			System.out.println("Descrição do erro: " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, psSelect, rsSelect);
		}
		return financiamento;
	}

}
