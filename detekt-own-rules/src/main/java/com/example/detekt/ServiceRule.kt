package com.example.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.config
import io.gitlab.arturbosch.detekt.api.internal.Configuration
import io.gitlab.arturbosch.detekt.rules.identifierName
import org.jetbrains.kotlin.psi.KtClassOrObject

class ServiceRule(config: Config) : Rule(config) {

  @Configuration("naming pattern")
  private val classPattern: Regex by config("[A-Z][a-zA-Z0-9]*Service[A-Z][a-zA-Z0-9]*") { it.toRegex() }

  override val issue: Issue = Issue(
    id = "ServiceInterfaceNaming",
    severity = Severity.Style,
    description = "Interfaces containing 'Service' in their name should be reviewed.",
    debt = Debt.FIVE_MINS
  )

  override fun visitClassOrObject(classOrObject: KtClassOrObject) {
    super.visitClassOrObject(classOrObject)
    if (classOrObject.nameAsSafeName.isSpecial || classOrObject.nameIdentifier?.parent?.javaClass == null) {
      return
    }
    if (!classOrObject.identifierName().removeSurrounding("`").matches(classPattern)) {
      report(
        CodeSmell(
          issue,
          Entity.atName(classOrObject),
          message = "release Service 에도 동일하게 적용을 하셨나요 ? 적용을 하셨다면 이모지를 남겨주세요"
        )
      )
    }
  }
}

