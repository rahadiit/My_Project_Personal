package Service;
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
    
}
