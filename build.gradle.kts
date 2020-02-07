import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version = "1.3.0"
val junit_version = "5.6.0"
val hamcrest_version = "2.2"
val zookeeper_version="3.5.6"
val kaml_verison = "0.15.0"
val kotlinx_serialization_version = "0.14.0"

plugins {
    kotlin("jvm") version "1.3.31"
    kotlin("plugin.serialization")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.zookeeper:zookeeper:$zookeeper_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("com.charleskorn.kaml:kaml:$kaml_verison")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinx_serialization_version")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter:$junit_version")
    testImplementation("org.hamcrest:hamcrest:$hamcrest_version")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "org.sbk.web.TypeAheadAppKt"
}

