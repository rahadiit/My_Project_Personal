package Service;
import java.util.ArrayList;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO mymsg;
    Message returnmsg;


    public MessageService() {
        this.mymsg=new MessageDAO();
    }

    public MessageService(MessageDAO mymsg) {
        this.mymsg = mymsg;
    }

    public Message addMessage(Message msg) {

        if(msg==null||!mymsg.userExists(msg.getPosted_by())){
            return null;
        }

        if(!(msg.getMessage_text().equals(""))&&(msg.getMessage_text().length()<255)){
                returnmsg= mymsg.createMessage(msg);
                return returnmsg;

        }
            
      else{
         return null;

      }    
      
    }

    public ArrayList<Message> getMessage( ){

        return mymsg.retriveMessage();

    }


    public Message getMessagesByID(int inputMessageID){

        if(mymsg.retriveMessageByID(inputMessageID)==null){
            return null;
        }

        return mymsg.retriveMessageByID(inputMessageID);
    }


    public Message  getDeleteByID(int inputMessageID){

        try {
                 Message getdeleted= mymsg.retriveDeleteByID(inputMessageID);
                 if(getdeleted!=null){
                     return getdeleted;

                 }
                 else{
                    return null;
                 }
                

            }

     
        catch (Exception e) {
            e.printStackTrace();
        }

       return null;
    }



     public ArrayList<Message> getAllMessagesByUser(int inputAcoountID){

        return mymsg.retriveAllMessageByUser(inputAcoountID);
    }
    


    

     public Message UpdateMessage(int inputmessageID,Message msg) {
        Message returnmsg=null;

        if(msg==null){
            return null;
        }

        if(!msg.getMessage_text().equals(null)){
            returnmsg= mymsg.retriveUpdatedMessageByID(inputmessageID,msg);

        }
            
        
        return returnmsg;
    }
   
}
