package de.alpharogroup.runtime.compiler;

import lombok.Setter;

/**
 * The class {@link RuntimeCompilerClassLoader}.
 */
public class RuntimeCompilerClassLoader extends ClassLoader
{

	/** The compiled java file object. */
	@Setter
	private BaseJavaFileObject compiledJavaFileObject;

	/**
	 * Instantiates a new {@link RuntimeCompilerClassLoader}.
	 *
	 * @param parentClassLoader
	 *            the parent class loader
	 */
	public RuntimeCompilerClassLoader(final ClassLoader parentClassLoader)
	{
		super(parentClassLoader);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> findClass(final String qualifiedClassName)
	{
		final byte[] bytes = this.compiledJavaFileObject.getBytes();
		return defineClass(qualifiedClassName, bytes, 0, bytes.length);
	}

}
