package pt.inevo.nuxeo.product.service;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

import com.google.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ ProductFeatures.class })
@Deploy({"pt.inevo.nuxeo.product.service.nuxeo-product-service","studio.extensions.rdias-SANDBOX"})
@LocalDeploy("nuxeo-product-service-test:OSGI-INF/dummy-notifier-contrib.xml")
public class TestProductService {

    @Inject
    protected ProductService productService;

    @Inject
    protected CoreSession coreSession;
    
    @Test
    public void testService() {
        assertNotNull(productService);
    }
    
    @Test
    public void testComputePrice() {
    	//create the dummy document
    	DocumentModel doc = coreSession.createDocumentModel("/", "Test Product", "Product");
    	doc.setProperty("nxproduct","productPrice", "100");
    	doc = coreSession.createDocument(doc);
    	coreSession.save();
    	
    	IdRef docIdRef = new IdRef(doc.getId());
    	doc = coreSession.getDocument(docIdRef);
    	assertNotNull(doc);
    	
    	long price = productService.computePrice(doc);
    	assertEquals(100, price);
    	
    }
}
