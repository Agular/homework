package kodutoo5.test;

import kodutoo5.coffee.EspressoGrinder;
import kodutoo5.coffee.Grinder;
import kodutoo5.exceptions.GrinderNotCleanException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashMap;

public class CoffeeGrinderTest {

    private Grinder grinder;
    private EspressoGrinder espressoGrinder;

    @Before
    public void setupBeforeEachTest() {
        grinder = new Grinder();
        espressoGrinder = new EspressoGrinder();
    }

    @Test
    public void NormalGrinderDiameter() throws GrinderNotCleanException {
        Assert.assertEquals(1.0, grinder.grind(), 0.01);
    }

    @Test
    public void EspressoGrinderDiameter() throws GrinderNotCleanException {
        Assert.assertEquals(0.3, espressoGrinder.grind(), 0.01);
    }

    @Test
    public void NormalGrinderGrindCalloutCounterZeroTimes() {
        Assert.assertEquals(0, grinder.getCalloutCounter());
    }

    @Test
    public void NormalGrinderGrindCalloutCounterTwoTimes() throws GrinderNotCleanException {
        grinder.grind();
        grinder.grind();
        Assert.assertEquals(2, grinder.getCalloutCounter());
    }

    @Test
    public void EspressoGrinderGrindCalloutCounterZeroTimes() {
        Assert.assertEquals(0, espressoGrinder.getCalloutCounter());
    }

    @Test
    public void EspressoGrinderGrindCalloutCounterTwoTimes() throws GrinderNotCleanException {
        espressoGrinder.grind();
        espressoGrinder.grind();
        Assert.assertEquals(2, espressoGrinder.getCalloutCounter());
    }

    @Test
    public void NormalGrinderSuccessfullyCleans() throws GrinderNotCleanException {
        for(int i = 0; i < 3; i++){
            grinder.grind();
        }
        grinder.clean();
        Assert.assertEquals(0, grinder.getCalloutCounter());
    }

    @Test
    public void EspressoGrinderSuccessfullyCleans() throws GrinderNotCleanException {
        for(int i = 0; i < 3; i++){
           espressoGrinder.grind();
        }
        espressoGrinder.clean();
        Assert.assertEquals(0, espressoGrinder.getCalloutCounter());
    }

    @Test(expected = GrinderNotCleanException.class)
    public void NormalGrinderThrowsNotCleanExceptionWhenNotClean() throws GrinderNotCleanException {
        for(int i = 0; i < 4; i++){
            grinder.grind();
        }
    }

