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

	/** The system java compiler. */
	private JavaCompiler systemJavaCompiler;

	/** The base java file manager. */
	private BaseJavaFileManager baseJavaFileManager;

	/** The runtime compiler class loader. */
	private RuntimeCompilerClassLoader runtimeCompilerClassLoader;

	/** The diagnostic listener. */
	private DiagnosticCollector<JavaFileObject> diagnosticCollectors;

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
