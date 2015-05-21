package com.taylan.security;


import com.taylan.persistence.DAO.UserInfo;
import com.taylan.persistence.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author  Taylan Kurt <taylankurt34@gmail.com>
 * 
 *      Authenticator
 */
public class Authenticator {
    private static UserInfo userr;
    
     public static boolean validate(String user, String password){
        Session session=null;
        Transaction tx = null;
        String validUserPassword;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);
            
            /* TABLE VIEW PART START*/
            String hql="FROM UserInfo u WHERE u.usernamee = :usernamee";
            Query query =session.createQuery(hql);
            query.setParameter("usernamee", user);
            
            if((query.uniqueResult()!= null) ){
                setUserr((UserInfo) query.uniqueResult());
                validUserPassword=getUserr().getPasswordd();
            }else
                validUserPassword="nvhk123584eou243ýugf2uý3vtyQQQ24382953892hfufnsab";
   
            tx.commit();
        } catch (HibernateException e) {
            try{
                tx.rollback();
            }catch(RuntimeException rbe){
                rbe.printStackTrace();
            }
            throw e;
        } finally {
            if(session!=null){
    		session.close();
            } 
        }
        return validUserPassword != null && validUserPassword.equals(password);
    }  

    /**
     * @return the userr
     */
    public static UserInfo getUserr() {
        return userr;
    }

    /**
     * @param Userr the userr to set
     */
    public static void setUserr(UserInfo Userr) {
        userr = Userr;
    }
}
