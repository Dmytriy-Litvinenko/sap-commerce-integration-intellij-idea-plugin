/*
 * This file is part of "hybris integration" plugin for Intellij IDEA.
 * Copyright (C) 2014-2016 Alexander Bartash <AlexanderBartash@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.intellij.idea.plugin.hybris.flexibleSearch.psi.references;

import com.intellij.idea.plugin.hybris.flexibleSearch.psi.FlexibleSearchTableName;
import com.intellij.idea.plugin.hybris.psi.references.TypeSystemReferenceBase;
import com.intellij.idea.plugin.hybris.type.system.meta.TSMetaClass;
import com.intellij.idea.plugin.hybris.type.system.meta.TSMetaModel;
import com.intellij.idea.plugin.hybris.type.system.model.ItemType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.intellij.util.xml.GenericAttributeValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.Stream;


class TypeSystemItemReference extends TypeSystemReferenceBase<FlexibleSearchTableName> {

    public TypeSystemItemReference(@NotNull final FlexibleSearchTableName owner) {
        super(owner);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(final boolean incompleteCode) {
        final TSMetaModel meta = getTypeSystemMeta();
        final String lookingForName = getElement().getText();
        return Optional.ofNullable(meta.findMetaClassByName(lookingForName))
                       .map(TSMetaClass::retrieveAllDomsStream)
                       .orElse(Stream.empty())
                       .map(ItemTypeResolveResult::new)
                       .toArray(ResolveResult[]::new);
    }

    private static class ItemTypeResolveResult implements ResolveResult {

        private final ItemType myDomItemType;

        public ItemTypeResolveResult(@NotNull final ItemType domItemType) {
            myDomItemType = domItemType;
        }

        @Nullable
        @Override
        public PsiElement getElement() {
            final GenericAttributeValue<String> codeAttr = myDomItemType.getCode();
            return codeAttr == null ? null : codeAttr.getXmlAttributeValue();
        }

        @Override
        public boolean isValidResult() {
            return getElement() != null;
        }
    }

}
