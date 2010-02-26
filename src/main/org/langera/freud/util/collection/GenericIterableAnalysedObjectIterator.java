package org.langera.freud.util.collection;

import java.util.Iterator;

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

public final class GenericIterableAnalysedObjectIterator<T> extends AbstractAnalysedObjectIterator<T>
{
    private final Iterator<T> iterator;

    public GenericIterableAnalysedObjectIterator(final Iterable<T> iterable, final boolean alertOnEmptyIterator)
    {
        super(alertOnEmptyIterator);
        this.iterator = iterable.iterator();
    }

    @Override
    protected T generateNextItem()
    {
        return (iterator.hasNext()) ? iterator.next() : null;
    }
}
