package io.devlight.gradle.plugin.kotlin

import io.devlight.gradle.extension.addKotlinStdLibDependency
import io.devlight.gradle.extension.setKotlinCompileOption
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KotlinModulePlugin : Plugin<Project> {

	override fun apply(project: Project) = with(project) {
		plugins.apply("kotlin")

		setKotlinCompileOption()
		addKotlinStdLibDependency("api")
	}
}
