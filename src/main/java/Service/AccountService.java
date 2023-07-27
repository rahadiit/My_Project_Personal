package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO=new AccountDAO();
    }


    public AccountService(AccountDAO accountDAO){
        this.accountDAO=accountDAO;
    }

    public Account addUser(Account user) {
        Account added=null;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() 
        || user.getPassword() == null || user.getPassword().length() < 4) {
            return null;
        } 
        else {
               try {
                    added= accountDAO.createRegistration(user);
                
               } catch (Exception e) {
                    
               }          
        }
        return added;
    }

    public Account loginCheck(Account user){
       return accountDAO.getUser(user);
        
    }
    
}
