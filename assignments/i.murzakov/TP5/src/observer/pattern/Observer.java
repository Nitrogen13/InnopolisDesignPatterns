package observer.pattern;

/**
 * An interface for all Observers
 */
public interface Observer {
	/**
	 * Informs this observer that an observed subject has changed
	 *
	 * @param o
	 *            the observed subject that has changed
	 */
	void update(Observable o);

	/**
	 * Informs this observer that an observed subject has changed
	 * @param args object that has changed from observable
	 */
	void update(Object args);
}