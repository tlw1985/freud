package org.langera.freud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnalysisUtilsTest
{

    private AnalysisAssertion<AnalysisUtilsTest> dummyTrueAssertion;
    private AnalysisAssertion<AnalysisUtilsTest> dummyFalseAssertion;
    private AnalysisCalculation<AnalysisUtilsTest> dummyThreeCalculation;
    private AnalysisCalculation<AnalysisUtilsTest> dummyFourCalculation;

    @Test
    public void testShouldCreateNegatedAssertion() throws Exception
    {

        AnalysisAssertion<AnalysisUtilsTest> negatedTrue =
                AnalysisUtils.negatedAssertion(dummyTrueAssertion);
        AnalysisAssertion<AnalysisUtilsTest> negatedFalse =
                AnalysisUtils.negatedAssertion(dummyFalseAssertion);

        Assert.assertTrue(negatedFalse.analyse(null));
        Assert.assertFalse(negatedTrue.analyse(null));
    }


    @Test
    public void testShouldCreateAndAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.andOperatorAssertion(
                dummyTrueAssertion, dummyTrueAssertion).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.andOperatorAssertion(
                dummyFalseAssertion, dummyTrueAssertion).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.andOperatorAssertion(
                dummyTrueAssertion, dummyFalseAssertion).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.andOperatorAssertion(
                dummyFalseAssertion, dummyFalseAssertion).
                analyse(null));
    }

    @Test
    public void testNegateAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.negatedAssertion(dummyTrueAssertion);
        final AnalysisAssertion assertion2 = AnalysisUtils.negatedAssertion(dummyTrueAssertion);
        final AnalysisAssertion assertion3 = AnalysisUtils.negatedAssertion(dummyFalseAssertion);
        Assert.assertTrue(assertion1.equals(assertion2));
        Assert.assertTrue(assertion2.equals(assertion1));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertTrue(assertion1.hashCode() == assertion2.hashCode());
    }


    @Test
    public void testAndAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.andOperatorAssertion(
                dummyTrueAssertion, dummyFalseAssertion);
        final AnalysisAssertion assertion2 = AnalysisUtils.andOperatorAssertion(
                dummyFalseAssertion, dummyTrueAssertion);
        final AnalysisAssertion assertion3 = AnalysisUtils.andOperatorAssertion(
                dummyTrueAssertion, dummyTrueAssertion);
        Assert.assertTrue(assertion1.equals(assertion2));
        Assert.assertTrue(assertion2.equals(assertion1));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertTrue(assertion1.hashCode() == assertion2.hashCode());
    }

    @Test
    public void testShouldCreateOrAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.orOperatorAssertion(
                dummyTrueAssertion, dummyTrueAssertion).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.orOperatorAssertion(
                dummyFalseAssertion, dummyTrueAssertion).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.orOperatorAssertion(
                dummyTrueAssertion, dummyFalseAssertion).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.orOperatorAssertion(
                dummyFalseAssertion, dummyFalseAssertion).
                analyse(null));
    }

    @Test
    public void testOrAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.orOperatorAssertion(
                dummyTrueAssertion, dummyFalseAssertion);
        final AnalysisAssertion assertion2 = AnalysisUtils.orOperatorAssertion(
                dummyFalseAssertion, dummyTrueAssertion);
        final AnalysisAssertion assertion3 = AnalysisUtils.orOperatorAssertion(
                dummyTrueAssertion, dummyTrueAssertion);
        Assert.assertTrue(assertion1.equals(assertion2));
        Assert.assertTrue(assertion2.equals(assertion1));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertTrue(assertion1.hashCode() == assertion2.hashCode());
    }

    @Test
    public void testShouldCreateTrueAssertion() throws Exception
    {
        Assert.assertTrue(AnalysisUtils.trueAssertion().analyse(null));
    }

    @Test
    public void testTrueAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.trueAssertion();
        final AnalysisAssertion assertion2 = AnalysisUtils.trueAssertion();
        Assert.assertTrue(assertion1.equals(assertion2));
        Assert.assertTrue(assertion2.equals(assertion1));
        Assert.assertTrue(assertion1.hashCode() == assertion2.hashCode());
    }

    @Test
    public void testShouldCreateEqualAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.equalOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.equalOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.equalOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.equalOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testEqualAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.equalOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion2 = AnalysisUtils.equalOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion3 = AnalysisUtils.equalOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertTrue(assertion2.equals(assertion3));
        Assert.assertTrue(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
        Assert.assertTrue(assertion3.hashCode() == assertion2.hashCode());
    }

    @Test
    public void testShouldCreateGreaterThanOrEqualAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testGreaterThanOrEqualAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion2 = AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion3 = AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertFalse(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
    }


    @Test
    public void testShouldCreateGreaterThanAssertion() throws Exception
    {

        Assert.assertFalse(AnalysisUtils.greaterThanOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.greaterThanOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.greaterThanOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.greaterThanOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testGreaterThanAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.greaterThanOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion2 = AnalysisUtils.greaterThanOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion3 = AnalysisUtils.greaterThanOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertFalse(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
    }


    @Test
    public void testShouldCreateLessThanOrEqualAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testLessThanOrEqualAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion2 = AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion3 = AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertFalse(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
    }


    @Test
    public void testShouldCreateLessThanAssertion() throws Exception
    {

        Assert.assertFalse(AnalysisUtils.lessThanOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(AnalysisUtils.lessThanOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.lessThanOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertFalse(AnalysisUtils.lessThanOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testLessThanAssertionEquality() throws Exception
    {
        final AnalysisAssertion assertion1 = AnalysisUtils.lessThanOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion2 = AnalysisUtils.lessThanOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisAssertion assertion3 = AnalysisUtils.lessThanOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertFalse(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
    }


    @Test
    public void testShouldCreateMultiplyCalculation() throws Exception
    {

        Assert.assertTrue(9 == AnalysisUtils.multiplyOperatorCalculation(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(12 == AnalysisUtils.multiplyOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertTrue(12 == AnalysisUtils.multiplyOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(16 == AnalysisUtils.multiplyOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testMultiplyCalculationEquality() throws Exception
    {
        final AnalysisCalculation assertion1 = AnalysisUtils.multiplyOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisCalculation assertion2 = AnalysisUtils.multiplyOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisCalculation assertion3 = AnalysisUtils.multiplyOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertTrue(assertion2.equals(assertion3));
        Assert.assertTrue(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
        Assert.assertTrue(assertion3.hashCode() == assertion2.hashCode());
    }

    @Test
    public void testShouldCreateSubtractCalculation() throws Exception
    {

        Assert.assertTrue(0 == AnalysisUtils.subtractOperatorCalculation(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(-1 == AnalysisUtils.subtractOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertTrue(1 == AnalysisUtils.subtractOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(0 == AnalysisUtils.subtractOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testSubtractCalculationEquality() throws Exception
    {
        final AnalysisCalculation assertion1 = AnalysisUtils.subtractOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisCalculation assertion2 = AnalysisUtils.subtractOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisCalculation assertion3 = AnalysisUtils.subtractOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertFalse(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
    }

    @Test
    public void testShouldCreateAddCalculation() throws Exception
    {

        Assert.assertTrue(6 == AnalysisUtils.addOperatorCalculation(
                dummyThreeCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(7 == AnalysisUtils.addOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation).
                analyse(null));
        Assert.assertTrue(7 == AnalysisUtils.addOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation).
                analyse(null));
        Assert.assertTrue(8 == AnalysisUtils.addOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation).
                analyse(null));
    }

    @Test
    public void testAddCalculationEquality() throws Exception
    {
        final AnalysisCalculation assertion1 = AnalysisUtils.addOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation);
        final AnalysisCalculation assertion2 = AnalysisUtils.addOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation);
        final AnalysisCalculation assertion3 = AnalysisUtils.addOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertTrue(assertion2.equals(assertion3));
        Assert.assertTrue(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
        Assert.assertTrue(assertion3.hashCode() == assertion2.hashCode());
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @Before
    public void setUp() throws Exception
    {
        dummyTrueAssertion = new AnalysisAssertion<AnalysisUtilsTest>()
        {
            public boolean analyse(AnalysisUtilsTest toBeAnalysed)
            {
                return true;
            }
        };
        dummyFalseAssertion = new AnalysisAssertion<AnalysisUtilsTest>()
        {
            public boolean analyse(AnalysisUtilsTest toBeAnalysed)
            {
                return false;
            }
        };
        dummyThreeCalculation = new AnalysisCalculation<AnalysisUtilsTest>()
        {
            public int analyse(AnalysisUtilsTest toBeAnalysed)
            {
                return 3;
            }
        };
        dummyFourCalculation = new AnalysisCalculation<AnalysisUtilsTest>()
        {
            public int analyse(AnalysisUtilsTest toBeAnalysed)
            {
                return 4;
            }
        };
    }
}