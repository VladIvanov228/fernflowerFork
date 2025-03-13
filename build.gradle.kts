// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
plugins {
  java
  pmd
}

tasks.compileJava {
  sourceCompatibility = "17"
  targetCompatibility = "17"
}

sourceSets {
  main {
    java.srcDirs("src")
  }
  test {
    java.srcDirs("test")
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains:annotations:24.0.0")
  testImplementation("junit:junit:4.13.2")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.9.2") 
  testImplementation("org.assertj:assertj-core:3.26.3")
}


tasks.jar {
  archiveFileName = "fernflower.jar"
  manifest {
    attributes["Main-Class"] = "org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler"
  }
}
tasks.test {
  	useJUnitPlatform()
	maxHeapSize = "1024m"
}

pmd{
	toolVersion = "6.55.0"
	isIgnoreFailures = true
	ruleSets = listOf("category/java/errorprone.xml")
}
tasks.jar {
    archiveFileName = "fernflower.jar"
    destinationDirectory = file("$buildDir/libs")  // Явное указание пути [5]
}
