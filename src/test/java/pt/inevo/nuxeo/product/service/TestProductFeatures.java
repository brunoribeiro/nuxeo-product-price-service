package pt.inevo.nuxeo.product.service;

import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.SimpleFeature;

@Features({ CoreFeature.class })
@Deploy({"org.nuxeo.ecm.platform.collections.core","studio.extensions.rdias-SANDBOX"})
public class TestProductFeatures extends SimpleFeature {

}
