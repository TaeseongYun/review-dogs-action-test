plugins {
  kotlin("jvm")
  id("io.gitlab.arturbosch.detekt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.23.6")
}
