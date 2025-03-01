package com.company.tennis.core.repository;

import com.company.tennis.core.DataSourceProvider;
import com.company.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepositoryImpl {

    public void create (Match match) {

        Connection conn = null;
        try {

            // connection db
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);


            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,match.getEpreuve().getId());
            preparedStatement.setLong(2,match.getVainqueur().getId());
            preparedStatement.setLong(3,match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            // recupérer l'id du joueur créé
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                match.setId(resultSet.getLong(1));
            }

            System.out.println("Match créé");
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn!=null) conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
