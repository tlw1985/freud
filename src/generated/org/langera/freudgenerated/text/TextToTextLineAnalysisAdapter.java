// Freud generated code 2010-08-29 18:09:34
package org.langera.freudgenerated.text;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.text.Text;
import org.langera.freud.text.line.TextLine;

public final class TextToTextLineAnalysisAdapter implements NestedTypeAnalysisAdapter<Text, TextLine>
{
    private static final TextToTextLineAnalysisAdapter SINGLETON = new TextToTextLineAnalysisAdapter();

    private TextToTextLineAnalysisAdapter()
    {
        // singleton
    }

    public static TextToTextLineAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<TextLine> getNestedObjectsToAnalyse(Text toBeAnalysed)
    {
        return toBeAnalysed.getTextLines();
    }
}