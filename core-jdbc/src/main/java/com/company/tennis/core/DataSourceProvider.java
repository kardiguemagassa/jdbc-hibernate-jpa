package com.company.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static BasicDataSource singleDataSource;

    public static DataSource getSingleDataSourceInstance(){
        if (singleDataSource==null){
            singleDataSource=new BasicDataSource();
            singleDataSource.setInitialSize(5);
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/jnesis_tennis?useSSL=false");

            singleDataSource.setUsername("jnesis");
            singleDataSource.setPassword("jnesis");
        }
        return singleDataSource;
    }
}