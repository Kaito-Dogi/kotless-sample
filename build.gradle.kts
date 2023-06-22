import io.kotless.plugin.gradle.dsl.kotless
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.5.31" apply true
  id("io.kotless") version "0.2.0" apply true
}

group = "com.doggy.kotless.sample"
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

tasks.withType<KotlinCompile>() {
  kotlinOptions.jvmTarget = "11"
}

kotless {
  config {
    aws {
      storage {
        bucket = "doggy-kotless-sample"
      }

      profile = "default"
      region = "us-east-1"
    }
  }

  webapp {
    lambda {
      kotless {
        packages = setOf("com.doggy.kotless.sample")
      }

      memoryMb = 1024
      timeoutSec = 120
    }
  }

  extensions {
    terraform {
      allowDestroy = true
    }

    local {
      useAWSEmulation = true
    }
  }
}
