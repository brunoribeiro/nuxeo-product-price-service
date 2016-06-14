package pt.inevo.nuxeo.product;

import java.util.List;

import org.junit.Assert;
import org.nuxeo.ecm.collections.api.CollectionConstants;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.EventListener;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;

public class NotSoldListener implements EventListener {

	@Override
	public void handleEvent(Event event) {
		
		EventContext ctx = event.getContext();
        if (!(ctx instanceof DocumentEventContext)) {
            return;
        }
        
        DocumentModel doc = ((DocumentEventContext) ctx).getSourceDocument();
        if (doc == null) {
            return;
        }
        
        @SuppressWarnings("unchecked")
		List<String> visualIds = (List<String>) doc.getPropertyValue(CollectionConstants.COLLECTION_DOCUMENT_IDS_PROPERTY_NAME);
        for (String visualId: visualIds) {
        	IdRef docIdRef = new IdRef(visualId);
        	DocumentModel visual = ctx.getCoreSession().getDocument(docIdRef);
        	Assert.assertNotNull(visual);
        }
        
        System.out.println("Handled event in NotSoldListener.");
		
	}

}