    @Test(expected = GrinderNotCleanException.class)
    public void EspressoGrinderThrowsNotCleanExceptionWhenNotClean() throws GrinderNotCleanException {
        for(int i = 0; i < 4; i++){
            espressoGrinder.grind();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void NormalGrinderSetEnergySavingModeExceptionWithZeroMinutes() {
        grinder.setEnergySavingModeWithMinutes(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NormalGrinderSetEnergySavingModeExceptionWithSixtyOneMinutes() {
        grinder.setEnergySavingModeWithMinutes(61);
    }

    @Test(expected = IllegalArgumentException.class)
    public void EspressoGrinderSetEnergySavingModeExceptionWithZeroMinutes() {
        espressoGrinder.setEnergySavingModeWithMinutes(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void EspressoGrinderSetEnergySavingModeExceptionWithSixtyOneMinutes() {
        espressoGrinder.setEnergySavingModeWithMinutes(61);
    }

    @Test
    public void NormalGrinderIsInEnergySavingModeFalse(){
        Assert.assertFalse(grinder.isInEnergySavingMode());
    }

    @Test
    public void NormalGrinderIsInEnergySavingModeTrue(){
        grinder.setEnergySavingModeWithMinutes(25);
        Assert.assertTrue(grinder.isInEnergySavingMode());
    }

    @Test
    public void EspressoGrinderIsInEnergySavingModeFalse(){
        Assert.assertFalse(espressoGrinder.isInEnergySavingMode());
    }

    @Test
    public void EspressoGrinderIsInEnergySavingModeTrue(){
        espressoGrinder.setEnergySavingModeWithMinutes(25);
        Assert.assertTrue(espressoGrinder.isInEnergySavingMode());
    }

    @Test
    public void NormalGrinderLeaveEnergyMode(){
        grinder.setEnergySavingModeWithMinutes(25);
        grinder.exitEnergySavingMode();
        Assert.assertFalse(grinder.isInEnergySavingMode());
    }

    @Test
    public void EspressoGrinderLeaveEnergyMode(){
        espressoGrinder.setEnergySavingModeWithMinutes(25);
        espressoGrinder.exitEnergySavingMode();
        Assert.assertFalse(espressoGrinder.isInEnergySavingMode());
    }

    @Test
    public void NormalGrinderLeaveEnergyModeAfterGrind() throws GrinderNotCleanException {
        grinder.setEnergySavingModeWithMinutes(25);
        grinder.grind();
        Assert.assertFalse(grinder.isInEnergySavingMode());
    }

    @Test
    public void EspressoGrinderLeaveEnergyModeAfterGrind() throws GrinderNotCleanException {
        espressoGrinder.setEnergySavingModeWithMinutes(25);
        espressoGrinder.grind();
        Assert.assertFalse(espressoGrinder.isInEnergySavingMode());
    }


    @Test
    public void twoNewGrindersAreEqual(){
        Grinder other = new Grinder();
        Assert.assertTrue(grinder.equals(other));
    }

    @Test
    public void grindersWithDifferentCalloutCountNotEqual() throws GrinderNotCleanException {
        Grinder other = new Grinder();
        other.grind();
        Assert.assertFalse(grinder.equals(other));
    }

    @Test
    public void testHashCodeWithOneGrind() throws GrinderNotCleanException {
        grinder.grind();
        Assert.assertEquals(-1914670732, grinder.hashCode());
    }

    @Test
    public void NormalGrinderEqualsIsSymmetric() throws GrinderNotCleanException {
        Grinder other = new Grinder();
        grinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        other.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        grinder.grind();
        other.grind();
        Assert.assertTrue(grinder.equals(other) && other.equals(grinder));
    }

    @Test
    public void EspressoGrinderEqualsIsSymmetric() throws GrinderNotCleanException {
        EspressoGrinder other = new EspressoGrinder();
        espressoGrinder.setCustomBeanRatio(new BigDecimal("0.1"), new BigDecimal("0.9"));
        other.setCustomBeanRatio(new BigDecimal("0.1"), new BigDecimal("0.9"));
        other.grind();
        espressoGrinder.grind();
        Assert.assertTrue(espressoGrinder.equals(other) && other.equals(espressoGrinder));
    }

    @Test
    public void NormalGrinderEqualsIsReflexive() throws GrinderNotCleanException {
        grinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        grinder.grind();
        Assert.assertTrue(grinder.equals(grinder));
    }

    @Test
    public void EspressoGrinderEqualsIsReflexive() throws GrinderNotCleanException {
        espressoGrinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        espressoGrinder.grind();
        Assert.assertTrue(espressoGrinder.equals(espressoGrinder));
    }

    @Test
    public void NormalGrinderEqualsIsTransitive() throws GrinderNotCleanException {
        Grinder other = new Grinder();
        Grinder third = new Grinder();
        grinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        other.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        third.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        grinder.grind();
        other.grind();
        third.grind();
        Assert.assertTrue(grinder.equals(other) && other.equals(third)
        && grinder.equals(third));
    }

    @Test
    public void EspressoGrinderEqualsIsTransitive() throws GrinderNotCleanException {
        EspressoGrinder other = new EspressoGrinder();
        EspressoGrinder third = new EspressoGrinder();
        espressoGrinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        other.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        third.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        espressoGrinder.grind();
        other.grind();
        third.grind();
        Assert.assertTrue(espressoGrinder.equals(other) && other.equals(third)
        && espressoGrinder.equals(third));
    }

    @Test
    public void GrindersCanBeUsedWithHashMap() throws GrinderNotCleanException {
        HashMap<String, Grinder> map = new HashMap<>();
        Grinder otherGrinder = new Grinder();
        EspressoGrinder otherEspressoGrinder = new EspressoGrinder();

        grinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        espressoGrinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        otherGrinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));
        otherEspressoGrinder.setCustomBeanRatio(new BigDecimal("0.5"), new BigDecimal("0.5"));

        grinder.grind();
        espressoGrinder.grind();
        otherGrinder.grind();
        otherGrinder.grind();
        otherEspressoGrinder.grind();
        otherEspressoGrinder.grind();

        map.put("grinder", grinder);
        map.put("espressoGrinder", espressoGrinder);
        map.put("otherGrinder", otherGrinder);
        map.put("otherEspressoGrinder", otherEspressoGrinder);

        Assert.assertEquals(1, map.get("grinder").getCalloutCounter());
        Assert.assertEquals(1, map.get("espressoGrinder").getCalloutCounter());
        Assert.assertEquals(2, map.get("otherGrinder").getCalloutCounter());
        Assert.assertEquals(2, map.get("otherEspressoGrinder").getCalloutCounter());
    }

    @Test
    public void GrinderGetNewInstanceNotMorning(){
        LocalTime localTime = LocalTime.of(10,43,12);
        Grinder grinder = Grinder.newInstance(localTime);
        Assert.assertFalse(grinder instanceof EspressoGrinder);
    }

    @Test
    public void GrinderGetNewInstanceInTheMorning(){
        LocalTime localTime = LocalTime.of(7,43,12);
        Grinder grinder = Grinder.newInstance(localTime);
        Assert.assertTrue(grinder instanceof EspressoGrinder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void GrinderWrongBeanRatioThrowsIllegalArgumentException(){
        grinder.setCustomBeanRatio(new BigDecimal("1.00"), new BigDecimal("0.57"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GrinderWrongBeanRatioNegativeArgumentThrowException(){
        grinder.setCustomBeanRatio(new BigDecimal("0.87"), new BigDecimal("-0.34"));
    }

    @Test
    public void GrinderPrimaryBeansInGramsAfterOneCup() throws GrinderNotCleanException {
        grinder.grind();
        Assert.assertEquals(new BigDecimal("140.00"), grinder.getPrimaryBeansInGrams().setScale(2));
    }

    @Test
    public void GrinderPrimaryBeansInGramsAfterFiveCups() throws GrinderNotCleanException {
        for(int i=0; i< 5; i++){
            grinder.grind();
            grinder.clean();
        }
        Assert.assertEquals(new BigDecimal("100.00"), grinder.getPrimaryBeansInGrams().setScale(2));
    }

    @Test
    public void GrinderBeansInGramsAfterOneCupFourToOneRatio() throws GrinderNotCleanException {
        grinder.setCustomBeanRatio(new BigDecimal("0.8"), new BigDecimal("0.2"));
        grinder.grind();
        Assert.assertEquals(new BigDecimal("142.00"), grinder.getPrimaryBeansInGrams().setScale(2));
        Assert.assertEquals(new BigDecimal("83.00"), grinder.getSecondaryBeansInGrams().setScale(2));
    }

    @Test
    public void GrinderBeansInGramsAfterFiveCupsThreeToOneRatio() throws GrinderNotCleanException {
        grinder.setCustomBeanRatio(new BigDecimal("0.75"), new BigDecimal("0.25"));
        grinder.grind();
        grinder.grind();
        grinder.grind();
        Assert.assertEquals(new BigDecimal("127.50"), grinder.getPrimaryBeansInGrams().setScale(2));
        Assert.assertEquals(new BigDecimal("77.50"), grinder.getSecondaryBeansInGrams().setScale(2));
    }

    @Test(expected = IllegalStateException.class)
    public void GrinderNoPrimaryBeansLeftThrowIllegalStateException() throws GrinderNotCleanException {
        for(int i =0; i< 16; i++){
            grinder.grind();
            grinder.clean();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void GrinderNoSecondaryBeansLeftThrowIllegalStateException() throws GrinderNotCleanException {
        grinder.setCustomBeanRatio(new BigDecimal("0.1"), new BigDecimal("0.9"));
        for(int i = 0; i< 10; i++){
            grinder.grind();
            grinder.clean();
        }
    }
}
