package pt.inevo.nuxeo.product.extension;

public class DummyNotifier implements Notifier {

	@Override
	public void notify(String message) {
		System.out.println(message);
	}

}
