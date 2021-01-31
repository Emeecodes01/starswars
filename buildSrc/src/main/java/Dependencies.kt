import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

const val kotlinVersion = "1.4.0"
const val gradle = "4.1.2"

object Config {
    object Versions {
        const val compileSdkVer = 29
        const val buildToolsVer = "30.0.0"
        const val minSdk = 21
        const val targetSdk = 29
        const val versionCode = 1
        const val versionName = "v1.0.0"
    }

    object Android {
        const val applicationId = "com.example.starwarssearch"
        const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    object ClassPaths {
        const val androidGradlePlugin = "com.android.tools.build:gradle:$gradle"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Dependencies.Navigation.Version.navVersion}"
        const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Dependencies.DependencyInjection.Version.daggerHiltVersion}"
        const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Dependencies.Lint.Version.spotless}"
    }

}


object Dependencies {

    object AndroidX {
        object Versions {
            const val coreKtx = "1.3.2"
            const val appCompact = "1.2.0"
            const val activityExt = "1.2.0-beta01"
            const val fragmentExt = "1.3.0-beta01"
        }

        const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}"
        const val activityExtLib = "androidx.activity:activity-ktx:${Versions.activityExt}"
        const val fragmentExt = "androidx.fragment:fragment-ktx:${Versions.fragmentExt}"
    }


    object UILibs {
        object Versions {
            const val materialDesignComps = "1.2.1"
            const val constraintLayout = "2.0.4"
            const val rvAnimator = "4.0.1"
            const val loadingDialog = "1.2.1"
            const val circularImage = "3.1.0"
            const val flowBinding = "1.0.0"
        }

        const val materialDesign = "com.google.android.material:material:${Versions.materialDesignComps}"
        const val constraintLayoutDep = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val recyclerViewAnimator = "jp.wasabeef:recyclerview-animators:${Versions.rvAnimator}"
        const val loadingDep = "cc.cloudist.acplibrary:library:${Versions.loadingDialog}"
        const val circularImageView = "de.hdodenhof:circleimageview:${Versions.circularImage}"
        const val appCompactFlowBinding = "io.github.reactivecircus.flowbinding:flowbinding-appcompat:${Versions.flowBinding}"
    }


    object Persistence {
        object Versions {
            const val roomVersion = "2.2.5"
            const val preferenceDataStore = "1.0.0-alpha06"
        }

        const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomExt = "androidx.room:room-ktx:${Versions.roomVersion}"
        const val preferenceDataStore = "androidx.datastore:datastore-preferences:${Versions.preferenceDataStore}"
    }


    object DependencyInjection {
        object Version {
            const val daggerHiltVersion = "2.31.2-alpha"
            const val hiltArchComp = "1.0.0-alpha03"
        }

        const val daggerHilt = "com.google.dagger:hilt-android:${Version.daggerHiltVersion}"
        const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Version.daggerHiltVersion}"
        const val hiltViewModelCompiler = "androidx.hilt:hilt-compiler:${Version.hiltArchComp}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltArchComp}"
    }


    object Async {
        object Version {
            const val coroutines = "1.4.0"
        }
        const val ktCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val ktCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    }

    object Architecture {
        object Version {
            const val viewModelVersion = "2.2.0"
        }

        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Version.viewModelVersion}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.viewModelVersion}"
    }


    object Navigation {
        object Version {
            const val navVersion = "2.3.1"
        }

        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navVersion}"
        const val navUI = "androidx.navigation:navigation-ui-ktx:${Version.navVersion}"
        const val navFeatureModule = "androidx.navigation:navigation-dynamic-features-fragment:${Version.navVersion}"
    }

    object Api {
        object Version {
            const val retrofit = "2.9.0"
            const val okhttpInterceptor = "4.9.0"
            const val gson = "2.8.6"
            const val gsonConverter = "2.9.0"
        }

        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpInterceptor}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.gsonConverter}"
    }

    object Lint {
        object Version {
            const val spotless = "3.27.0"
        }
    }

    object Image {
        object Version {
            const val glide = "4.11.0"
        }
        const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    }

}


object TestDependencies {

    object Versions {
        const val mockk = "1.9.3"
        const val konveyor = "1.0.3"
        const val coroutineTest = "1.4.2"
        const val junitVersion = "4.13.1"
        const val androidJunit = "1.1.2"
        const val espressoCore = "3.3.0"
        const val annotation = "1.1.0"
        const val truth = "1.0.1"
        const val rule = "1.1.0"
        const val lifecycle = "2.1.0"
        const val coreTestingExt = "1.3.0-rc01"
        const val hiltTesting = "2.28-alpha"
    }

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val konveyor = "com.github.vacxe:konveyor:${Versions.konveyor}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoContri = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val rules = "androidx.test:rules:${Versions.rule}"
    const val runner = "androidx.test:runner:${Versions.rule}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    const val coreTestingExt = "androidx.test:core-ktx:${Versions.coreTestingExt}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltTesting}"
    const val hiltTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltTesting}"
    const val androidxTestCore = "androidx.test:core:${Versions.rule}"
}


