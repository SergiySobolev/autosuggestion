import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    application
}

repositories {
    mavenCentral()
}

val ktor_version = "1.3.0"
val junit_version = "5.6.0"
val hamcrest_version = "2.2"

dependencies {
    implementation("io.ktor:ktor-server-netty:$ktor_version")
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

