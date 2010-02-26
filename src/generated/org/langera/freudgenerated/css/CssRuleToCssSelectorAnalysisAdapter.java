// Freud generated code 2010-02-14 19:03:24
package org.langera.freudgenerated.css;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.selector.CssSelector;

public final class CssRuleToCssSelectorAnalysisAdapter implements NestedTypeAnalysisAdapter<CssRule, CssSelector>
{
    private static final CssRuleToCssSelectorAnalysisAdapter SINGLETON = new CssRuleToCssSelectorAnalysisAdapter();

    private CssRuleToCssSelectorAnalysisAdapter()
    {
        // singleton
    }

    public static CssRuleToCssSelectorAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<CssSelector> getNestedObjectsToAnalyse(CssRule toBeAnalysed)
    {
        return toBeAnalysed.getCssSelectorList();
    }
}