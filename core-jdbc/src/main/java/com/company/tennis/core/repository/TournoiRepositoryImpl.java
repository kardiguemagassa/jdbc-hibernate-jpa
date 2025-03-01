package com.company.tennis.core.repository;

import com.company.tennis.core.DataSourceProvider;
import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public List<Tournoi> list () {

        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,CODE FROM TOURNOI");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Tournois lus");
            conn.commit();

            while (resultSet.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setId(resultSet.getLong("ID"));
                tournoi.setNom(resultSet.getString("NOM"));
                tournoi.setCode(resultSet.getString("CODE"));
                tournois.add(tournoi);
            }

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
        return tournois;
    }

    public Tournoi getById (Long id) {

        Connection conn = null;
        Tournoi tournoi = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("select NOM,CODE FROM TOURNOI WHERE ID=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tournoi = new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(resultSet.getString("NOM"));
                tournoi.setCode(resultSet.getString("CODE"));

            }

            System.out.println("Tournoi lu");
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
        return tournoi;
    }

    public void create (Tournoi tournoi) {

        Connection conn = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO TOURNOI (NOM,CODE) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tournoi.setId(resultSet.getLong(1));
            }

            System.out.println("Tournoi créé");
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

    public void update (Tournoi tournoi) {

        Connection conn = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?,CODE=? WHERE ID=?");
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3, tournoi.getId());

            preparedStatement.executeUpdate();

            System.out.println("Tournoi modifié");
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

    public void delete (Long id) {

        Connection conn = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM TOURNOI  WHERE ID=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Tournoi supprimé");
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
