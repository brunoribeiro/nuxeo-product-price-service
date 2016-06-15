package pt.inevo.nuxeo.product.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.runtime.api.Framework;

import pt.inevo.nuxeo.product.service.ProductService;

@Path("product")
public class ProductWebAppRoot {
	
	@GET
 	public Object doGet() {
 		return "Use me by adding \"/price/{workspace}/{product}\" to this page's URL.";
 	}
	
	@GET
 	@Path("price/{workspace}/{product}")
 	public String doCount(@PathParam("workspace") String ws, @PathParam("product") String p) {
 		DocumentModel product = WebEngine.getActiveContext()
 				.getCoreSession()
 				.getDocument(new PathRef("/default-domain/workspaces/" + ws + "/" + p));
 		ProductService service = Framework.getService(ProductService.class);
 		long price = service.computePrice(product);
 		return "Product price: " + price;
 	}
	
}
