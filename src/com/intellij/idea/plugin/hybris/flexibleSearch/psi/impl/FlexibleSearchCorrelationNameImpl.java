// This is a generated file. Not intended for manual editing.
package com.intellij.idea.plugin.hybris.flexibleSearch.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchCorrelationName;
import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

import static com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchTypes.IDENTIFIER;

public class FlexibleSearchCorrelationNameImpl extends ASTWrapperPsiElement implements FlexibleSearchCorrelationName {

    public FlexibleSearchCorrelationNameImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull FlexibleSearchVisitor visitor) {
        visitor.visitCorrelationName(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof FlexibleSearchVisitor) {
            accept((FlexibleSearchVisitor) visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        return findNotNullChildByType(IDENTIFIER);
    }

}