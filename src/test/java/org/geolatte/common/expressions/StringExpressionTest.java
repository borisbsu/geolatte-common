/*
 * This file is part of the GeoLatte project.
 *
 *     GeoLatte is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     GeoLatte is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with GeoLatte.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010 - 2010 and Ownership of code is shared by:
 * Qmino bvba - Romeinsestraat 18 - 3001 Heverlee  (http://www.qmino.com)
 * Geovise bvba - Generaal Eisenhowerlei 9 - 2140 Antwerpen (http://www.geovise.com)
 */

package org.geolatte.common.expressions;

import org.geolatte.common.expressions.stubs.StringExpressionStub;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Tests the {@link StringExpression#compareTo(Object, String)} and {@link StringExpression#switchOn(BasicTypeSwitch)} methods.
 * </p>
 * <p>
 * <i>Creation-Date</i>: 10-Aug-2010<br>
 * <i>Creation-Time</i>:  18:16:22<br>
 * </p>
 *
 * @author Bert Vanhooff
 * @author <a href="http://www.qmino.com">Qmino bvba</a>
 * @since SDK1.5
 */
public class StringExpressionTest {

    private String baseString;
    private String greaterString;
    private String lowerString;

    private StringExpression baseExpression;

    private Object objectToEvaluate;

    private Mockery context = new Mockery();

    @Before
    public void setUp() throws Exception {

        baseString = "def";
        greaterString = "ghi";
        lowerString = "abc";

        baseExpression = new StringExpressionStub(baseString);

        objectToEvaluate = new Object();
    }

    @Test
    public void testCompareTo() throws Exception {

        Assert.assertTrue(baseExpression.compareTo(objectToEvaluate, lowerString) > 0);
        Assert.assertTrue(baseExpression.compareTo(objectToEvaluate, greaterString) < 0);
        Assert.assertEquals(0, baseExpression.compareTo(objectToEvaluate, baseString));
    }

    @Test
    public void testSwitchOn() throws Exception {

        final BasicTypeSwitch basicSwitch = context.mock(BasicTypeSwitch.class);

        context.checking(new Expectations() {
            {exactly(1).of(basicSwitch).caseString(baseExpression);}
        });

        baseExpression.switchOn(basicSwitch);

        context.assertIsSatisfied();
    }
}
