package com.intellij.idea.plugin.hybris.reference.provider;

import com.intellij.idea.plugin.hybris.reference.HybrisModelItemReference;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nosov Aleksandr
 */
public class HybrisModelItemReferenceProvider extends PsiReferenceProvider {

    @Override
    @NotNull
    public final PsiReference[] getReferencesByElement(
        @NotNull final PsiElement element,
        @NotNull final ProcessingContext context
    ) {

        final HybrisModelItemReference reference
            = new HybrisModelItemReference(element, true);
        final List<PsiReference> results = new ArrayList<>();
        results.add(reference);
        return results.toArray(new PsiReference[results.size()]);
    }
}