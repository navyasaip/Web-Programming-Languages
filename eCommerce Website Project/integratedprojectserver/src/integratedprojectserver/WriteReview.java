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

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;

import com.google.gson.Gson;
@Path("/store")
public class WriteReview {

	@Path("/review")
	@GET
	@Produces("application/text")
	public Response writeReviewToDB(@Context UriInfo uriinfo) {
		System.out.println("in server start");
		//@QueryParam("cid") String cid,@QueryParam("orderid") String orderid,@QueryParam("productid") String productid,@QueryParam("review") String review
		MultivaluedMap<String, String> queryParams = uriinfo.getQueryParameters();
		if(queryParams!=null){
			System.out.println("1111111111111111111111111111111111");
		}
		else
			System.out.println("222222222222222222222222222");
		System.out.println("in server start2");
	    String review = queryParams.getFirst("review").toString();
	    System.out.println("in server start3");
	    String cid = queryParams.getFirst("cid").toString();
	    System.out.println("in server start4");
	    String productid = queryParams.getFirst("productid").toString();
	    System.out.println("in server start5");
	    String orderid = queryParams.getFirst("orderid").toString();
	    System.out.println("review="+review);
	    System.out.println("pr"+productid+" or"+orderid+" cid"+cid);
		boolean status=getStatus(productid,orderid,cid,review);
		return Response.status(200).entity(Boolean.toString(status)).build();
		//return jsonObject;
	}
	private static boolean getStatus(String prodid,String orderid,String cid,String review)  {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");

		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Reviews r=new Reviews();
		r.setReview(review);
		ReviewPK r1=new ReviewPK();
		r1.setCid(Integer.parseInt(cid));
		r1.setOrderid(Integer.parseInt(orderid));
		r1.setProductid(prodid);
		r.setReviewPK(r1);
		
		//Transaction
		Transaction tx = session.beginTransaction();
		
		Serializable s=session.save(r);
		tx.commit();
		session.close();
		if(s!=null){
			return true;
		}
		else
			return false;
	}
}
