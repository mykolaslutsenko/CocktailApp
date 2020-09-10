package io.devlight.gradle.plugin.android

import com.android.build.gradle.LibraryExtension
import org.gradle.api.plugins.PluginContainer

@Suppress("unused") // user in build.gradle.kts.kts.kts.kts
class FeatureModulePlugin : LibModulePlugin() {

	override fun apply(plugins: PluginContainer): Unit = with(plugins) {
		super.apply(plugins)
		apply("kotlin-kapt")
	}

	override fun configure(extension: LibraryExtension) {
		super.configure(extension)
		extension.dataBinding.isEnabled = true
	}

}