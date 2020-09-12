//apply plugin: 'com.android.application'
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'
//apply plugin: 'kotlin-kapt'
//
//// Google Services Gradle plugin (FCM)
//apply plugin: 'com.google.gms.google-services'
//// Apply the Crashlytics Gradle plugin
//apply plugin: 'com.google.firebase.crashlytics'
//
//
//android {
//    compileSdkVersion 29
//    buildToolsVersion "29.0.3"
//
//    buildFeatures {
//        dataBinding true
//    }
//
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//
//    kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_1_8.toString()
//    }
//
//    defaultConfig {
//        applicationId "com.example.cocktailapptest"
//        minSdkVersion 21
//        targetSdkVersion 29
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//
//}
//
//dependencies {
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
//
//    implementation 'androidx.appcompat:appcompat:1.2.0'
//    implementation 'androidx.constraintlayout:constraintlayout:2.0.0'
//    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
//    implementation 'androidx.gridlayout:gridlayout:1.0.0'
//    testImplementation 'junit:junit:4.13'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
//    implementation 'com.google.android.material:material:1.2.0'
//
//    //retrofit
//    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
//    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
//
//    //glide
//    implementation 'com.github.bumptech.glide:glide:4.11.0'
//    kapt 'com.github.bumptech.glide:compiler:4.11.0'
//
//    //recycler
//    implementation 'androidx.recyclerview:recyclerview:1.1.0'
//    implementation 'androidx.recyclerview:recyclerview-selection:1.1.0-rc01'
//
//    //room
//    implementation 'androidx.room:room-common:2.2.5'
//    implementation "androidx.room:room-runtime:2.2.5"
//    kapt "androidx.room:room-compiler:2.2.5"
//    implementation 'androidx.room:room-ktx:2.2.5'
//    androidTestImplementation "androidx.room:room-testing:2.2.5"
//
//
//    implementation 'androidx.core:core-ktx:1.5.0-alpha02'
//    implementation 'androidx.collection:collection-ktx:1.1.0'
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
//    implementation 'androidx.fragment:fragment-ktx:1.3.0-alpha08'
//    implementation 'com.google.android.material:material:1.3.0-alpha02'
//    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
//    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-alpha07'
//    implementation 'androidx.activity:activity-ktx:1.2.0-alpha08'
//    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
//
//    implementation "com.squareup.okhttp3:logging-interceptor:4.5.0-RC1"
//
//    implementation 'jp.wasabeef:glide-transformations:4.0.1'
//
//    // Recommended: Add the Firebase SDK for Google Analytics
//    implementation 'com.google.firebase:firebase-analytics-ktx:17.4.4'
//    // Add the Firebase Crashlytics SDK
//    implementation 'com.google.firebase:firebase-crashlytics:17.1.1'
//    // Add the SDK for Firebase Cloud Messaging
//    implementation 'com.google.firebase:firebase-messaging:20.2.4'
//    // Add the SDK for Firebase Dynamic Links
//    implementation 'com.google.firebase:firebase-dynamic-links-ktx:19.1.0'
//    // Add the SDK for Firebase Remote Config
//    implementation 'com.google.firebase:firebase-config-ktx:19.2.0'
//
//    implementation project(':lang')
//
//}
//repositories {
//    mavenCentral()
//}


import io.devlight.gradle.extension.release

plugins {
    `android-app-module`
    `kotlin-kapt`
}


android {

    defaultConfig.javaCompileOptions.annotationProcessorOptions.arguments =
        mapOf("room.incremental" to "true")


    buildTypes {
//        debug {
////            // api
////            buildConfigField("API_URL_POSTMAN", "https://4a706c72-875e-420e-af29-65a2205bf421.mock.pstmn.io/")
////            buildConfigField("API_URL_PROD", "https://4a706c72-875e-420e-af29-65a2205bf421.mock.pstmn.io/")
//        }

        release {
            // api
            //buildConfigField("API_URL_PROD", "https://4a706c72-875e-420e-af29-65a2205bf421.mock.pstmn.io/")
        }
    }


}

dependencies {
    api(localization)
    api(coreStyling)
    api(Lib.Google.material)


    //implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("com.google.android.material:material:1.2.0")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    // kapt ("com.github.bumptech.glide:compiler:4.11.0")

    //recycler
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0-rc01")

    //room
    implementation("androidx.room:room-common:2.2.5")
    implementation("androidx.room:room-runtime:2.2.5")
    //kapt ("androidx.room:room-compiler:2.2.5")
    kapt(Lib.AndroidX.Room.compiler)
    implementation("androidx.room:room-ktx:2.2.5")
    androidTestImplementation("androidx.room:room-testing:2.2.5")


    implementation("androidx.core:core-ktx:1.5.0-alpha02")
    implementation("androidx.collection:collection-ktx:1.1.0")
    //implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")
    implementation("androidx.fragment:fragment-ktx:1.3.0-alpha08")
    implementation("com.google.android.material:material:1.3.0-alpha02")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-alpha07")
    implementation("androidx.activity:activity-ktx:1.2.0-alpha08")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.5.0-RC1")

    implementation("jp.wasabeef:glide-transformations:4.0.1")

    // Recommended: Add the Firebase SDK for Google Analytics
    implementation("com.google.firebase:firebase-analytics-ktx:17.4.4")
    // Add the Firebase Crashlytics SDK
    implementation("com.google.firebase:firebase-crashlytics:17.1.1")
    // Add the SDK for Firebase Cloud Messaging
    implementation("com.google.firebase:firebase-messaging:20.2.4")
    // Add the SDK for Firebase Dynamic Links
    implementation("com.google.firebase:firebase-dynamic-links-ktx:19.1.0")
    // Add the SDK for Firebase Remote Config
    implementation("com.google.firebase:firebase-config-ktx:19.2.0")




    implementation(coreCommon)
//    implementation(coreKodein)
//    implementation(corePresentation)
//
//    implementation(dataRepository)
//    implementation(dataNetworkImpl)
//    implementation(dataDatabaseImpl)
//    implementation(dataLocalPreference)
//
//    implementation(domain)
//
//    implementation(platformFirebaseFcm)
//
//    implementation(featureSplash)
//    implementation(featureAuth)
//    implementation(featureMain)
//
//    debugImplementation(hyperion)
//
    implementation(Lib.Kodein.androidX)
    implementation(Lib.AndroidX.preferenceKtx)
//
//    debugImplementation(Lib.Network.Gander.persintance)
//    debugImplementation(Lib.Facebook.stetho)
}