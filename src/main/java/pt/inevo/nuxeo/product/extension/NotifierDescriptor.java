package pt.inevo.nuxeo.product.extension;

import java.io.Serializable;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("notifier")
public class NotifierDescriptor implements Serializable {

	private static final long serialVersionUID = 8733997724708177356L;

	protected Notifier notifier;
	
    @XNode("name")
    protected String name;
    
    @XNode("@class")
    protected Class<?> notifierClass;
    
    public String getName() {
    	return name;
    }
    
    public Notifier getNotifierInstance() {
    	if (notifier == null) {
    		try {
				notifier = (Notifier) notifierClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    	}
    	return notifier;
    }
    
}
