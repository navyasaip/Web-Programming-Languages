package integratedprojectserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import net.spy.memcached.MemcachedClient;

@Path("/loginservice")

public class loginservice {
@Path("/hi")
@Consumes("application/x-www-form-urlencoded")
@Produces(MediaType.TEXT_PLAIN)
@POST
public Response hello(MultivaluedMap <String,String> formParam)
{
	System.out.println("im in rest service");
	String response = new String();
	String useremail=formParam.get("useremail").toString();
	String userpassword=formParam.get("userpassword").toString();
	useremail = useremail.replace("[", "").replace("]", "");
	userpassword=userpassword.replace("[", "").replace("]", "");
    String results=new String();
    /*
	try{
		Configuration cfg=new Configuration();
	    cfg.configure("conn.cfg.xml");
	    @SuppressWarnings("deprecation")
		SessionFactory sf=cfg.buildSessionFactory();
	    Session s=sf.openSession();
		
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		System.out.println(mcc.flush().isDone());
		
        System.out.println("Connection to server sucessful."+mcc);
        if(mcc.get(useremail)!=null)
        {
        	System.out.println("hit");
            results=mcc.get(useremail).toString();
        }
        else
        {
        	System.out.println("miss");
        	    Transaction tx=s.beginTransaction();
        	    String hql = "SELECT password from customer where email="+"'"+useremail+"'";
        	    System.out.println("the query is:"+hql);
        	    Query query = s.createQuery(hql);
        	    List resultsa = query.list();
        	    results=resultsa.get(0).toString();
        	    tx.commit();        	
        	    Future fo = mcc.set(useremail, 3600,results);
        }
        mcc.shutdown();
        Transaction tx1=s.beginTransaction();
        String hql1 = "SELECT lastlogin from customer where email="+"'"+useremail+"'";
        System.out.println("the query is:"+hql1);
        Query query1 = s.createQuery(hql1);
        List results1 = query1.list();
        tx1.commit();
        
        
        
        if(userpassword.contentEquals(results))
        {
        	System.out.println("the passwords match");
        	java.util.Date date= new java.util.Date();
    		Timestamp lastlogin=new Timestamp(date.getTime());
    		
        	
        	Transaction tx2=s.beginTransaction();
        	String hql2="update customer  set lastlogin = :lastlogin where email ="+"'"+useremail+"'";
        	Query query2=s.createQuery(hql2);
        	query2.setParameter("lastlogin", lastlogin);
        	query2.executeUpdate();
        	tx2.commit();
        	response=results1.get(0).toString();
        }
        else
        {
        	System.out.println("The passwords donot match:"+userpassword+" :"+results);
        	
        	Transaction tx3=s.beginTransaction();
        	String hql3 = "SELECT failedlogin from customer where email="+"'"+useremail+"'";
        	Query query3 = s.createQuery(hql3);
            List results3 = query3.list();
            tx3.commit();    	
        	
        	
        	Transaction tx4=s.beginTransaction();
        	String hql4="update customer  set failedlogin = :failedlogin where email ="+"'"+useremail+"'";
        	Query query4=s.createQuery(hql4);
        	int failedlogin=(int)results3.get(0)+1;
        	query4.setParameter("failedlogin", failedlogin);
        	query4.executeUpdate();
        	tx4.commit();
        	
        	
        	response="false";
        }
         System.out.println("password:"+results);
        s.close();

        
    		
	}
	catch(Exception e)
	{
		System.out.println("i caught exception:"+e.getMessage());
		e.printStackTrace();
		response="false";
	}
	
	return Response.ok().entity(String.valueOf(response)).build();
	*/

	try{
		Configuration cfg=new Configuration();
	    cfg.configure("conn.cfg.xml");
	    @SuppressWarnings("deprecation")
		SessionFactory sf=cfg.buildSessionFactory();
	    Session s=sf.openSession();
	    
	    Transaction tx=s.beginTransaction();
	    String hql = "SELECT password from customer where email="+"'"+useremail+"'";
	    System.out.println("the query is:"+hql);
	    Query query = s.createQuery(hql);
	    List results20 = query.list();
	    tx.commit();
	 
	    //System.out.println("hiiiiiiiiiiiiiiiii");
	    Transaction tx1=s.beginTransaction();
	    String hql1 = "SELECT lastlogin from customer where email="+"'"+useremail+"'";
	    System.out.println("the query is:"+hql);
	    Query query1 = s.createQuery(hql1);
	    List results1 = query1.list();
	    tx1.commit();
	    
	    
	    
	    if(userpassword.contentEquals(results20.get(0).toString()))
	    {
	    	System.out.println("the passwords match");
	    	java.util.Date date= new java.util.Date();
			Timestamp lastlogin=new Timestamp(date.getTime());
			
	    	
	    	Transaction tx2=s.beginTransaction();
	    	String hql2="update customer  set lastlogin = :lastlogin where email ="+"'"+useremail+"'";
	    	Query query2=s.createQuery(hql2);
	    	query2.setParameter("lastlogin", lastlogin);
	    	query2.executeUpdate();
	    	tx2.commit();
	    	response=results1.get(0).toString();
	    	System.out.println("the passwords match1");
	    }
	    else
	    {
	    	System.out.println("The passwords donot match:"+userpassword+" :"+results20.get(0));
	    	
	    	Transaction tx3=s.beginTransaction();
	    	String hql3 = "SELECT failedlogin from customer where email="+"'"+useremail+"'";
	    	Query query3 = s.createQuery(hql3);
	        List results3 = query3.list();
	        tx3.commit();    	
	    	
	    	
	    	Transaction tx4=s.beginTransaction();
	    	String hql4="update customer  set failedlogin = :failedlogin where email ="+"'"+useremail+"'";
	    	Query query4=s.createQuery(hql4);
	    	int failedlogin=(int)results3.get(0)+1;
	    	query4.setParameter("failedlogin", failedlogin);
	    	query4.executeUpdate();
	    	tx4.commit();
	    	
	    	
	    	response="false";
	    }
	     System.out.println("password:"+results20);
	    s.close();
	    System.out.println("the passwords match2");
		}
		catch(Exception e)
		{
			System.out.println("i caught exception:"+e.getMessage());
			response="false";
		}
	    
	   // s.flush();
	    //tx.commit();
	    
		
		
		return Response.ok().entity(String.valueOf(response)).build();

}
}
