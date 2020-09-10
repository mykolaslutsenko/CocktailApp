package io.devlight.gradle.plugin.android

import org.gradle.api.plugins.PluginContainer

@Suppress("unused") // user in build.gradle.kts.kts.kts.kts
open class LibModulePlugin : ResModulePlugin() {

	override fun apply(plugins: PluginContainer): Unit = with(plugins) {
		super.apply(plugins)
		apply("kotlin-android")
	}

}