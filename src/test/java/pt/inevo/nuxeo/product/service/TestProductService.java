package pt.inevo.nuxeo.product.service;

import org.junit.Test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

import com.google.inject.Inject;

import pt.inevo.nuxeo.product.ProductAdapter;

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
        Assert.assertNotNull(productService);
    }
    
    @Test
    public void testComputePrice() {
    	
    	DocumentModel product = coreSession.createDocumentModel("/", "Test Product", "Product");
    	ProductAdapter productAdapter = product.getAdapter(ProductAdapter.class);
    	long price = 100l;
    	
    	productAdapter.setProductPrice(price);
    	product = coreSession.createDocument(product);
    	coreSession.save();
    	
    	product = coreSession.getDocument(new IdRef(product.getId()));
    	Assert.assertNotNull(product);
    	long productPrice = productService.computePrice(product);
    	Assert.assertEquals(productPrice, price);
    	
    }
}
