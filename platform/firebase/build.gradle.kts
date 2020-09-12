plugins {
    `android-lib-module`
}

dependencies {

    implementation(Lib.Kotlin.stdlib)
    implementation(Lib.Kotlin.coroutines)
    implementation(Lib.Kodein.androidX)
    api(Lib.Firebase.fcm)
}
