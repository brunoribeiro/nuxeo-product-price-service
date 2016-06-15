package pt.inevo.nuxeo.product;

import java.util.List;

import org.nuxeo.ecm.collections.api.CollectionConstants;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.api.security.ACE;
import org.nuxeo.ecm.core.api.security.ACL;
import org.nuxeo.ecm.core.api.security.ACP;
import org.nuxeo.ecm.core.api.security.impl.ACLImpl;
import org.nuxeo.ecm.core.api.security.impl.ACPImpl;
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
        
        PathRef folderPathRef = new PathRef("/", "folder");
        DocumentModel folder;
        if (!ctx.getCoreSession().exists(folderPathRef)) {
        	folder = ctx.getCoreSession().createDocumentModel("/", "folder", "Folder");        	
        	ACL acl = new ACLImpl();
        	acl.add(new ACE("Group1", "Read", false));
        	ACP acp = new ACPImpl();
        	acp.addACL(acl);
        	folder = ctx.getCoreSession().createDocument(folder);
        	ctx.getCoreSession().save();
        	folder.setACP(acp, true);   
        	ctx.getCoreSession().save();
        } else {
        	folder = ctx.getCoreSession().getDocument(folderPathRef);
        }
        
        @SuppressWarnings("unchecked")
		List<String> visualIds = (List<String>) doc.getPropertyValue(CollectionConstants.COLLECTION_DOCUMENT_IDS_PROPERTY_NAME);
        for (String visualId: visualIds) {
        	IdRef docIdRef = new IdRef(visualId);
        	DocumentModel visual = ctx.getCoreSession().getDocument(docIdRef);
        	ctx.getCoreSession().move(visual.getRef(), folder.getRef(), null);	
        }
        		
	}

}
