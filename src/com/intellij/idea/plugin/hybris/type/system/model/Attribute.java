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

// Generated on Sun Jun 05 01:21:13 EEST 2016
// DTD/Schema  :    null

package com.intellij.idea.plugin.hybris.type.system.model;

import com.intellij.idea.plugin.hybris.type.system.file.CompositeConverter;
import com.intellij.idea.plugin.hybris.type.system.file.EnumTypeConverter;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Required;
import com.intellij.util.xml.Stubbed;
import com.intellij.util.xml.StubbedOccurrence;
import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;

/**
 * null:attributeType interface.
 * <pre>
 * <h3>Type null:attributeType documentation</h3>
 * Defines an attribute of a type.
 * </pre>
 */
@Stubbed
@StubbedOccurrence
public interface Attribute extends DomElement {

    /**
     * Returns the value of the redeclare child.
     * <pre>
     * <h3>Attribute null:redeclare documentation</h3>
     * Lets you re-define the attribute definition from an inherited type. In essence, you can use a different type of attribute as well as different modifier combinations than on the supertype. Default is 'false'.
     * </pre>
     *
     * @return the value of the redeclare child.
     */
    @NotNull
    @com.intellij.util.xml.Attribute("redeclare")
    GenericAttributeValue<Boolean> getRedeclare();


    /**
     * Returns the value of the qualifier child.
     * <pre>
     * <h3>Attribute null:qualifier documentation</h3>
     * Qualifier of this attribute. Attribute qualifiers	must be unique across a single type.
     * </pre>
     *
     * @return the value of the qualifier child.
     */
    @NotNull
    @com.intellij.util.xml.Attribute("qualifier")
    @Required
    GenericAttributeValue<String> getQualifier();


    /**
     * Returns the value of the type child.
     * <pre>
     * <h3>Attribute null:type documentation</h3>
     * The type of the attribute, such as 'Product', 'int' or 'java.lang.String'. Primitive java types will be mapped to the corresponding atomic type. For example: 'int' will be mapped to the atomic type	'java.lang.Integer' with implicit default value.
     * </pre>
     *
     * @return the value of the type child.
     */
    @NotNull
    @com.intellij.util.xml.Attribute("type")
    @Required
    @Convert(value = CompositeConverter.AnyClassifier.class, soft = true)
    GenericAttributeValue<String> getType();


    /**
     * Returns the value of the metatype child.
     * <pre>
     * <h3>Attribute null:metatype documentation</h3>
     * Advanced setting. Specifies the metatype for the attributes definition. Must be a type extending AttributeDescriptor. Default is 'AttributeDescriptor'.
     * </pre>
     *
     * @return the value of the metatype child.
     */
    @NotNull
    @com.intellij.util.xml.Attribute("metatype")
    GenericAttributeValue<String> getMetaType();


    /**
     * Returns the value of the autocreate child.
     * <pre>
     * <h3>Attribute null:autocreate documentation</h3>
     * If 'true', the attribute descriptor will be created during initialization. Default is 'true'.
     * </pre>
     *
     * @return the value of the autocreate child.
     */
    @NotNull
    @com.intellij.util.xml.Attribute("autocreate")
    GenericAttributeValue<Boolean> getAutoCreate();


    /**
     * Returns the value of the generate child.
     * <pre>
     * <h3>Attribute null:generate documentation</h3>
     * If 'true', getter and setter methods for this	attribute will be generated during a hybris Suite build. Default is 'true'.
     * </pre>
     *
     * @return the value of the generate child.
     */
    @NotNull
    @com.intellij.util.xml.Attribute("generate")
    GenericAttributeValue<Boolean> getGenerate();


    /**
     * Returns the value of the isSelectionOf child.
     * <pre>
     * <h3>Attribute null:isSelectionOf documentation</h3>
     * References an attribute of the same type. Only values of the referenced attribute can be selected	as values for this attribute. Typical example: the default delivery address of a customer must be one of the addresses set for the customer. Default is 'false'.
     * </pre>
     *
     * @return the value of the isSelectionOf child.
     */
    @NotNull
    @com.intellij.util.xml.Attribute("isSelectionOf")
    GenericAttributeValue<String> getIsSelectionOf();


    /**
     * Returns the value of the defaultvalue child.
     * <pre>
     * <h3>Element null:defaultvalue documentation</h3>
     * Configures a default value for this attribute used if no value is provided. The default value is calculated by initialization and will not be re-calculated by runtime.
     * </pre>
     *
     * @return the value of the defaultvalue child.
     */
    @NotNull
    @SubTag("defaultvalue")
    GenericDomValue<String> getDefaultValue();


    /**
     * Returns the value of the description child.
     * <pre>
     * <h3>Element null:description documentation</h3>
     * Gives a description for this attribute only used for the javadoc of generated attribute methods.
     * </pre>
     *
     * @return the value of the description child.
     */
    @NotNull
    @SubTag("description")
    GenericDomValue<String> getDescription();


    /**
     * Returns the value of the persistence child.
     * <pre>
     * <h3>Element null:persistence documentation</h3>
     * Defines how the values of the attribute will be stored. Possible values: 'cmp' (deprecated), 'jalo' (not persistent, deprecated), 'property' (persistent), 'dynamic' (not persisted).
     * </pre>
     *
     * @return the value of the persistence child.
     */
    @NotNull
    @SubTag("persistence")
    Persistence getPersistence();


    /**
     * Returns the value of the modifiers child.
     * <pre>
     * <h3>Element null:modifiers documentation</h3>
     * Configures advanced settings for this attribute definition.
     * </pre>
     *
     * @return the value of the modifiers child.
     */
    @NotNull
    @SubTag("modifiers")
    Modifiers getModifiers();


    /**
     * Returns the value of the custom-properties child.
     * <pre>
     * <h3>Element null:custom-properties documentation</h3>
     * Allows to configure custom properties for this attribute.
     * </pre>
     *
     * @return the value of the custom-properties child.
     */
    @NotNull
    @SubTag("custom-properties")
    CustomProperties getCustomProperties();


    /**
     * Returns the value of the model child.
     * <pre>
     * <h3>Element null:model documentation</h3>
     * Allows to configure model generation settings for this attribute. Models are used by the hybris ServiceLayer.
     * </pre>
     *
     * @return the value of the model child.
     */
    @NotNull
    @SubTag("model")
    AttributeModel getModel();


}