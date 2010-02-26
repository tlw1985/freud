// Freud generated code 2010-02-14 19:03:25
package org.langera.freudgenerated.spring;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import java.lang.Object;
import java.lang.Class;
import java.util.Collections;    

public final class ObjectToClassAnalysisAdapter implements NestedTypeAnalysisAdapter<Object, Class>
{
    private static final ObjectToClassAnalysisAdapter SINGLETON = new ObjectToClassAnalysisAdapter();

    private ObjectToClassAnalysisAdapter()
    {
        // singleton
    }

    public static ObjectToClassAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<Class> getNestedObjectsToAnalyse(Object toBeAnalysed)
    {
        return Collections.<Class>singleton(toBeAnalysed.getClass());
    }
}