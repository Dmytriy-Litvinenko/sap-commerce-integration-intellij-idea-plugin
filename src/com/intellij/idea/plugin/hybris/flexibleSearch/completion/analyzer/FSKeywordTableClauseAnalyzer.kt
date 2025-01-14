package com.intellij.idea.plugin.hybris.flexibleSearch.completion.analyzer

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.idea.plugin.hybris.flexibleSearch.completion.analyzer.checker.FSFromClauseKeywordsAnalyzer
import com.intellij.idea.plugin.hybris.flexibleSearch.completion.analyzer.checker.FSSelectClauseKeywordsAnalyzer
import com.intellij.idea.plugin.hybris.flexibleSearch.completion.analyzer.checker.FSWhereClauseKeywordsAnalyzer
import com.intellij.idea.plugin.hybris.flexibleSearch.completion.provider.FSFieldsCompletionProvider
import com.intellij.idea.plugin.hybris.flexibleSearch.file.FlexibleSearchFile
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchJoinCondition
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchSelectList
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchTypes
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import javax.swing.Icon


/**
 * @author Nosov Aleksandr <nosovae.dev@gmail.com>
 */
object FSKeywordTableClauseAnalyzer {
    private val topLevelKeywords = hashSetOf("SELECT", "FROM", "WHERE", "ORDER", /* Temporarily place this*/ "LEFT", "JOIN", "ON", "BY", "ASC", "DESC")

    fun analyzeKeyword(parameters: CompletionParameters, completionResultSet: CompletionResultSet) {
        if ((parameters.originalPosition == null && !isTableNameIdentifier(parameters) && !isColumnReferenceIdentifier(parameters)) || isFile(parameters)) {
            addToResult(hashSetOf("SELECT", "FROM", "WHERE"), completionResultSet, AllIcons.Nodes.Static, true)
        }
        if ((isColumnReferenceIdentifier(parameters) && parameters.position.skipWhitespaceSiblingsBackward() != null && parameters.position.skipWhitespaceSiblingsBackward()!!.text != "}") ||
                (isColumnReferenceIdentifier(parameters) && PsiTreeUtil.getParentOfType(parameters.position, FlexibleSearchSelectList::class.java) != null)) {
            FSFieldsCompletionProvider.instance.addCompletionVariants(parameters, ProcessingContext(), completionResultSet)
        }
        if (isFile(parameters)) {
            addToResult(hashSetOf("SELECT", "FROM", "WHERE"), completionResultSet, AllIcons.Nodes.Static, true)
        }
        
        FSSelectClauseKeywordsAnalyzer.analyzeCompletions(parameters, completionResultSet)
        FSWhereClauseKeywordsAnalyzer.analyzeCompletions(parameters, completionResultSet)
        FSFromClauseKeywordsAnalyzer.analyzeCompletions(parameters, completionResultSet)

    }
}

fun isFile(parameters: CompletionParameters) =
        parameters.position.parent != null && parameters.position.parent.parent != null && parameters.position.parent.parent is FlexibleSearchFile

fun isJoinCondition(parameters: CompletionParameters) =
        parameters.position.parent != null && parameters.position.parent.parent != null && parameters.position.parent.parent is FlexibleSearchJoinCondition

fun isTableNameIdentifier(parameters: CompletionParameters) =
        (parameters.position as LeafPsiElement).elementType == FlexibleSearchTypes.TABLE_NAME_IDENTIFIER

fun isColumnReferenceIdentifier(parameters: CompletionParameters) =
        (parameters.position as LeafPsiElement).elementType == FlexibleSearchTypes.COLUMN_REFERENCE_IDENTIFIER

fun isIdentifier(parameters: CompletionParameters) =
        (parameters.position as LeafPsiElement).elementType == FlexibleSearchTypes.IDENTIFIER


fun addToResult(results: Set<String>, completionResultSet: CompletionResultSet, icon: Icon, bold: Boolean = false) {
    results.forEach { completionResultSet.addElement(LookupElementBuilder.create(it).withCaseSensitivity(false).withBoldness(bold).withIcon(icon)) }
}

fun addSymbolToResult(results: Set<String>, completionResultSet: CompletionResultSet, icon: Icon, bold: Boolean = false) {
    results.forEach { completionResultSet.addElement(LookupElementBuilder.create(it).withPresentableText(it).withCaseSensitivity(false).withBoldness(bold).withIcon(icon)) }
}

fun PsiElement.skipWhitespaceSiblingsBackward() = PsiTreeUtil.skipSiblingsBackward(this, PsiWhiteSpace::class.java)
