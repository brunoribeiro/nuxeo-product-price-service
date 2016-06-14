package pt.inevo.nuxeo.product.event;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.collections.core.adapter.Collection;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.event.EventProducer;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

import com.google.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ CoreFeature.class })
@Deploy({"org.nuxeo.ecm.platform.collections.core","studio.extensions.rdias-SANDBOX"})
@LocalDeploy("nuxeo-product-service:OSGI-INF/listeners/product-not-sold-listener.xml")
public class TestNotSoldEvent {

    @Inject
    protected CoreSession coreSession;
    
    @Test
    public void testNotSoldEvent() {
   
    	DocumentModel doc = coreSession.createDocumentModel("/", "Test Product", "Product");
    	doc.setProperty("nxproduct","productPrice", "100");
    	doc = coreSession.createDocument(doc);
    	coreSession.save();
    	
    	IdRef docIdRef = new IdRef(doc.getId());
    	doc = coreSession.getDocument(docIdRef);
    	Assert.assertNotNull(doc);
    	Assert.assertTrue(doc.hasSchema("collection"));
    	
		EventProducer eventProducer;
		try {
			eventProducer = Framework.getService(EventProducer.class);
		} catch (Exception e) {
			return;
		}
		DocumentEventContext ctx = new DocumentEventContext(coreSession, coreSession.getPrincipal(), doc);
		Event event = ctx.newEvent("notSoldEvent");
		eventProducer.fireEvent(event);
    	
    }
	
}
