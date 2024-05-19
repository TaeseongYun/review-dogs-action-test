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

detekt{
  toolVersion = "1.23.6"
  buildUponDefaultConfig = true
  allRules = false
  config = files("$rootDir/detekt-config.yml") // Detekt에서 제공된 yml에서 Rule 설정 On/Off 가능
  ignoreFailures = true // detekt 빌드시 실패 ignore 처리
}
