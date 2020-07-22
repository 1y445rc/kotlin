plugins {
    id("org.jetbrains.kotlin.multiplatform").version("<pluginMarkerVersion>")
    id("org.jetbrains.kotlin.native.cocoapods").version("<pluginMarkerVersion>")
}

group = "com.example"
version = "1.0"

repositories {
    mavenLocal()
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlinx.html/") }
}

group = "org.jetbrains.kotlin.sample.native"
version = "1.0"

kotlin {
    // that target considered as default by CocoaPodsIT
    iosX64()
    cocoapods {
        homepage = "https://github.com/JetBrains/kotlin"
        summary = "CocoaPods test library"
        ios.deploymentTarget = "13.5"
    }
}
