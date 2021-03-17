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
package io.github.astrapi69.runtime.compiler;

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

}
