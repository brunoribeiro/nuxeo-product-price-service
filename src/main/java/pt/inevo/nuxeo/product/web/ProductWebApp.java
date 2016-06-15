package pt.inevo.nuxeo.product.web;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class ProductWebApp extends Application{
 	@Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> result = new HashSet<Class<?>>();
        result.add(ProductWebAppRoot.class);
        return result;
    }
}
