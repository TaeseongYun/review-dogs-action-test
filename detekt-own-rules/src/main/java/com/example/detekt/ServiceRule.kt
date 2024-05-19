package com.example.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtFile

class ServiceRule(config: Config) : Rule(config) {
  override val issue: Issue = Issue(
    id = "ServiceInterfaceNaming",
    severity = Severity.Style,
    description = "Interfaces containing 'Service' in their name should be reviewed.",
    debt = Debt.FIVE_MINS
  )

  override fun visitKtFile(file: KtFile) {
    super.visitKtFile(file)
    if (file.name.contains("Service")) {
      report(
        CodeSmell(
          issue,
          Entity.from(file),
          message = "debug 와 release 둘 다 변경 적용하셨나요 ? 적용하였다면 이모지를 남겨주세요."
        )
      )
    }
  }
}
