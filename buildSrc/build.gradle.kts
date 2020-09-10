plugins {
	`java-gradle-plugin`
	`kotlin-dsl`
}

gradlePlugin {
	plugins {
		// Use for ":app" module only
		// Applies "com.android.application" only
		register("android-app-module") {
			id = "android-app-module"
			implementationClass = "io.devlight.gradle.plugin.android.AppModulePlugin"
		}

		// Use for any android modules that do not contain any kotlin code
		// e.g. ":core:styling"
		// Applies "com.android.library" only
		register("android-res-module") {
			id = "android-res-module"
			implementationClass = "io.devlight.gradle.plugin.android.ResModulePlugin"
		}

		// Use for any android modules that does not need data binding or android extension
		// e.g. ":data:impl", ":network:impl"
		// Applies "com.android.library", "kotlin-android"
		register("android-lib-module") {
			id = "android-lib-module"
			implementationClass = "io.devlight.gradle.plugin.android.LibModulePlugin"
		}

		// Use for feature modules
		// e.g. ":feature:splash"
		// Applies "com.android.library", "kotlin-android"
		// Enables dataBinding
		register("feature-module") {
			id = "feature-module"
			implementationClass = "io.devlight.gradle.plugin.android.FeatureModulePlugin"
		}

		// Use for pure kotlin modules
		// Applies "kotlin" plugin
		register("kotlin-module") {
			id = "kotlin-module"
			implementationClass = "io.devlight.gradle.plugin.kotlin.KotlinModulePlugin"
		}
	}
}

repositories {
	google()
	mavenCentral()
	jcenter()
}

dependencies {
	compileOnly(gradleApi())

	implementation("com.android.tools.build:gradle:3.6.1")
	implementation(kotlin("gradle-plugin", "1.3.70"))
}

kotlinDslPluginOptions {
	@Suppress("UnstableApiUsage")
	experimentalWarning.set(false)
}


