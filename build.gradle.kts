plugins {
  id("org.jetbrains.kotlin.jvm")
  id("maven-publish")
}

group = "alexx.rizz"
version = "0.0.1"

repositories {
  mavenCentral()
}

dependencies {
  val kotlinVer: String by project

  implementation("javax.inject:javax.inject:1")
  implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVer")
  implementation(platform("org.junit:junit-bom:5.10.2"))
  implementation("org.junit.jupiter:junit-jupiter")
  implementation("io.mockk:mockk:1.13.16")

  testImplementation(kotlin("test"))
  testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(17)
}

publishing {
  repositories {
    maven {
      url = uri("https://github.com/alexxRizz/AutoMockker")
    }
  }

  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
    }
  }
}