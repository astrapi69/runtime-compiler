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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.tools.SimpleJavaFileObject;

/**
 * The class {@link BaseJavaFileObject}.
 */
public class BaseJavaFileObject extends SimpleJavaFileObject
{

	/** The byte code. */
	private ByteArrayOutputStream javaByteCode = new ByteArrayOutputStream();

	/** The java source. */
	private String javaSource;

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
