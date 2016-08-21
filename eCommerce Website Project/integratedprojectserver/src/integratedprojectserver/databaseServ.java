package integratedprojectserver;

import java.io.IOException;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;


@Path("/products")
public class databaseServ {

	@SuppressWarnings({ "unchecked", "unused" })
	private static List<Object> getData(String id) throws IOException  {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");

		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		//Transaction
		Transaction tx = session.beginTransaction();
		//System.out.println("The Product Ids of Product are: ");
		//Display all products
		List<Object> products;
		products =  session. createQuery("from product where productid='"+id+"'").list();
		tx.commit();
		session.close();
		return products;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private static List<Object> getData(String id,String search,String order) throws IOException  {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");
		
		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		//Transaction
		Transaction tx = session.beginTransaction();
		//System.out.println("The Product Ids of Product are: ");
		//Display all products
		List<Object> products;
		String category="0";
		int cat=0;
		if(search.equalsIgnoreCase("pants")){
			category="3";
			cat=1;
		}
		if(search.equalsIgnoreCase("dress"))
			category="2";
		if(search.equalsIgnoreCase("tops"))
			category="1";
		if(order.equals("asc")){
		products =  session. createQuery("from product where category='"+category+"' order by price asc").list();
		}
		else{
			products =  session. createQuery("from product where category='"+category+"' order by price desc").list();
		}
		tx.commit();
		session.close();
		return products;
	}

	@Path("/display")
	@GET
	@Produces("application/text")
	public Response searchDBForImages(@QueryParam("searchstring") String search, @QueryParam("order") String order) throws IOException {
		//System.out.println();
		//System.out.println("search="+search);
		String id="no ID";
		List<Object> products;
		if(order==null){
			products = getData(id,search,"asc");
		}
		else if(order.equals("asc")){
			products = getData(id,search,"asc");
		}
		else{
			products = getData(id,search,"desc");
		}
		// create a new Gson instance
		Gson gson = new Gson();
		// convert your list to json
		String jsonCartList = gson.toJson(products);
		// print your generated json
		//System.out.println("jsonCartList: " + jsonCartList);
		//System.out.println("jsonCartList: " + jsonCartList);
		return Response.status(200).entity(jsonCartList).build();
		//return jsonObject;
	}

	@Path("/{productId}")
	@GET
	@Produces("application/text")
	public Response searchDBforElement(@PathParam("productId") String id) throws IOException {
		System.out.println("In server");
		System.out.println("Product_id=  "+id);
		List<Object> products = getData(id);
		System.out.println("Inside productId path param:");
		System.out.println(products.get(0));
		Gson gson = new Gson();
		String jsonCartList = gson.toJson(products);
		System.out.println("jsonCartList: "+jsonCartList);
		return Response.status(200).entity(jsonCartList).build();
	}
}
