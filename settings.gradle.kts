pluginManagement {

  val kotlinVer: String by settings

  plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    id("org.jetbrains.kotlin.jvm") version kotlinVer apply false
  }
  rootProject.name = "automockker"
}


