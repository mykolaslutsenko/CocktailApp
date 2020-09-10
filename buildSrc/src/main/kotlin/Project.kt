import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

inline val DependencyHandler.lang: ProjectDependency
	get() = project(":lang")

inline val DependencyHandler.app: ProjectDependency
	get() = project(":app")

inline val DependencyHandler.domain: ProjectDependency
	get() = project(":domain")

inline val DependencyHandler.hyperion: ProjectDependency
	get() = project(":hyperion")

inline val DependencyHandler.localization: ProjectDependency
	get() = project(":localization")

// core
inline val DependencyHandler.coreCommon: ProjectDependency
	get() = project(":core:common")

inline val DependencyHandler.coreKodein: ProjectDependency
	get() = project(":core:kodein")

inline val DependencyHandler.corePresentation: ProjectDependency
	get() = project(":core:presentation")

inline val DependencyHandler.coreStyling: ProjectDependency
	get() = project(":core:styling")

// data
inline val DependencyHandler.dataNetwork: ProjectDependency
	get() = project(":data:network")

inline val DependencyHandler.dataNetworkImpl: ProjectDependency
	get() = project(":data:network:impl")

inline val DependencyHandler.dataDatabase: ProjectDependency
	get() = project(":data:database")

inline val DependencyHandler.dataDatabaseImpl: ProjectDependency
	get() = project(":data:database:impl")

inline val DependencyHandler.dataLocal: ProjectDependency
	get() = project(":data:local")

inline val DependencyHandler.dataLocalPreference: ProjectDependency
	get() = project(":data:local:preference")

inline val DependencyHandler.dataRepository: ProjectDependency
	get() = project(":data:repository")

// platform
inline val DependencyHandler.platformFirebase: ProjectDependency
	get() = project(":platform:firebase")

inline val DependencyHandler.platformFirebaseFcm: ProjectDependency
	get() = project(":platform:firebase:fcm")

// feature
inline val DependencyHandler.featureSplash: ProjectDependency
	get() = project(":feature:splash")

inline val DependencyHandler.featureAuth: ProjectDependency
	get() = project(":feature:auth")

inline val DependencyHandler.featureMain: ProjectDependency
	get() = project(":feature:main")

inline val DependencyHandler.featureOnbording: ProjectDependency
	get() = project(":feature:onbording")
