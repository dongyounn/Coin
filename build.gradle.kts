import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

plugins {
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.jpa") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"
    id("com.gorylenko.gradle-git-properties") version "1.5.1"
}

group = "com.coin"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

configurations.forEach {
    it.exclude("org.springframework.boot", "spring-boot-starter-tomcat")
}

dependencies {
    implementation("com.querydsl:querydsl-jpa:4.2.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("ojdbc7-12.1.0.2.jar"))))

    implementation("com.auth0:java-jwt:3.15.0")

    implementation("io.springfox", "springfox-swagger2", "2.9.2")
    implementation("io.springfox", "springfox-swagger-ui", "2.9.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.mockito:mockito-core:3.1.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}

tasks.withType<KotlinCompile> {
    doFirst {
        val git = org.ajoberstar.grgit.Grgit.open {
            file(".")
        }
        val infoFile = file("${projectDir}/src/main/resources/build-info.properties")
        val properties = Properties()
        val localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        properties.setProperty("info.version", "1.1.4")
        properties.setProperty("info.git.hash", git.head().getAbbreviatedId(7))
        properties.setProperty("info.git.buildDate", localDateTime)
        properties.store(infoFile.writer(), "Build Information")
    }
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
