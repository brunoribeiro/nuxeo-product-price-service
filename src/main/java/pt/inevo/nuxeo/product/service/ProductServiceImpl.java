package pt.inevo.nuxeo.product.service;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.ComponentInstance;
import org.nuxeo.runtime.model.DefaultComponent;

import pt.inevo.nuxeo.product.ProductAdapter;
import pt.inevo.nuxeo.product.ProductAdapterFactory;
import pt.inevo.nuxeo.product.extension.Notifier;
import pt.inevo.nuxeo.product.extension.NotifierDescriptor;

public class ProductServiceImpl extends DefaultComponent implements ProductService {

	protected final Map<String, NotifierDescriptor> notifierDescriptors = new HashMap<>();
	
	protected final Log log = LogFactory.getLog(ProductServiceImpl.class);
	
    /**
     * Component activated notification.
     * Called when the component is activated. All component dependencies are resolved at that moment.
     * Use this method to initialize the component.
     *
     * @param context the component context.
     */
    @Override
    public void activate(ComponentContext context) {
        super.activate(context);
    }

    /**
     * Component deactivated notification.
     * Called before a component is unregistered.
     * Use this method to do cleanup if any and free any resources held by the component.
     *
     * @param context the component context.
     */
    @Override
    public void deactivate(ComponentContext context) {
        super.deactivate(context);
    }

    /**
     * Application started notification.
     * Called after the application started.
     * You can do here any initialization that requires a working application
     * (all resolved bundles and components are active at that moment)
     *
     * @param context the component context. Use it to get the current bundle context
     * @throws Exception
     */
    @Override
    public void applicationStarted(ComponentContext context) {
        // do nothing by default. You can remove this method if not used.
    }

    @Override
    public void registerContribution(Object contribution, String extensionPoint, ComponentInstance contributor) {
    	if (extensionPoint.equals("notifier")) {
    		NotifierDescriptor desc = (NotifierDescriptor) contribution;
    		notifierDescriptors.put(desc.getName(), desc);	
    	}
    }

    @Override
    public void unregisterContribution(Object contribution, String extensionPoint, ComponentInstance contributor) {
        // Logic to do when unregistering any contribution
    }

	@Override
	public long computePrice(DocumentModel product) {
		
		ProductAdapter productAdapter = product.getAdapter(ProductAdapter.class);
		
		if (productAdapter != null) {
		
			long price = (long) product.getPropertyValue("nxproduct:productPrice");
			
			for (NotifierDescriptor desc: notifierDescriptors.values()) {
				Notifier notifier = desc.getNotifierInstance();
				notifier.notify("New price computed: " + price);
			}
			
			return price;
		}
		return 0;
	}
}
