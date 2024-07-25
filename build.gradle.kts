// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
  alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

subprojects{  // 하위 모듈에 detekt 설정
  apply(plugin = "io.gitlab.arturbosch.detekt")

  detekt{
    toolVersion = "1.23.6"
    buildUponDefaultConfig = true
    allRules = false
//    config = files("$rootDir/resource/config/detekt/detekt.yml") // Detekt에서 제공된 yml에서 Rule 설정 On/Off 가능
    ignoreFailures = true // detekt 빌드시 실패 ignore 처리
  }
}


//tasks.detekt.configure { // Reviewdog 사용을 위한 Detekt 수행결과 REPORT
//  reports {
//    xml.required.set(true)
//    xml.outputLocation.set(file("build/reports/detekt/detekt.xml"))
//  }
//}
