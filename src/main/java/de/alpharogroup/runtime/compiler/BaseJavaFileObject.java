package de.alpharogroup.runtime.compiler;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.tools.SimpleJavaFileObject;

/**
 * The class {@link BaseJavaFileObject}.
 */
public class BaseJavaFileObject extends SimpleJavaFileObject
{

	/** The java source. */
	private String javaSource;

	/** The byte code. */
	private ByteArrayOutputStream javaByteCode = new ByteArrayOutputStream();

	/**
	 * Instantiates a new {@link BaseJavaFileObject}.
	 *
	 * @param name
	 *            the name
	 */
	public BaseJavaFileObject(final String name)
	{
		super(CompilerExtensions.newURIQuietly(name), Kind.CLASS);
	}

	/**
	 * Instantiates a new {@link BaseJavaFileObject}.
	 *
	 * @param baseName
	 *            the base name
	 * @param javaSource
	 *            the java source
	 */
	public BaseJavaFileObject(final String baseName, final String javaSource)
	{
		super(CompilerExtensions
			.newURIQuietly(CompilerExtensions.getClassNameWithExtension(baseName)), Kind.SOURCE);
		this.javaSource = javaSource;
	}

	/**
	 * Gets the byte array.
	 *
	 * @return the byte array.
	 */
	public byte[] getBytes()
	{
		return this.javaByteCode.toByteArray();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCharContent(final boolean ignoreEncodingErrors)
	{
		return this.javaSource;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream openOutputStream()
	{
		return this.javaByteCode;
	}

}
