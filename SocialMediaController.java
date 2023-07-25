package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService account;
    MessageService msgService;

    
    public SocialMediaController() {
        this.account = new AccountService();
        this.msgService=new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
   public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::PostAddUser);
        app.post("/login", this::PostLogin);
        app.post("/messages", this::PostMessage);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    public void PostAddUser(Context context) {

        Account user=context.bodyAsClass(Account.class);

        try {
            Account addedUser=account.addUser(user);
            context.json(addedUser);
           
            
        } catch (Exception e) {
        
            context.status(400);
        }
            
    }

    public void PostLogin(Context context) {
             Account user=context.bodyAsClass(Account.class);

            
             Account use=account.loginCheck(user);
             if(use==null){
                context.status(401);
             }

             else{
                context.json(use);
             }
                 
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    public void PostMessage(Context context) {

        try {
            Message msg=context.bodyAsClass(Message.class);
             if(msg.equals(null)){
            context.status(400);
            }
             else{
             context.json(msgService.addMessage(msg));

            }
            
        } catch (Exception e) {
            // TODO: handle exception
            context.status(400);
        }
        

       

       
       
    }
            
}

