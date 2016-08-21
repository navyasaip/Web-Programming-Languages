package integratedprojectserver;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;

import com.google.gson.Gson;



@Path("/retrieve")
public class ResponseOrder {
	@Path("/orders")
	@GET
	@Produces("application/json")
	public Response searchDBForImages() {
		
		System.out.println("1");
		List<Object> products = getData();
		GenericEntity<List<Object>> list = new GenericEntity<List<Object>>(products) {
        };
		
        return Response.ok(list).build();
		
	}
	private static List<Object> getData()  {
		Logger log = Logger.getLogger(ResponseOrder.class.getName());
		
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Object> orders =  session. createQuery("from orders o, product p where o.productid=p.productid and cid="+1).list();
		
		System.out.println("orders.get(0)"+orders.get(0).toString());
		tx.commit();
		session.close();
		return orders;
	}
}