fun DependencyHandler.domain() {
    add("implementation", Dependencies.AndroidX.kotlinStdlib)
    add("implementation", Dependencies.Async.ktCoroutines)
    add("implementation", Dependencies.Async.ktCoroutinesAndroid)
    dagger()
    unitTestDeps()
}


fun DependencyHandler.remote() {
    add("implementation", Dependencies.AndroidX.kotlinStdlib)
    add("implementation", project(":domain"))
    add("implementation", project(":core"))
    network()
    coroutines()
    dagger()
    unitTestDeps()
}


fun DependencyHandler.core() {
    add("implementation", Dependencies.AndroidX.kotlinStdlib)
    add("implementation", Dependencies.UILibs.materialDesign)
    add("implementation", project(":domain"))
    navigationComponent()
    unitTestDeps()
    androidX()
}

fun DependencyHandler.local() {
    add("implementation", Dependencies.AndroidX.kotlinStdlib)
    add("api", Dependencies.Persistence.room)
    add("api", Dependencies.Persistence.roomExt)
    add("implementation", Dependencies.Api.gson)
    add("kapt", Dependencies.Persistence.roomCompiler)
    add("implementation", project(":domain"))
    add("implementation", Dependencies.Persistence.preferenceDataStore)
    dagger()
    unitTestDeps()
    androidX()
    androidUITestDeps()
}


fun DependencyHandler.presentation() {
    add("implementation", Dependencies.AndroidX.kotlinStdlib)
    add("implementation", Dependencies.UILibs.materialDesign)
    add("implementation", Dependencies.UILibs.constraintLayoutDep)
    add("implementation", Dependencies.UILibs.recyclerViewAnimator)
    add("implementation", Dependencies.DependencyInjection.hiltViewModel)
    add("kapt", Dependencies.DependencyInjection.hiltViewModelCompiler)
    add("implementation", Dependencies.UILibs.loadingDep)
    add("implementation", Dependencies.UILibs.circularImageView)
    add("implementation", Dependencies.UILibs.appCompactFlowBinding)
    add("implementation", project(":remote"))
    add("implementation", project(":local"))
    add("implementation", project(":domain"))
    add("implementation", project(":core"))
    archComponent()
    dagger()
    glide()
    unitTestDeps()
    androidUITestDeps()
    androidX()
    navigationComponent()
}


fun DependencyHandler.archComponent() {
    add("implementation", Dependencies.Architecture.viewModel)
    add("implementation", Dependencies.Architecture.viewModelKtx)
}

fun DependencyHandler.coroutines() {
    add("implementation", Dependencies.Async.ktCoroutines)
    add("implementation", Dependencies.Async.ktCoroutinesAndroid)
}


fun DependencyHandler.navigationComponent() {
    add("implementation", Dependencies.Navigation.navFragment)
    add("implementation", Dependencies.Navigation.navUI)
    add("implementation", Dependencies.Navigation.navFeatureModule)
}

fun DependencyHandler.unitTestDeps() {
    add("testImplementation", TestDependencies.junit)
    add("testImplementation", TestDependencies.konveyor)
    add("testImplementation", TestDependencies.coroutineTest)
    add("testImplementation", TestDependencies.truth)
    add("testImplementation", TestDependencies.mockk)
    add("testImplementation", TestDependencies.rules)
    add("testImplementation", TestDependencies.coreTesting)
    add("testImplementation", TestDependencies.coreTestingExt)
    add("testImplementation", TestDependencies.runner)
}


fun DependencyHandler.androidUITestDeps() {
    add("androidTestImplementation", TestDependencies.androidJunit)
    add("androidTestImplementation", TestDependencies.espresso)
    add("androidTestImplementation", TestDependencies.annotation)
    add("androidTestImplementation", TestDependencies.truth)
    add("androidTestImplementation", TestDependencies.konveyor)
    add("androidTestImplementation", TestDependencies.rules)
    add("androidTestImplementation", TestDependencies.espressoContri)
    add("androidTestImplementation", TestDependencies.androidxTestCore)
    add("androidTestImplementation", TestDependencies.hiltTesting)
    add("kaptAndroidTest", TestDependencies.hiltTestCompiler)
}


fun DependencyHandler.androidX(){
    add("implementation", Dependencies.AndroidX.appCompact)
    add("implementation", Dependencies.AndroidX.core)
    add("implementation", Dependencies.AndroidX.activityExtLib)
}

fun DependencyHandler.network() {
    add("api", Dependencies.Api.retrofit)
    add("api", Dependencies.Api.okhttpInterceptor)
    add("api", Dependencies.Api.gson)
    add("api", Dependencies.Api.gsonConverter)
}

fun DependencyHandler.dagger() {
    add("api", Dependencies.DependencyInjection.daggerHilt)
    add("kapt", Dependencies.DependencyInjection.daggerCompiler)
}

fun DependencyHandler.glide() {
    add("api", Dependencies.Image.glide)
}