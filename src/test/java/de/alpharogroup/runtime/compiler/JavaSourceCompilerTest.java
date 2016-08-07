package de.alpharogroup.runtime.compiler;

import org.testng.annotations.Test;

/**
 * Test class for the class {@link JavaSourceCompiler}.
 *
 */
public class JavaSourceCompilerTest
{

	/**
	 * Test for method {@link JavaSourceCompiler#compile(String, String, String)}
	 *
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(enabled = false)
	public void testCompile() throws InstantiationException, IllegalAccessException
	{
		final JavaSourceCompiler<Runnable> runtimeCompiler = new JavaSourceCompiler<>();
		final String source = "public final class FooRunnable implements Runnable { public void run() { System.out.println(\"Foo bar\"); } } ";
		final Class<Runnable> clazz = runtimeCompiler.compile(null, "FooRunnable", source);
		final Runnable r = clazz.newInstance();
		r.run();
	}

}
