// This is a generated file. Not intended for manual editing.
package com.intellij.idea.plugin.hybris.flexibleSearch.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FlexibleSearchCharacterLikePredicate extends PsiElement {

    @Nullable
    FlexibleSearchCharacterPattern getCharacterPattern();

    @NotNull
    FlexibleSearchRowValuePredicand getRowValuePredicand();

    @Nullable
    FlexibleSearchValueExpression getValueExpression();

}