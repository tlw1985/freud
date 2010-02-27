package org.langera.freud;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public interface AnalysisListener
{
    void passed(Object toBeAnalysed, AnalysisAssertion analysisAssertion);

    void failed(Object toBeAnalysed, AnalysisAssertion analysisAssertion);

    void filtered(Object toBeAnalysed, AnalysisAssertion analysisAssertion);

    void unexpected(Object toBeAnalysed, Exception exception);

}