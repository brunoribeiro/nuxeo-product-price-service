package pt.inevo.nuxeo.product.service;

import org.nuxeo.ecm.core.api.DocumentModel;

public interface ProductService {
    /** Add some methods here. **/
	public long computePrice(DocumentModel product);
}
