package org.langera.freud.css.cssrule.declaration.assertion;

import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.util.regex.RegexMatchAnalysisAssertionAdapter;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class CssDeclarationValueMatchAssertionAdapter implements RegexMatchAnalysisAssertionAdapter<CssDeclaration>
{
    private static final CssDeclarationValueMatchAssertionAdapter SINGLETON = new CssDeclarationValueMatchAssertionAdapter();

    public static CssDeclarationValueMatchAssertionAdapter getInstance()
    {
        return SINGLETON;
    }

    private CssDeclarationValueMatchAssertionAdapter()
    {
        // singleton
    }

    public String getStringToMatch(CssDeclaration toBeAnalysed)
    {
        return toBeAnalysed.getValue();
    }

    public String assertionDisplayName()
    {
        return "CssDeclarationValue";
    }
}
