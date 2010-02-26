package org.langera.examples.aclass;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.runner.RunWith;
import org.langera.freud.Analysis;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freudgenerated.aclass.ClassAnalysis;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 31-Oct-2008
 * Time: 09:09:32
 */
public final class ClassExamples
{
    private ClassExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static Analysis equalsAlwaysGoesTogetherWithHashCode(final AnalysedObjectIterator<Class> iterator)
    {
        return new ClassAnalysis(iterator)
        {
            {
                assertThat(hasDeclaredMethod("equals", Object.class).and(hasDeclaredMethod("hashCode")).
                        or(no(hasDeclaredMethod("equals", Object.class)).and(no(hasDeclaredMethod("hashCode")))));
            }
        };
    }

    public static Analysis noSubTypeOfObjectHasPropertyOfTypeString(final AnalysedObjectIterator<Class> iterator)
    {
        return new ClassAnalysis(iterator)
        {
            {
                assertThat(no(subTypeOf(Object.class).and(hasPropertyOfType(String.class))));
            }
        };
    }


    public static Analysis testClassWithMockeryMustContainRunWithAnnotation(final AnalysedObjectIterator<Class> iterator)
    {
        return new ClassAnalysis(iterator)
        {
            {
                forEach(aClass().matches(".+Test"));
                assertThat(no(hasDeclaredField(Mockery.class)).
                        or(classAnnotation(RunWith.class, JMock.class)));
            }
        };
    }

}
