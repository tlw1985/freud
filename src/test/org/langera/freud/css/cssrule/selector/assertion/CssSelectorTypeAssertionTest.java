package org.langera.freud.css.cssrule.selector.assertion;

import org.jdom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.css.cssrule.selector.CssSelectorJdom;
import org.langera.freud.util.parser.JdomTreeAdaptor;

public final class CssSelectorTypeAssertionTest
{
    private CssSelectorTypeAssertion classTypeAssertion;
    private CssSelectorTypeAssertion idTypeAssertion;
    private CssSelector toBeAnalysed;

    @Test
    public void shouldPassAssertion()
    {
        Assert.assertTrue(classTypeAssertion.matches(toBeAnalysed));
    }

    @Test
    public void shouldFailAssertion()
    {
        Assert.assertFalse(idTypeAssertion.matches(toBeAnalysed));
    }

    @Before
    public void setUp() throws Exception
    {
        final Element element = new Element(CssSelector.Type.CLASS.name());
        element.setAttribute(JdomTreeAdaptor.ID_ATTR, "test");
        toBeAnalysed = new CssSelectorJdom(null, element, CssSelector.Combinator.DESCENDANT);
        classTypeAssertion = new CssSelectorTypeAssertion(CssSelector.Type.CLASS);
        idTypeAssertion = new CssSelectorTypeAssertion(CssSelector.Type.ID);
    }
}