package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    Message mymsg;

   

    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT INTO message(posted_by,message_text,time_posted_epoch) VALUES(?,?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(),message.getMessage_text(),message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }


    public boolean userExists(int post_by) {

        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT account_id FROM account WHERE account_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString method here.
           preparedStatement.setInt(1,post_by);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
                
                int val= rs.getInt("account_id");
                if(val==post_by){
                    return true;

                }
                  
            }
        }

            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        return false;
    
        }


    public ArrayList<Message> retriveMessage(){

        ArrayList<Message> list=new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString method here.
           
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){

                Message createMsg=new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                list.add(0, createMsg);
            }      
            
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

 public Message retriveMessageByID(int InputmessageID){

        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "SELECT * FROM message WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString method here.

            preparedStatement.setInt(1,InputmessageID);
           
            ResultSet rs = preparedStatement.executeQuery();
            

            while(rs.next()){

               Message createMsg=new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                return createMsg;
            }      
            
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    

     public Message retriveDeleteByID(int InputmessageID){
        Message createMsg=null;

        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            
            String sql2="SELECT * FROM message WHERE message_id=?";
           

            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            //write preparedStatement's setString method here.

            
            preparedStatement2.setInt(1,InputmessageID);
           
             

            ResultSet rs=preparedStatement2.executeQuery();
            

            while(rs.next()){

               createMsg=new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"),rs.getLong("time_posted_epoch"));
            
            }  
            
            
            String sql1 = "DELETE FROM message WHERE message_id=?";
             PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
             preparedStatement1.setInt(1,InputmessageID);
             preparedStatement1.execute();
            
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        if(createMsg!=null){
            return createMsg;

        }

        else{
            return null;
        }
        
    }


    public ArrayList<Message> retriveAllMessageByUser(int InputAccountID){

        Connection connection = ConnectionUtil.getConnection();

        System.out.println("acc Id"+" "+InputAccountID);

        ArrayList<Message> messagelist=new ArrayList<>();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "SELECT * FROM message WHERE posted_by=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString method here.

            preparedStatement.setInt(1,InputAccountID);
           
            ResultSet rs = preparedStatement.executeQuery();
            
            

            while(rs.next()){

               Message createMsg=new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                messagelist.add(createMsg);
           
              
            }      
            
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

           
       return  messagelist;
    }



     public Message retriveUpdatedMessageByID(int InputmessageID, Message updatemsg){

        Connection connection = ConnectionUtil.getConnection();
        Message createMsg=null;
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "SELECT * FROM message WHERE message_id=?";
            String sqlupdate="UPDATE message SET message_text=? WHERE message_id=?";
 


            PreparedStatement preparedStatementupdate = connection.prepareStatement(sqlupdate);

            //write preparedStatement's setString method here.

            preparedStatementupdate.setString(1, updatemsg.getMessage_text());
            preparedStatementupdate.setInt(2,InputmessageID);
            preparedStatementupdate.executeUpdate();



            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,InputmessageID);
           
            ResultSet rs = preparedStatement.executeQuery();
            

            while(rs.next()){

               createMsg=new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                
            }      
            
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
       return createMsg;
    }



}
