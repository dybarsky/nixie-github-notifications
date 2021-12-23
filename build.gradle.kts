import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.6.10"
	kotlin("jvm") version kotlinVersion
	kotlin("kapt") version kotlinVersion
	kotlin("plugin.serialization") version kotlinVersion
	id("com.github.ben-manes.versions") version "0.39.0"
	application
}

application {
	mainClass.set("App")
}

repositories {
	jcenter()
	mavenCentral()
	maven("https://kotlin.bintray.com/kotlinx")
	maven("https://oss.sonatype.org/content/groups/public")
}

dependencies {
	implementation(kotlin("stdlib"))

	implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
	implementation("com.pi4j:pi4j-core:1.4") // v2 has breaking changes
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.okhttp3:okhttp:4.9.3")
	implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
	implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}
