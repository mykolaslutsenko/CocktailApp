plugins {
    `feature-module`
}

dependencies {
    api(coreCommon)

    api(coreStyling)

    api(localization)

    // AndroidX
    api(Lib.AndroidX.coreKtx)

    api(Lib.AndroidX.appCompat)
    api(Lib.AndroidX.fragment)
    api(Lib.AndroidX.constraintlayout)
    api(Lib.AndroidX.swiperefreshlayout)
    api(Lib.AndroidX.Lifecycle.extensions)
    api(Lib.AndroidX.Lifecycle.viewModelKtx)

    // Coroutines
    api(Lib.Kotlin.coroutines)

}