package pt.inevo.nuxeo.product.service;

import org.junit.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.model.Property;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

import com.google.inject.Inject;

import pt.inevo.nuxeo.product.ProductAdapter;

@RunWith(FeaturesRunner.class)
@Features({ ProductFeatures.class })
@LocalDeploy("nuxeo-product-service:OSGI-INF/extensions/pt.inevo.nuxeo.product.ProductAdapter.xml")
public class TestDistributorProperty {

    @Inject
    protected CoreSession coreSession;
    
    @Test
    public void testDistributorProperty() {
    	
    	DocumentModel doc = coreSession.createDocumentModel("/", "Test Product", "Product");
    	doc.setProperty("nxproduct","productPrice", "100");
    	doc = coreSession.createDocument(doc);
    	coreSession.save();
    	
    	IdRef docIdRef = new IdRef(doc.getId());
    	doc = coreSession.getDocument(docIdRef);
    	Assert.assertNotNull(doc);
    	
    	Map<String, Serializable> distributor = new HashMap<String, Serializable>();
    	distributor.put("name", "Miguel");
    	distributor.put("sellLocation", "inevo");
    	
    	List<Map<String, Serializable>> distributors = new ArrayList<Map<String, Serializable>>();
    	distributors.add(distributor);
    	
    	doc.setPropertyValue("nxproduct:distributors", (Serializable)distributors);
    	
    	doc = coreSession.saveDocument(doc);
    	coreSession.save();
    	
    	Property distributorsProperty = doc.getProperty("nxproduct:distributors");
    	Property firstDistributor = distributorsProperty.get(0);
    	
    	Serializable firstDistributorName = firstDistributor.getValue("name");
    	
    	ProductAdapter adapter = doc.getAdapter(ProductAdapter.class);
    	Assert.assertNotNull(adapter);
    	
    	firstDistributorName = adapter.getDistributors().get(0).get("name");
    	Assert.assertEquals("Miguel", firstDistributorName);
    }
    
}
