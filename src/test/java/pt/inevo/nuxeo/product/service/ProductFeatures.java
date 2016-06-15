package pt.inevo.nuxeo.product.service;

import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.LocalDeploy;
import org.nuxeo.runtime.test.runner.SimpleFeature;

@Features({PlatformFeature.class})
@Deploy({"org.nuxeo.ecm.platform.collections.core","studio.extensions.rdias-SANDBOX"})
@LocalDeploy({"nuxeo-product-service-test:OSGI-INF/test-org.nuxeo.ecm.directory.sql.storage.xml"})
public class ProductFeatures extends SimpleFeature {

}
