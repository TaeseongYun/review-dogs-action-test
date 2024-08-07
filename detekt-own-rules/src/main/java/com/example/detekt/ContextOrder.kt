package com.example.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtNamedFunction

class ContextOrder(config: Config) : Rule(config) {

  companion object {
    const val ISSUE_DESCRIPTION = "This rule reports the method which doesn't use Context as the first parameter"
    const val CONTEXT = "Context"
    const val CONTEXT_WITH_QUESTION_MARK = "$CONTEXT?"
    const val REPORT_MESSAGE = "Context must be the first parameter"
  }

  override val issue = Issue(javaClass.simpleName, Severity.Minor, ISSUE_DESCRIPTION, Debt.FIVE_MINS)

  //Triggers for every function
  override fun visitNamedFunction(function: KtNamedFunction) {
    super.visitNamedFunction(function)
    val parameters = function.valueParameters
    //If parameter size smaller than 2, no need to check
    if (parameters.size < 2) {
      return
    }
    //Get parameter types like [String,Context,Fragment] etc
    val parameterTypeList = parameters.map { x -> x.children[0].text }
    //If Context is the first parameter, no need to check
    if (isContext(parameterTypeList[0])) {
      return
    }
    //If Context is not the first parameter, report issue
    else if (isContext(parameterTypeList)) {
      report(CodeSmell(issue, Entity.from(function), REPORT_MESSAGE))
    }
  }

  private fun isContext(s: String?): Boolean {
    return (s == CONTEXT || s == CONTEXT_WITH_QUESTION_MARK)
  }

  private fun isContext(s: List<String>): Boolean {
    return (s.contains(CONTEXT) || s.contains(CONTEXT_WITH_QUESTION_MARK))
  }

}
