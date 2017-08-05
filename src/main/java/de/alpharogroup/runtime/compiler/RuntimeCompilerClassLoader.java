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
