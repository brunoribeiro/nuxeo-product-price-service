package pt.inevo.nuxeo.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PropertyException;
import org.nuxeo.ecm.core.api.model.Property;

/**
 * @author ricardodias
 */
public class ProductAdapter {

    protected final DocumentModel doc;

    public ProductAdapter(DocumentModel doc) {
        this.doc = doc;
    }

    public void save(CoreSession session) throws NuxeoException {
        session.saveDocument(doc);
    }

    public String getId() {
        return doc.getId();
    }

    public Object getAvailableImmediately() throws PropertyException, NuxeoException {
        return (Object)doc.getPropertyValue("nxproduct:availableImmediately");
    }

    public void setAvailableImmediately(Object value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("nxproduct:availableImmediately", (Serializable) value);
    }

    public String[] getCategories() throws PropertyException, NuxeoException {
        return (String[])doc.getPropertyValue("nxproduct:categories");
    }

    public void setCategories(String[] value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("nxproduct:categories", value);
    }

    public String getName() throws PropertyException, NuxeoException {
        return (String)doc.getPropertyValue("nxproduct:name");
    }

    public void setName(String value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("nxproduct:name", value);
    }

    public String getOriginOfFabrication() throws PropertyException, NuxeoException {
        return (String)doc.getPropertyValue("nxproduct:originOfFabrication");
    }

    public void setOriginOfFabrication(String value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("nxproduct:originOfFabrication", value);
    }

    public String getProductId() throws PropertyException, NuxeoException {
        return (String)doc.getPropertyValue("nxproduct:productId");
    }

    public void setProductId(String value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("nxproduct:productId", value);
    }

    public Long getProductPrice() throws PropertyException, NuxeoException {
        return (Long)doc.getPropertyValue("nxproduct:productPrice");
    }

    public void setProductPrice(Long value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("nxproduct:productPrice", value);
    }

    public Long getSize() throws PropertyException, NuxeoException {
        return (Long)doc.getPropertyValue("nxproduct:size");
    }

    public void setSize(Long value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("nxproduct:size", value);
    }
    
    public void setDistributors(List<Map<String, Serializable>> distributors) throws PropertyException, NuxeoException {
    	doc.setPropertyValue("nxproduct:distributors", (Serializable)distributors);
    }
    
    public List<Map<String, Serializable>> getDistributors() {
    	
    	List<Map<String, Serializable>> distributors = new ArrayList<Map<String, Serializable>>();
    	
    	Property distributorsProperty = doc.getProperty("nxproduct:distributors");
    	for (Property distributorProperty: distributorsProperty) {
    		Map<String, Serializable> distributor = new HashMap<>();
    		distributor.put("name", distributorProperty.getValue("name"));
    		distributor.put("sellLocation", distributorProperty.getValue("sellLocation"));
    		
    		distributors.add(distributor);
    	}
    	
    	return distributors;
    }

}
