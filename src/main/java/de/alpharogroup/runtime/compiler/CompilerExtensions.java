package de.alpharogroup.runtime.compiler;

import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

import org.apache.commons.lang3.StringUtils;

/**
 * The class {@link CompilerExtensions}.
 */
public class CompilerExtensions
{

	/**
	 * Factory method for create an {@link URI} with the given uri string.
	 *
	 * @param uriString
	 *            the uri string
	 * @return the created {@link URI}.
	 */
	public static URI newURIQuietly(final String uriString)
	{
		try
		{
			final URI uri = newURI(uriString);
			return uri;
		}
		catch (final URISyntaxException e)
		{
			throw new CompilerRuntimeException(
				"Given String " + uriString + " does not match to an uri", e);
		}
	}

	/**
	 * Factory method for create an {@link URI} with the given uri string.
	 *
	 * @param uriString
	 *            the uri string
	 * @return the created {@link URI}.
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	public static URI newURI(final String uriString) throws URISyntaxException
	{
		final URI uri = new URI(uriString);
		return uri;
	}

	/**
	 * Factory method for create a qualified class name from the given arguments.
	 *
	 * @param packageName
	 *            the package name
	 * @param className
	 *            the class name
	 * @return the created qualified class name
	 */
	public static String newQualifiedClassName(final String packageName, final String className)
	{
		if (StringUtils.isBlank(packageName))
		{
			return className;
		}
		return new StringBuilder().append(packageName).append(SeparatorConstants.DOT)
			.append(className).toString();
	}

	/**
	 * Gets the class name with java file extension.
	 *
	 * @param className
	 *            the class name
	 * @return the class name with extension
	 */
	public static String getClassNameWithExtension(final String className)
	{
		return new StringBuilder().append(className).append(Kind.SOURCE.extension).toString();
	}

	/**
	 * Generate a compilation stacktrace from the given parameter.
	 *
	 * @param diagnosticCollectors
	 *            the diagnostic collectors
	 * @return the generated stacktrace string.
	 */
	public static String generateCompilationStacktrace(
		final DiagnosticCollector<JavaFileObject> diagnosticCollectors)
	{
		final StringBuilder sb = new StringBuilder();
		for (final Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollectors
			.getDiagnostics())
		{
			sb.append(diagnostic.getMessage(null));
			sb.append(SeparatorConstants.SEMI_COLON_WHITE_SPACE);
		}
		return sb.toString();
	}

}
