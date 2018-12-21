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

import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * The class {@link JavaSourceCompiler}.
 *
 * @param <T>
 *            the generic type of the compiled class.
 */
public class JavaSourceCompiler<T>
{

	/** The base java file manager. */
	private BaseJavaFileManager baseJavaFileManager;

	/** The diagnostic listener. */
	private DiagnosticCollector<JavaFileObject> diagnosticCollectors;

	/** The runtime compiler class loader. */
	private RuntimeCompilerClassLoader runtimeCompilerClassLoader;

	/** The system java compiler. */
	private JavaCompiler systemJavaCompiler;

	/**
	 * Instantiates a new java source compiler.
	 */
	public JavaSourceCompiler()
	{
		if ((this.systemJavaCompiler = ToolProvider.getSystemJavaCompiler()) == null)
		{
			throw new CompilerRuntimeException("The SystemJavaCompiler was not found.");
		}

		this.runtimeCompilerClassLoader = new RuntimeCompilerClassLoader(
			getClass().getClassLoader());
		this.diagnosticCollectors = new DiagnosticCollector<>();
		final StandardJavaFileManager standardFileManager = this.systemJavaCompiler
			.getStandardFileManager(this.diagnosticCollectors, null, null);
		this.baseJavaFileManager = new BaseJavaFileManager(standardFileManager,
			this.runtimeCompilerClassLoader);
	}

	/**
	 * Tries to compile the given String with the java source with the given package name and the
	 * given class name and returns the generated {@link Class} object.
	 *
	 * @param packageName
	 *            the package name
	 * @param className
	 *            the class name
	 * @param javaSource
	 *            the java source
	 * @return the generated {@link Class} object.
	 */
	@SuppressWarnings("unchecked")
	public synchronized Class<T> compile(final String packageName, final String className,
		final String javaSource)
	{

		final String qualifiedClassName = CompilerExtensions.newQualifiedClassName(packageName,
			className);

		final BaseJavaFileObject javaSourceFileObject = new BaseJavaFileObject(className,
			javaSource);
		final BaseJavaFileObject compiledJavaFileObject = new BaseJavaFileObject(
			qualifiedClassName);
		this.baseJavaFileManager.initialize(javaSourceFileObject, compiledJavaFileObject);

		final CompilationTask task = this.systemJavaCompiler.getTask(null, this.baseJavaFileManager,
			this.diagnosticCollectors, null, null, Arrays.asList(javaSourceFileObject));

		if (!task.call())
		{
			final String message = CompilerExtensions
				.generateCompilationStacktrace(this.diagnosticCollectors);
			throw new CompilerRuntimeException(message);
		}

		final Class<T> newClass = (Class<T>)this.runtimeCompilerClassLoader
			.findClass(qualifiedClassName);

		return newClass;
	}

}
