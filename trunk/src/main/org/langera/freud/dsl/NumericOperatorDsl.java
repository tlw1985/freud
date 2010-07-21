package org.langera.freud.dsl;

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

public interface NumericOperatorDsl<ThisDsl extends BooleanOperatorDsl>
{
    // TODO NumericOperatorDsl<ThisDsl> min(NumericOperatorDsl<ThisDsl>... values);

    // TODO NumericOperatorDsl<ThisDsl> max(NumericOperatorDsl<ThisDsl>... values);

    NumericOperatorDsl<ThisDsl> add(int value);

    NumericOperatorDsl<ThisDsl> add(NumericOperatorDsl<ThisDsl> value);

    NumericOperatorDsl<ThisDsl> subtract(int value);

    NumericOperatorDsl<ThisDsl> subtract(NumericOperatorDsl<ThisDsl> value);

    NumericOperatorDsl<ThisDsl> multiply(int value);

    NumericOperatorDsl<ThisDsl> multiply(NumericOperatorDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> equalTo(int value);

    BooleanOperatorDsl<ThisDsl> equalTo(NumericOperatorDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> lessThanOrEqualTo(int value);

    BooleanOperatorDsl<ThisDsl> lessThanOrEqualTo(NumericOperatorDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> greaterThanOrEqualTo(int value);

    BooleanOperatorDsl<ThisDsl> greaterThanOrEqualTo(NumericOperatorDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> lessThan(int value);

    BooleanOperatorDsl<ThisDsl> lessThan(NumericOperatorDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> greaterThan(int value);

    BooleanOperatorDsl<ThisDsl> greaterThan(NumericOperatorDsl<ThisDsl> value);
}
