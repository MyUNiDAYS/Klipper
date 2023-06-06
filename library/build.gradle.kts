

val MODULE_PACKAGE_NAME: String by project
val MODULE_NAME: String by project
val MODULE_VERSION_NUMBER: String by project
val PUBLISH_NAME: String by project

group = MODULE_PACKAGE_NAME
version = MODULE_VERSION_NUMBER

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    id("io.github.luca992.multiplatform-swiftpackage") version "2.1.1"
    signing
    `maven-publish`
}

ktlint {
    version.set("0.43.0")
}

detekt {
    config = files("./custom-detekt-config.yml")
    buildUponDefaultConfig = true // preconfigure defaults
    input = files("src/commonMain/kotlin")
    autoCorrect = false

    reports {
        html.enabled = true
        xml.enabled = true
        txt.enabled = true
        sarif.enabled = true
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    jvmTarget = "1.8"
}

kotlin {
    android {
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
            baseName = MODULE_NAME
        }
        pod("FlipperKit") {
            headers = "FlipperDiagnosticsViewController.h FlipperStateUpdateListener.h FlipperClient.h FlipperPlugin.h FlipperConnection.h FlipperResponder.h SKMacros.h FlipperKitCertificateProvider.h SKIOSNetworkPlugin/SKIOSNetworkAdapter.h FlipperKitNetworkPlugin/FlipperKitNetworkPlugin.h FlipperKitUserDefaultsPlugin/FKUserDefaultsPlugin.h"
            extraOpts = listOf("-compiler-option", "-DFB_SONARKIT_ENABLED=1")
        }
        pod("FlipperKit/SKIOSNetworkPlugin")
        pod("FlipperKit/FlipperKitUserDefaultsPlugin")
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:2.3.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.facebook.flipper:flipper:0.193.0")
                implementation("com.facebook.flipper:flipper-network-plugin:0.193.0")
                implementation("com.squareup.okhttp3:okhttp:4.11.0")
            }
        }
//        val androidTest by getting {
//            dependencies {
//                implementation("junit:junit:4.13.2")
//            }
//        }
        val iosMain by getting
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val iosTest by getting
        val iosSimulatorArm64Test by getting
        iosSimulatorArm64Test.dependsOn(iosTest)
    }
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

fun SigningExtension.whenRequired(block: () -> Boolean) {
    setRequired(block)
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.value("javadoc")
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.native.tasks.PodGenTask>().configureEach {
    doLast {
        podfile.get().apply { writeText(readText().replace("use_frameworks!", "use_modular_headers!")) }
//        podfile.get().writeText(
//            "source 'https://cdn.cocoapods.org'\n" +
//                    "target 'ios' do\n" +
//                    "\tplatform :ios, '10.0'\n" +
//                    "\tuse_modular_headers!\n" +
//                    "\tpod 'FlipperKit'\n" +
//                    "\tpod 'FlipperKit/SKIOSNetworkPlugin'\n" +
//                    "end\n" +
//                    "\n" +
//                    "post_install do |installer|\n" +
//                    "  installer.pods_project.targets.each do |target|\n" +
//                    "    target.build_configurations.each do |config|\n" +
//                    "      config.build_settings['EXPANDED_CODE_SIGN_IDENTITY'] = \"\"\n" +
//                    "      config.build_settings['CODE_SIGNING_REQUIRED'] = \"NO\"\n" +
//                    "      config.build_settings['CODE_SIGNING_ALLOWED'] = \"NO\"\n" +
//                    "    end\n" +
//                    "  end\n" +
//                    "end\n"
//        )
    }
}

publishing {
    val OPEN_SOURCE_REPO: String by project
    val PUBLISH_DESCRIPTION: String by project
    val PUBLISH_URL: String by project
    val POM_DEVELOPER_ID: String by project
    val POM_DEVELOPER_NAME: String by project
    val POM_DEVELOPER_EMAIL: String by project
    val PUBLISH_SCM_URL: String by project
    val PUBLISH_SCM_CONNECTION: String by project
    val PUBLISH_SCM_DEVELOPERCONNECTION: String by project

    repositories {
        maven {
            url = uri(OPEN_SOURCE_REPO)

            credentials {
                username = System.getenv("sonatypeUsernameEnv")
                password = System.getenv("sonatypePasswordEnv")
            }
        }
    }

    publications.all {
        this as MavenPublication

        artifact(javadocJar)

        pom {
            name.set(PUBLISH_NAME)
            description.set(PUBLISH_DESCRIPTION)
            url.set(PUBLISH_URL)

            licenses {
                license {
                    name.set("MIT License")
                    url.set("http://opensource.org/licenses/MIT")
                }
            }

            developers {
                developer {
                    id.set(POM_DEVELOPER_ID)
                    name.set(POM_DEVELOPER_NAME)
                    email.set(POM_DEVELOPER_EMAIL)
                }
            }

            scm {
                url.set(PUBLISH_SCM_URL)
                connection.set(PUBLISH_SCM_CONNECTION)
                developerConnection.set(PUBLISH_SCM_DEVELOPERCONNECTION)
            }
        }
    }
}

signing {
    whenRequired { gradle.taskGraph.hasTask("publish") }
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

multiplatformSwiftPackage {
    packageName("Klipper")
    swiftToolsVersion("5.5")
    targetPlatforms {
        iOS { v("16.4") }
    }
}
