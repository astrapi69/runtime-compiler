package de.alpharogroup.runtime.compiler;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

/**
 * The class {@link BaseJavaFileManager}.
 */
public class BaseJavaFileManager extends ForwardingJavaFileManager<JavaFileManager>
{

	/** The runtime compiler class loader. */
	private RuntimeCompilerClassLoader runtimeCompilerClassLoader;

	/** The java source file object. */
	private BaseJavaFileObject javaSourceFileObject;

	/** The compiled java file object. */
	private BaseJavaFileObject compiledJavaFileObject;

	/**
	 * Instantiates a new {@link BaseJavaFileManager}.
	 *
	 * @param fileManager
	 *            the file manager
	 * @param classLoader
	 *            the class loader
	 */
	public BaseJavaFileManager(final JavaFileManager fileManager,
		final RuntimeCompilerClassLoader classLoader)
	{
		super(fileManager);
		this.runtimeCompilerClassLoader = classLoader;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClassLoader getClassLoader(final Location location)
	{
		return this.runtimeCompilerClassLoader;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileObject getFileForInput(final Location location, final String packageName,
		final String relativeName)
	{
		return this.javaSourceFileObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(final Location location, final String qualifiedName,
		final Kind kind, final FileObject outputFile)
	{
		return this.compiledJavaFileObject;
	}

	/**
	 * Initialize this {@link BaseJavaFileManager} object with the given java source file object and
	 * the given compiled java file object.
	 *
	 * @param javaSourceFileObject
	 *            the java source file object
	 * @param compiledJavaFileObject
	 *            the compiled java file object
	 */
	public void initialize(final BaseJavaFileObject javaSourceFileObject,
		final BaseJavaFileObject compiledJavaFileObject)
	{
		this.javaSourceFileObject = javaSourceFileObject;
		this.compiledJavaFileObject = compiledJavaFileObject;
		this.runtimeCompilerClassLoader.setCompiledJavaFileObject(this.compiledJavaFileObject);
	}

}
