package com.company.tennis.core.repository;

import com.company.tennis.core.DataSourceProvider;
import com.company.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {

    public void create (Score score) {

        Connection conn = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, score.getMatch().getId());

            if (score.getSet1() == null) {
                preparedStatement.setNull(2, Types.TINYINT);
            } else {
                preparedStatement.setByte(2, score.getSet1());
            }
            if (score.getSet2() == null) {
                preparedStatement.setNull(3, Types.TINYINT);
            } else {
                preparedStatement.setByte(3, score.getSet2());
            }

            if (score.getSet3() == null) {
                preparedStatement.setNull(4, Types.TINYINT);
            } else {
                preparedStatement.setByte(4, score.getSet3());
            }
            if (score.getSet4() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5, score.getSet4());
            }
            if (score.getSet5() == null) {
                preparedStatement.setNull(6, Types.TINYINT);
            } else {
                preparedStatement.setByte(6, score.getSet5());
            }

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                score.setId(resultSet.getLong(1));
            }

            System.out.println("Score créé");

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn !=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
