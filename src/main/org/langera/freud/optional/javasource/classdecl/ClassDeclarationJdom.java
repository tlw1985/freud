/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.javasource.classdecl;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Element;
import org.langera.freud.optional.javasource.JavaSourceJdom;
import org.langera.freud.optional.javasource.methoddecl.MethodDeclaration;
import org.langera.freud.optional.javasource.methoddecl.MethodDeclarationJdom;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;
import org.langera.freud.util.parser.JdomTreeAdaptor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public final class ClassDeclarationJdom implements ClassDeclaration
{
    private final Element classDeclElement;
    private String name;
    private Map<String, List<MethodDeclaration>> methodDeclarationListByNameMap;
    private Map<String, ClassDeclaration> innerClassDeclarationByNameMap;
    private DeclarationType declarationType;

    public ClassDeclarationJdom(Element classDeclElement, DeclarationType declarationType)
    {
        this.classDeclElement = classDeclElement;
        this.declarationType = declarationType;
    }

    public String[] getDeclaredClassAnnotations()
    {
        // TODO
        return new String[0];
    }

    public DeclarationType getDeclarationType()
    {
        return declarationType;
    }

    public String[] getDeclaredImplementedInterfaceNames()
    {
        // TODO
        return new String[0];
    }

    //  TODO  VarDeclaration[] getFieldDeclarations();

    @SuppressWarnings("unchecked")
    public Map<String, ClassDeclaration> getInnerClassDeclarationByNameMap()
    {
        if (innerClassDeclarationByNameMap == null)
        {
            JXPathContext context = JXPathContext.newContext(classDeclElement);
            for (JavaSourceTokenType tokenType : JavaSourceJdom.POSSIBLE_CLASS_DECLARATION_TYPES)
            {
                final String tokenName = tokenType.getName();
                List<Element> innerClassElementList =
                        context.selectNodes("/" + JavaSourceTokenType.CLASS.getName() + "/" + tokenName);
                innerClassDeclarationByNameMap = new HashMap<String, ClassDeclaration>();
                for (Element innerClassElement : innerClassElementList)
                {
                    ClassDeclaration innerClass = new ClassDeclarationJdom(innerClassElement, DeclarationType.valueOf(tokenName));
                    innerClassDeclarationByNameMap.put(innerClass.getName(), innerClass);
                }
            }
        }
        return innerClassDeclarationByNameMap;
    }

    // TODO   Block getStaticBlock();


    public Map<String, List<MethodDeclaration>> getMethodDeclarationListByNameMap()
    {
        if (methodDeclarationListByNameMap == null)
        {
            JXPathContext context = JXPathContext.newContext(classDeclElement);
            methodDeclarationListByNameMap = new HashMap<String, List<MethodDeclaration>>();
            getMethodDeclarationListByNameMap(context, JavaSourceTokenType.FUNCTION_METHOD_DECL);
            getMethodDeclarationListByNameMap(context, JavaSourceTokenType.VOID_METHOD_DECL);
        }
        return methodDeclarationListByNameMap;
    }

    private void getMethodDeclarationListByNameMap(final JXPathContext context, final JavaSourceTokenType methodElementName)
    {
        List<Element> methodDeclElementList =
                context.selectNodes("//" + methodElementName.getName());
        for (Element methodElement : methodDeclElementList)
        {
            MethodDeclaration methodDeclaration = new MethodDeclarationJdom(methodElement, this);
            final String name = methodDeclaration.getName();
            List<MethodDeclaration> methodDeclarationList = methodDeclarationListByNameMap.get(name);
            if (methodDeclarationList == null)
            {
                methodDeclarationList = new LinkedList<MethodDeclaration>();
                methodDeclarationListByNameMap.put(name, methodDeclarationList);
            }
            methodDeclarationList.add(methodDeclaration);

        }
    }

    public long getModifierMask()
    {
        // TODO
        return 0;
    }

    public String getName()
    {
        if (name == null)
        {
            name = classDeclElement.getAttribute(JdomTreeAdaptor.ID_ATTR).getValue();
        }
        return name;
    }

    public String getSuperClassName()
    {
        // TODO        
        return null;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
