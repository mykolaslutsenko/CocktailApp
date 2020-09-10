package io.devlight.gradle.plugin.android

import Config
import com.android.build.gradle.AppExtension
import io.devlight.gradle.extension.addManifestPlaceholders
import io.devlight.gradle.extension.debug
import io.devlight.gradle.extension.release
import io.devlight.gradle.plugin.android.base.BaseAndroidModulePlugin
import org.gradle.api.plugins.PluginContainer

@Suppress("unused") // user in build.gradle.kts.kts.kts.kts
class AppModulePlugin : BaseAndroidModulePlugin<AppExtension>() {

	override fun apply(plugins: PluginContainer): Unit = with(plugins) {
		apply("com.android.application")
		apply("kotlin-android")
		apply("kotlin-android-extensions")
		apply("kotlin-kapt")
		apply("com.google.gms.google-services")
		apply("com.google.firebase.crashlytics")
		//apply plugin: 'com.android.application'
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'
//apply plugin: 'kotlin-kapt'
	}

	override fun configure(extension: AppExtension) = with(extension) {
		defaultConfig.applicationId = Config.applicationId
		dataBinding.isEnabled = true

		buildTypes {
			debug {
				applicationIdSuffix = ".${Config.debugSuffix}"
				versionNameSuffix = "-${Config.debugSuffix}"
				isShrinkResources = false

				addManifestPlaceholders("appName" to ".${Config.appName} ${Config.debugSuffix}")
			}
			release {
				isShrinkResources = Config.enableProguard
				isCrunchPngs = true
				isMinifyEnabled = true
				addManifestPlaceholders("appName" to Config.appName)
			}
		}
	}
}