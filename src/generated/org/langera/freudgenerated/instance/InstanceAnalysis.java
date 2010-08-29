// Freud generated code [InstanceAnalysis] [2010-08-29 18:09:34]
package org.langera.freudgenerated.instance;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.method.*;
import org.langera.freud.instance.*;
import org.langera.freud.aclass.*;
import java.lang.annotation.Annotation;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.dsl.ReadableDsl;
import java.lang.reflect.Method;
import org.langera.freud.dsl.MatchingDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;

public class InstanceAnalysis extends AbstractAnalysis<Object, InstanceAnalysis>
        implements
                    MethodDsl,
                    InstanceDsl,
                    ClassDsl,
                    Analysis
{
    public InstanceAnalysis(AnalysedObjectIterator<Object> objectAnalysedObjectIterator)
    {
        super(objectAnalysedObjectIterator, Object.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
                    if (type == Object.class)
            {
                                    if (nestedType == Class.class)
                    {                                                
                        return ObjectToClassAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == Method.class)
                    {                                                
                        return NestedTypeAnalysisAdapterChain.createChain(
			ObjectToClassAnalysisAdapter.getInstance(),
			ClassToMethodAnalysisAdapter.getInstance());
                    }
                                return null;
            }
                    if (type == Class.class)
            {
                                    if (nestedType == Method.class)
                    {                                                
                        return ClassToMethodAnalysisAdapter.getInstance();
                    }
                                return null;
            }
                return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /// DSL

            public CommonDsl<MethodAnalysisBuilder, Method> method()
        {
            return new MethodAnalysisBuilder().method(
                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> methodAnnotation(final Class<? extends Annotation> annotationType)
        {
            return new MethodAnalysisBuilder().methodAnnotation(
                            annotationType
                                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> throwsException(final Class<? extends Exception> exceptionType)
        {
            return new MethodAnalysisBuilder().throwsException(
                            exceptionType
                                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> publicMethod()
        {
            return new MethodAnalysisBuilder().publicMethod(
                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> staticMethod()
        {
            return new MethodAnalysisBuilder().staticMethod(
                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> declaredMethod()
        {
            return new MethodAnalysisBuilder().declaredMethod(
                        );
        }
        
                public CommonDsl<InstanceAnalysisBuilder, Object> instance()
        {
            return new InstanceAnalysisBuilder().instance(
                        );
        }
        
                public CommonDsl<ClassAnalysisBuilder, Class> aClass()
        {
            return new ClassAnalysisBuilder().aClass(
                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> subTypeOf(Class type)
        {
            return new ClassAnalysisBuilder().subTypeOf(
                            type
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(Class<? extends Annotation> annotationType)
        {
            return new ClassAnalysisBuilder().classAnnotation(
                            annotationType
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(
            Class<? extends Annotation> annotationType, Object annotationValue)
        {
            return new ClassAnalysisBuilder().classAnnotation(
                            annotationType
                                    ,
                                            annotationValue
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredMethod(String methodName, Class... parameterTypes)
        {
            return new ClassAnalysisBuilder().hasDeclaredMethod(
                            methodName
                                    ,
                                            parameterTypes
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredField(Class fieldType)
        {
            return new ClassAnalysisBuilder().hasDeclaredField(
                            fieldType
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> hasPropertyOfType(Class type)
        {
            return new ClassAnalysisBuilder().hasPropertyOfType(
                            type
                                        );
        }
        
    
}