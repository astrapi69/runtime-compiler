# Overview

<div align="center">

[![Build Status](https://travis-ci.org/astrapi69/runtime-compiler.svg?branch=master)](https://travis-ci.org/astrapi69/runtime-compiler)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/runtime-compiler.svg?style=flat)](https://github.com/astrapi69/runtime-compiler/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/runtime-compiler/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/runtime-compiler)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/runtime-compiler.svg)](http://www.javadoc.io/doc/io.github.astrapi69/runtime-compiler)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386)

</div>

The runtime-compiler project is a utility library for compiling java code ad-hoc.

If you like this project put a ⭐ and donate

## License

The source code comes under the liberal MIT License, making runtime-compiler great for all types of
applications.

## Maven dependency

Maven dependency is now on sonatype. Check
out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~runtime-compiler~~~)
for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of runtime-compiler:

Than you can add the dependency to your dependencies:

	<properties>
			...
		<!-- RUNTIME-COMPILER version -->
		<runtime-compiler.version>1.3</runtime-compiler.version>
			...
	</properties>
			...
		<dependencies>
			...
			<!-- RUNTIME-COMPILER DEPENDENCY -->
			<dependency>
				<groupId>io.github.astrapi69</groupId>
				<artifactId>runtime-compiler</artifactId>
				<version>${runtime-compiler.version}</version>
			</dependency>
			...
		</dependencies>

## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of runtime-compiler:

define version in file gradle.properties
```

runtimeCompilerVersion=1.3
```

or in build.gradle ext area

```
ext {
			...
    runtimeCompilerVersion = "1.3"
			...
}
```

and than add the dependency to the dependencies area

```
dependencies {
			...
    implementation("io.github.astrapi69:runtime-compiler:$runtimeCompilerVersion")
			...
}
```

## Semantic Versioning

The versions of runtime-compiler are maintained with the Simplified Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning for this project you can visit
this [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Simplified-Semantic-Versioning)
.


## Want to Help and improve it? ###

The source code for runtime-compiler are on GitHub. Please feel free to fork and send pull requests!

Create your own fork
of [astrapi69/runtime-compiler/fork](https://github.com/astrapi69/runtime-compiler/fork)

To share your
changes, [submit a pull request](https://github.com/astrapi69/runtime-compiler/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the runtime-compiler developers with your questions, concerns, comments,
bug reports, or feature requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/runtime-compiler/issues).

## Note

No animals were harmed in the making of this library.

## Support this project

If runtime-compiler is useful to you, a star on [GitHub](https://github.com/astrapi69/runtime-compiler) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Credits

|Travis CI| |:-:| |![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)|
|Many thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration
service for open source projects.|

