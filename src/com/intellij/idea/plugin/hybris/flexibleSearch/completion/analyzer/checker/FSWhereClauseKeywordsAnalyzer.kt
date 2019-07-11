package com.intellij.idea.plugin.hybris.flexibleSearch.completion.analyzer.checker

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.idea.plugin.hybris.flexibleSearch.completion.analyzer.*
import com.intellij.idea.plugin.hybris.flexibleSearch.completion.provider.FSFieldsCompletionProvider
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchJoinType
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchJoinedTable
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchTableReferenceList
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchWhereClause
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext

/**
 * @author Nosov Aleksandr <nosovae.dev@gmail.com>
 */
object FSWhereClauseKeywordsAnalyzer {
    private val errorIdendifier = "IntellijIdeaRulezzz"

    fun analyzeCompletions(parameters: CompletionParameters, completionResultSet: CompletionResultSet) {
        val context = parameters.originalPosition ?: parameters.position

        if (isColumnReferenceIdentifier(parameters)) FSFieldsCompletionProvider.instance.addCompletionVariants(parameters,  ProcessingContext(), completionResultSet)

        if (context.parent != null && PsiTreeUtil.getParentOfType(parameters.position.parent, FlexibleSearchWhereClause::class.java) != null) {
            val siblingBackward = context.skipWhitespaceSiblingsBackward()

            if (siblingBackward != null && siblingBackward is FlexibleSearchTableReferenceList && isIdentifier(parameters) && !isJoinCondition(parameters)) {
                addSymbolToResult(hashSetOf("ON"), completionResultSet.withPrefixMatcher(""), AllIcons.Nodes.Function)
                if (PsiTreeUtil.findChildrenOfAnyType(context.parent, FlexibleSearchJoinType::class.java, FlexibleSearchJoinedTable::class.java).isEmpty())
                    addToResult(hashSetOf("JOIN", "LEFT JOIN"), completionResultSet.withPrefixMatcher(""), AllIcons.Nodes.Function)
            }
        }
        if (isColumnReferenceIdentifier(parameters) && context.parentIsWhereClause()) {
            completionResultSet.addElement(LookupElementBuilder.create("{}").withPresentableText("{...}").withInsertHandler { ctx, _ ->
                val cursorOffset = ctx.editor.caretModel.offset
                ctx.editor.caretModel.moveToOffset(cursorOffset - 1)
            }.withCaseSensitivity(false))
        }
        if (isJoinCondition(parameters)) {
            addSymbolToResult(hashSetOf("=", ">", "<>", "<", "<=", ">="), completionResultSet.withPrefixMatcher(""), AllIcons.Nodes.Function)
            addToResult(hashSetOf("IS", "NOT"), completionResultSet.withPrefixMatcher(""), AllIcons.Nodes.Function)
        }

    }
}

fun PsiElement.parentIsWhereClause() = PsiTreeUtil.getParentOfType(this, FlexibleSearchWhereClause::class.java) != null