/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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

	/** The compiled java file object. */
	private BaseJavaFileObject compiledJavaFileObject;

	/** The java source file object. */
	private BaseJavaFileObject javaSourceFileObject;

	/** The runtime compiler class loader. */
	private RuntimeCompilerClassLoader runtimeCompilerClassLoader;

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
