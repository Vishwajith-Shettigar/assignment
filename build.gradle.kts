// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}
buildscript {


  val kotlinVersion by extra("1.8.0")
  val protobufVersion by extra("0.9.0")

  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:4.1.3")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    classpath("com.google.protobuf:protobuf-gradle-plugin:$protobufVersion")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}
