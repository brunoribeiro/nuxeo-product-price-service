/**
 * 
 */

package pt.inevo.nuxeo.product;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.adapter.DocumentAdapterFactory;

/**
 * @author ricardodias
 */
public class ProductAdapterFactory implements DocumentAdapterFactory {

    @Override
    public Object getAdapter(DocumentModel doc, Class<?> itf) {
        if (doc.hasSchema("nxproduct")){
            return new ProductAdapter(doc);
        }else{
            return null;
        }
    }
}
