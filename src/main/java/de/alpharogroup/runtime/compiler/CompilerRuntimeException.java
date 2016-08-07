package de.alpharogroup.runtime.compiler;

/**
 * The class {@link CompilerRuntimeException}.
 */
public class CompilerRuntimeException extends RuntimeException
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link CompilerRuntimeException}.
	 *
	 * @param message
	 *            the message
	 */
	public CompilerRuntimeException(final String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new {@link CompilerRuntimeException}.
	 *
	 * @param cause
	 *            the cause
	 */
	public CompilerRuntimeException(final Exception cause)
	{
		super(cause);
	}

	/**
	 * Instantiates a new {@link CompilerRuntimeException}.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CompilerRuntimeException(final String message, final Exception cause)
	{
		super(message, cause);
	}

}
