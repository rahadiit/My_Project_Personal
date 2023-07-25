package DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    public Account createRegistration(Account user){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT INTO account(username,password) VALUES(?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, user.getUsername(),user.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }


    public Account getUser(Account user){
        Connection connection = ConnectionUtil.getConnection();
        try {

           
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE account.username=? AND account.password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
                
                Account login = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return login;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    // public Account getAllUsers(){

    //     Connection connection = ConnectionUtil.getConnection();
    //     try {

           
    //         //Write SQL logic here
    //         String sql = "SELECT * FROM account";
    //         PreparedStatement preparedStatement = connection.prepareStatement(sql);

    //         //write preparedStatement's setInt method here.

    //         preparedStatement.setString(1, user.getUsername());
    //         preparedStatement.setString(2, user.getPassword());
    //         ResultSet rs = preparedStatement.executeQuery();
            
            
    //         while(rs.next()){
                
    //             Account login = new Account(rs.getInt("account_id"),
    //                     rs.getString("username"),
    //                     rs.getString("password"));
    //             return login;
    //         }
    //     }catch(SQLException e){
    //         System.out.println(e.getMessage());
    //     }

    //     return null;

    // }
    
}
