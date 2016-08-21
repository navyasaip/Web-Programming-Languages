package integratedprojectserver;

import java.sql.Timestamp;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Path("/newuserservice")
public class newuserservice {
	@Path("/register")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response newregister(MultivaluedMap m)
	{
		java.util.Date date= new java.util.Date();
		Timestamp lastlogin=new Timestamp(date.getTime());
		
	
		System.out.println("im in newuser registrattion service");
		try{
			Configuration cfg=new Configuration();
		    cfg.configure("conn.cfg.xml");
		    @SuppressWarnings("deprecation")
			SessionFactory sf=cfg.buildSessionFactory();
		    Session s=sf.openSession();
		    Transaction tx=s.beginTransaction();
		    customer c=new customer();
	        c.setFirstname(m.get("firstname").toString().replace("[", "").replace("]", ""));
	        c.setLastname(m.get("lastname").toString().replace("[", "").replace("]", ""));
	        c.setEmail(m.get("email").toString().replace("[", "").replace("]", ""));
	        c.setPassword(m.get("password").toString().replace("[", "").replace("]", ""));
	        
	        c.setLastlogin(lastlogin);
	        c.setFailedlogin(0);
	         s.save(c);
	        
	        tx.commit();
		    s.flush();
		    s.close();
                  
		}
		catch(Exception e)
		{
			System.out.println("i caught an exception in hibernate"+e.getMessage());
			return Response.ok().entity("fail").build();
		}
		
		return Response.ok().entity("success").build();
	}

}

