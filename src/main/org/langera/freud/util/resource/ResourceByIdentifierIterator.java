package org.langera.freud.util.resource;

import org.langera.freud.util.collection.AbstractAnalysedObjectIterator;

import java.io.IOException;

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

class ResourceByIdentifierIterator<T> extends AbstractAnalysedObjectIterator<T>
{
    private final ResourceParser<T> resourceParser;
    private final String[] resourceIdentifiers;
    private final Resource resource;
    private transient int ptr = 0;

    public ResourceByIdentifierIterator(final ResourceParser<T> resourceParser,
                                        final Resource resource,
                                        final String... resourceIdentifiers)
    {
        this(resourceParser, true, resource, resourceIdentifiers);
    }

    public ResourceByIdentifierIterator(final ResourceParser<T> resourceParser,
                                        final boolean alertOnEmptyIterator,
                                        final Resource resource,
                                        final String... resourceIdentfiers)
    {
        super(alertOnEmptyIterator);
        this.resourceParser = resourceParser;
        this.resourceIdentifiers = resourceIdentfiers;
        this.resource = resource;
    }

    @Override
    protected T generateNextItem()
    {
        if (ptr < resourceIdentifiers.length)
        {
            try
            {
                return resourceParser.parseResource(resourceIdentifiers[ptr++], resource);
            }
            catch (IOException e)
            {
                getListener().unexpected(null, new IllegalArgumentException("Failed to retrieve resource [" + resourceIdentifiers[ptr - 1] + "]", e));
                return generateNextItem();
            }
            catch (ResourceParserException e)
            {
                getListener().unexpected(null, new IllegalArgumentException("Failed to parse resource [" + resourceIdentifiers[ptr - 1] + "]", e));
                return generateNextItem();
            }
            catch (Exception e)
            {
                getListener().unexpected(null, new IllegalArgumentException("Unknown failure [" + resourceIdentifiers[ptr - 1] + "]", e));
                return generateNextItem();
            }
        }
        else
        {
            return null;
        }
    }
}
