import io.kotless.plugin.gradle.dsl.kotless

plugins {
  kotlin("jvm") version "1.8.21" apply true
  id("io.kotless") version "0.2.0" apply true
}

group = "com.doggy"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven(url = uri("https://packages.jetbrains.team/maven/p/ktls/maven"))
}

dependencies {
  implementation("io.kotless", "kotless-lang", "0.2.0")
  implementation("io.kotless", "kotless-lang-aws", "0.2.0")

  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

kotless {
  config {
    aws {
      storage {
        bucket = "kotless.s3.example.com"
      }
      profile = "example"
      region = "eu-west-1"
    }
  }

  webapp {
    lambda {
      kotless {
        packages = setOf("com.example.kotless")
      }
      memoryMb = 1024
      timeoutSec = 120
    }
  }

  extensions {
    terraform {
      allowDestroy = true
    }
  }
}

kotlin {
  jvmToolchain(11)
}
