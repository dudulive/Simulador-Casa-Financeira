package org.casaFinanceira.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.casaFinanceira.entidades.Pessoa;
import org.casaFinanceira.util.ConnectionFactory;

public class JDBCPessoaDAO implements PessoaDAO {

	private Connection connection;

	public JDBCPessoaDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public void insert(Pessoa pessoa) {
		PreparedStatement psInsert = null;
		try {
			psInsert = connection
					.prepareStatement("insert into pessoas (nome, cpf, salarioLiquido, valorMaximoParcela, situacao) values (?,?,?,?,?)");
			psInsert.setString(1, pessoa.getNome());
			psInsert.setString(2, pessoa.getCpf());
			psInsert.setDouble(3, pessoa.getSalarioLiquido());
			psInsert.setDouble(4, pessoa.getValorMaximoParcela());
			psInsert.setString(5, pessoa.getSituacao());
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.fecharConexao(connection, psInsert);
		}
	}

	@Override
	public void update(Pessoa pessoa) {
		PreparedStatement psUpdate = null;
		try {
			psUpdate = connection
					.prepareStatement("update pessoas set nome = ?, cpf = ?, salarioLiquido = ?, valorMaximoParcela = ? , situacao = ? WHERE id = ?");
			psUpdate.setString(1, pessoa.getNome());
			psUpdate.setString(2, pessoa.getCpf());
			psUpdate.setDouble(3, pessoa.getSalarioLiquido());
			psUpdate.setDouble(4, pessoa.getValorMaximoParcela());
            psUpdate.setString(5, pessoa.getSituacao());
            psUpdate.setLong(6, pessoa.getId());
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
					.prepareStatement("delete from pessoas where id = ?");
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
	public Pessoa findByID(Long id) {
		Pessoa pessoa = null;
		String sql = "SELECT * FROM pessoas where id = ?";
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;

		try {
			connection = this.connection;
			psSelect = connection.prepareStatement(sql);
			psSelect.setLong(1, id);
			rsSelect = psSelect.executeQuery();
			if (rsSelect.next()) {
				pessoa = new Pessoa();
				pessoa.setNome(rsSelect.getString("nome"));
				pessoa.setCpf(rsSelect.getString("cpf"));
				pessoa.setSalarioLiquido(rsSelect.getDouble("salarioLiquido"));
				pessoa.setValorMaximoParcela(rsSelect
						.getDouble("valorMaximoParcela"));
				pessoa.setSituacao(rsSelect.getString("situacao"));
			}

		} catch (SQLException e) {
			System.out
					.println("Erro no método findByAll da classe JDBCPessoaDAO.");
			System.out.println("Descrição do erro: " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, psSelect);
		}
		return pessoa;
	}

	

}
