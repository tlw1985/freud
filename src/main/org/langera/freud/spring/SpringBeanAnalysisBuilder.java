package org.langera.freud.spring;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.spring.assertion.BeanNameMatchAssertionAdapter;

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

public final class SpringBeanAnalysisBuilder extends AbstractAnalysisBuilder<SpringBeanAnalysisBuilder, SpringBean> implements SpringBeanDsl
{
    public CommonDsl<SpringBeanAnalysisBuilder, SpringBean> springBean()
    {
        setRegexAssertionAdapterClass(BeanNameMatchAssertionAdapter.class);
        return (CommonDsl<SpringBeanAnalysisBuilder, SpringBean>) trueAssertion();
    }

    public Class<SpringBean> getType()
    {
        return SpringBean.class;
    }
}