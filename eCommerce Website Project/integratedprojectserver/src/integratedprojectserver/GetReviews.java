package integratedprojectserver;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;

import com.google.gson.Gson;
@Path("/display")
public class GetReviews {

	@Path("/reviews")
	@GET
	@Produces("application/text")
	public Response writeReviewToDB(@QueryParam("productid") String productid) {
		System.out.println("in server start");
		System.out.println("product id"+productid);
		
		JSONObject jsonObject = new JSONObject();
		List<Reviews> review = getData(productid);
		// create a new Gson instance
		 Gson gson = new Gson();
		 // convert your list to json
		 String jsonCartList = gson.toJson(review);
		 System.out.println("in server:"+jsonCartList);
		return Response.status(200).entity((jsonCartList)).build();
		//return jsonObject;
	}
	private static List<Reviews> getData(String prodid)  {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");

		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		
		//Transaction
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Reviews> r=session.createQuery("from Reviews where productid ='"+prodid+"'").list();
		
		tx.commit();
		session.close();
		return r;
	}
}
