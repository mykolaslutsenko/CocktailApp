// Top-level build file where you can add configuration options common to all sub-projects/modules.

//buildscript {
//    ext.kotlin_version = '1.3.72'
//
//    repositories {
//        google()
//        jcenter()
//        maven { url = "https://dl.bintray.com/kotlin/kotlin-eap" }
//
//    }
//    dependencies {
//        classpath 'com.android.tools.build:gradle:4.2.0-alpha07'
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
//        // Check that you have the Google Services Gradle plugin v4.3.2 or later
//        // (if not, add it).
//        classpath 'com.google.gms:google-services:4.3.3'
//        // Add the Crashlytics Gradle plugin.
//        classpath 'com.google.firebase:firebase-crashlytics-gradle:2.2.1'
//
//    }
//}
//
//allprojects {
//    repositories {
//        google()
//        jcenter()
//
//
//    }
//}
//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}


import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
//        classpath("com.android.tools.build:gradle:4.2.0-alpha07")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")

        classpath("com.google.gms:google-services:4.3.3")

        classpath("com.google.firebase:firebase-crashlytics-gradle:2.2.1")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    project.plugins.withType(Kapt3GradleSubplugin::class.java).whenPluginAdded {
        extensions.getByName<KaptExtension>("kapt").apply {
            mapDiagnosticLocations = true
            useBuildCache = true
        }
    }
}

tasks.register("clean", Delete::class) {
    project.allprojects.forEach {
        delete(it.buildDir)
    }
}