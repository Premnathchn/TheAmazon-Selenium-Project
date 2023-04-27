package support;

/**
 * <h1>Fields to configure customised exception messages
 * <h1>
 * 
 * @author Premnath Ayyadurai
 * @since 20 April 2023
 *
 */
public class CustomisedException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomisedException(String message) {
		super(message);
	}
}
