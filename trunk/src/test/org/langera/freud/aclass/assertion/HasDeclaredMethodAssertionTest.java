package org.langera.freud.aclass.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class HasDeclaredMethodAssertionTest
{
    private HasDeclaredMethodAssertion hasSetUpMethodAssertion;
    private HasDeclaredMethodAssertion hasDeclaredToStringMethodAssertion;
    private HasDeclaredMethodAssertion hasDeclaredFooMethodAssertion;

    @Test
    public void shouldPass() throws Exception
    {
        Assert.assertTrue(hasSetUpMethodAssertion.analyse(HasDeclaredMethodAssertionTest.class));                
    }

    
    @Test
    public void shouldFailBecauseMethodNotDeclared() throws Exception
    {
        Assert.assertFalse(hasDeclaredToStringMethodAssertion.analyse(HasDeclaredMethodAssertionTest.class));        
    }

    @Test
    public void shouldFailBecauseMethodDoesNotExist() throws Exception
    {
        Assert.assertFalse(hasDeclaredFooMethodAssertion.analyse(HasDeclaredMethodAssertionTest.class));
    }

    @Before
    public void setUp() throws Exception
    {
        hasSetUpMethodAssertion = new HasDeclaredMethodAssertion("setUp");
        hasDeclaredToStringMethodAssertion = new HasDeclaredMethodAssertion("toString");
        hasDeclaredFooMethodAssertion = new HasDeclaredMethodAssertion("foo");
    }
}