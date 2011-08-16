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

package org.langera.examples.classfile;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.parser.asm.AsmClassFileParser;

import static org.langera.examples.classfile.ClassFileTestMatchers.methodNamed;

public class AnalyseSpecificMethodsShouldNotHaveBranchLogicTest
{
    private AnalysisListenerStub listener;
    private AsmClassFileParser parser;

    @Test
    public void shouldFailWhenMethodCalledCriticalPathContainsABranch() throws Exception
    {
        FreudAnalyser analyser = ClassFileExamples.specificMethodsShouldNotHaveBranchLogic(
                resource("org/langera/examples/classfile/AnalyseSpecificMethodsShouldNotHaveBranchLogicTest$WithBranch"));
        analyser.analyse(listener);

        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());
        listener.assertPassed(methodNamed("<init>"));
        listener.assertFailed(methodNamed("criticalPath"));
    }

    @Test
    public void shouldPassWhenMethodCalledCriticalPathDoesNotContainABranch() throws Exception
    {
        FreudAnalyser analyser = ClassFileExamples.specificMethodsShouldNotHaveBranchLogic(
                resource("org/langera/examples/classfile/AnalyseSpecificMethodsShouldNotHaveBranchLogicTest$WithoutBranch"));

        analyser.analyse(listener);

        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());
        listener.assertPassed(methodNamed("<init>"));
        listener.assertPassed(methodNamed("criticalPath"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
        parser = new AsmClassFileParser(null);
    }

    private AnalysedObjectIterator<ClassFile> resource(final String className)
    {
        return ResourceIterators.<ClassFile>fileResourceIterator(
                parser, ClassLoader.getSystemClassLoader().
                getResource(className + ".class").toExternalForm().substring("file:".length()));
    }

    ////////////////////////////////////////////////

    private static final class WithBranch
    {
        private enum BranchCosts
        {
            YES, NO, MAYBE, DONT_KNOW, STILL_THINKING_ABOUT_IT
        }

        public boolean criticalPath(BranchCosts branchCosts)
        {
            return (branchCosts == BranchCosts.YES);
        }
    }

    private static final class WithoutBranch
    {
        public boolean criticalPath()
        {
            return true;
        }
    }
}
