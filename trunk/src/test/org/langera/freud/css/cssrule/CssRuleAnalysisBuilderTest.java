package org.langera.freud.css.cssrule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AnalysisAssertion;
import org.langera.freud.css.cssrule.selector.CssSelector;

public final class CssRuleAnalysisBuilderTest
{
    private CssRuleAnalysisBuilder builder;

    @Test
    public void testShouldBuildAssertionAsTrueAssertion() throws Exception
    {
        builder.cssRule();

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Test
    public void testShouldBuildAssertionAsAContainsForSelectorType() throws Exception
    {
        builder.containsSelector(CssSelector.Type.TAG);

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("hasCssSelectorType(TAG)", assertion.toString());
    }

    @Before
    public void setUp()
    {
        builder = new CssRuleAnalysisBuilder();
    }
}
