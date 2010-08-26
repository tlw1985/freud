// Freud generated code 2010-08-25 22:33:47
package org.langera.freudgenerated.spring;

import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.spring.SpringBean;

import java.util.Collections;

public final class SpringBeanToObjectAnalysisAdapter implements NestedTypeAnalysisAdapter<SpringBean, Object>
{
    private static final SpringBeanToObjectAnalysisAdapter SINGLETON = new SpringBeanToObjectAnalysisAdapter();

    private SpringBeanToObjectAnalysisAdapter()
    {
        // singleton
    }

    public static SpringBeanToObjectAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<Object> getNestedObjectsToAnalyse(SpringBean toBeAnalysed)
    {
        return Collections.singleton(toBeAnalysed.getBean());
    }
}