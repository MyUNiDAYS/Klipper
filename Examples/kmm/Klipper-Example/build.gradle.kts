plugins {
    kotlin("multiplatform") version "1.9.0-Beta-145"
    id("com.android.library")
    id("io.github.luca992.multiplatform-swiftpackage") version "2.1.1"
    kotlin("native.cocoapods") version "1.9.0-Beta-145"
    `maven-publish`
}

group = "com.myunidays"
version = "1.0-SNAPSHOT"

val ktor_version = "2.3.0"
val kotlinx_coroutines_version = "1.6.4"

repositories {
    google()
    mavenCentral()
    mavenLocal()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
}

kotlin {
    androidTarget {
        publishAllLibraryVariants()
        publishLibraryVariantsGroupedByFlavor = true
    }
    ios()
    iosSimulatorArm64()
    cocoapods {
        ios.deploymentTarget = "10.0"
        noPodspec()
        framework {
            isStatic = true
            baseName = "library"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.myunidays:klipper:0.0.1")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version")
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktor_version")
                implementation("com.facebook.flipper:flipper:0.190.0")
                implementation("com.facebook.flipper:flipper-network-plugin:0.190.0")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktor_version")
            }
        }
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

android {
    namespace = "com.myunidays.library"
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

multiplatformSwiftPackage {
    packageName("library")
    swiftToolsVersion("5.5")
    targetPlatforms {
        iOS { v("16.4") }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.native.tasks.PodGenTask>().configureEach {
    doLast {
        podfile.get().apply { writeText(readText().replace("use_frameworks!", "use_modular_headers!")) }
    }
}

