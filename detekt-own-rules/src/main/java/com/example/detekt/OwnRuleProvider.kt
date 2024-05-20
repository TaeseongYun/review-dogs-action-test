package com.example.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class OwnRuleProvider : RuleSetProvider {
  override val ruleSetId: String = "own-rule"
  override fun instance(config: Config): RuleSet = RuleSet(ruleSetId, listOf(ServiceRule(config), ContextOrder(config)))
}
