package com.company.tennis.core.repository;

import com.company.tennis.core.DataSourceProvider;
import com.company.tennis.core.entity.Joueur;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public List<Joueur> list () {

        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,PRENOM,SEXE FROM JOUEUR");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Joueurs lus");

            while (resultSet.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(resultSet.getLong("ID"));
                joueur.setNom(resultSet.getString("NOM"));
                joueur.setPrenom(resultSet.getString("PRENOM"));
                joueur.setSexe(resultSet.getString("SEXE").charAt(0));
                joueurs.add(joueur);
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
        return joueurs;
    }

    public Joueur getById (Long id) {

        Connection conn = null;
        Joueur joueur = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("select NOM,PRENOM,SEXE FROM JOUEUR WHERE ID=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(resultSet.getString("NOM"));
                joueur.setPrenom(resultSet.getString("PRENOM"));
                joueur.setSexe(resultSet.getString("SEXE").charAt(0));
            }

            System.out.println("Joueur lu");

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
        return joueur;
    }

    public void create (Joueur joueur) {

        Connection conn = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                joueur.setId(resultSet.getLong(1));
            }

            System.out.println("Joueur créé");

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

    public void update (Joueur joueur) {

        Connection conn = null;

        try {

            DataSource dataSource =  DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=?,SEXE=? WHERE ID=?");
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate();

            System.out.println("Joueur modifié");

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

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM JOUEUR  WHERE ID=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Joueur supprimé");

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
