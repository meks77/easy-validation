package at.meks.validation.validations.date;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractValidationsTest;
import at.meks.validation.validations.common.CommonValidations;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

import static at.meks.validation.validations.common.CommonValidations.isBetween;
import static at.meks.validation.validations.common.CommonValidations.isGreaterThan;
import static at.meks.validation.validations.common.CommonValidations.isLessThan;
import static at.meks.validation.validations.date.DateValidations.isLocalDateBetween;
import static at.meks.validation.validations.date.DateValidations.isLocalDateTimeBetween;
import static at.meks.validation.validations.date.DateValidations.isZonedDateTimeBefore;
import static at.meks.validation.validations.date.DateValidations.isZonedDateTimeBetween;
import static java.time.LocalDateTime.of;
import static java.time.ZoneId.systemDefault;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.same;
import static org.mockito.Mockito.mock;

@PrepareForTest(CommonValidations.class)
public class DateValidationsTest extends AbstractValidationsTest<LocalDateTime> {

    @Mock
    private CoreDateValidations coreValidations;

    private Validation<Comparable<?>> expectedValidation;

    @Override
    protected Class<?> getTestedClass() {
        return DateValidations.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    private DateValidationsTestHelper testHelper;

    @Before
    public void initTestHelper() {
        testHelper = new DateValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<DateValidations> constructor = DateValidations.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testIsLocalDateAfter() throws Exception {
        LocalDate expectedDate = mockIsGreaterThan(LocalDate.of(2011, 1, 1));
        Validation<LocalDate> validation = DateValidations.isLocalDateAfter(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate)));
    }

    @Test
    public void testIsLocalDateAfterWithSupplier() throws Exception {
        Supplier<LocalDate> expectedSupplier = mockIsGreaterThan(() -> LocalDate.of(2011, 1, 1));
        Validation<Comparable<LocalDate>> validation = DateValidations.isLocalDateAfter(expectedSupplier);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedSupplier)));
    }

    @Test
    public void testIsLocalDateTimeAfter() throws Exception {
        LocalDateTime expectedDate = mockIsGreaterThan(of(2011, 1, 1, 2, 2));
        Validation<LocalDateTime> validation = DateValidations.isLocalDateTimeAfter(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate)));
    }

    @Test
    public void testIsLocalDateTimeAfterWithSupplier() throws Exception {
        Supplier<LocalDateTime> expectedSupplier = mockIsGreaterThan(() -> of(2011, 1, 1, 5, 1));
        Validation<Comparable<LocalDateTime>> validation = DateValidations.isLocalDateTimeAfter(expectedSupplier);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedSupplier)));
    }

    @Test
    public void testIsZonedDateTimeAfter() throws Exception {
        ZonedDateTime expectedDate = mockIsGreaterThan(ZonedDateTime.of(of(2011, 1, 1, 2, 2), systemDefault()));
        Validation<ZonedDateTime> validation = DateValidations.isZonedDateTimeAfter(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate)));
    }

    @Test
    public void testIsZonedDateTimeAfterWithSupplier() throws Exception {
        Supplier<ZonedDateTime> expectedDate = mockIsGreaterThan(() -> ZonedDateTime.of(of(2011, 1, 1, 2, 2),
                systemDefault()));
        Validation<Comparable<ZonedDateTime>> validation = DateValidations.isZonedDateTimeAfter(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate)));
    }

    private <T> T mockIsGreaterThan(T expectedDate) throws Exception {
        return mockCommonValidations(expectedDate, "isGreaterThan");
    }

    @Test
    public void testIsLocalDateBefore() throws Exception {
        LocalDate expectedDate = mockIsLessThan(LocalDate.of(2011, 1, 1));
        Validation<LocalDate> validation = DateValidations.isLocalDateBefore(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate)));
    }

    @Test
    public void testIsLocalDateBeforeWithSupplier() throws Exception {
        Supplier<LocalDate> expectedSupplier = mockIsLessThan(() -> LocalDate.of(2011, 1, 1));
        Validation<Comparable<LocalDate>> validation = DateValidations.isLocalDateBefore(expectedSupplier);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedSupplier)));
    }

    @Test
    public void testIsLocalDateTimeBefore() throws Exception {
        LocalDateTime expectedDate = mockIsLessThan(of(2011, 1, 1, 2, 2));
        Validation<LocalDateTime> validation = DateValidations.isLocalDateTimeBefore(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate)));
    }

    @Test
    public void testIsLocalDateTimeBeforeWithSupplier() throws Exception {
        Supplier<LocalDateTime> expectedSupplier = mockIsLessThan(() -> of(2011, 1, 1, 5, 1));
        Validation<Comparable<LocalDateTime>> validation = DateValidations.isLocalDateTimeBefore(expectedSupplier);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedSupplier)));
    }

    @Test
    public void testIsZonedDateTimeBefore() throws Exception {
        ZonedDateTime expectedDate = mockIsLessThan(ZonedDateTime.of(of(2011, 1, 1, 2, 2), systemDefault()));
        Validation<ZonedDateTime> validation = isZonedDateTimeBefore(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate)));
    }

    @Test
    public void testIsZonedDateTimeBeforeWithSupplier() throws Exception {
        Supplier<ZonedDateTime> expectedDate = mockIsLessThan(() -> ZonedDateTime.of(of(2011, 1, 1, 2, 2),
                systemDefault()));
        Validation<Comparable<ZonedDateTime>> validation = isZonedDateTimeBefore(expectedDate);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate)));
    }

    private <T> T mockIsLessThan(T expectedDate) throws Exception {
        return mockCommonValidations(expectedDate, "isLessThan");
    }

    @Test
    public void testIsLocalDateBetween() throws Exception {
        Range<LocalDate> dateRange = mockIsBetween(LocalDate.of(2011, 1, 1), LocalDate.of(2012, 1, 1));
        Validation<LocalDate> validation = isLocalDateBetween(dateRange.getMin(), dateRange.getMax());
        verifyResultAndCommonsInvocation(validation, () -> isBetween(dateRange.getMin(), dateRange.getMax()));
    }

    @Test
    public void testIsLocalDateBetweenWithSupplier() throws Exception {
        Range<Supplier<LocalDate>> range = mockIsBetween(() -> LocalDate.of(2011, 1, 1), () -> LocalDate.of(2011, 1, 1));
        Validation<Comparable<LocalDate>> validation = isLocalDateBetween(range.getMin(), range.getMax());
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax())));
    }

    @Test
    public void testIsLocalDateTimeBetween() throws Exception {
        Range<LocalDateTime> range = mockIsBetween(LocalDateTime.of(2011, 1, 1, 2, 2),
                LocalDateTime.of(2012, 1, 1, 2, 2));
        Validation<LocalDateTime> validation = isLocalDateTimeBetween(range.getMin(), range.getMax());
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax())));
    }

    @Test
    public void testIsLocalDateTimeBetweenWithSupplier() throws Exception {
        Range<Supplier<LocalDateTime>> range = mockIsBetween(() -> of(2011, 1, 1, 5, 1),
                () -> of(2012, 1, 1, 2, 2));
        Validation<Comparable<LocalDateTime>> validation = isLocalDateTimeBetween(range.getMin(), range.getMax());
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax())));
    }

    @Test
    public void testIsZonedDateTimeBetween() throws Exception {
        Range<ZonedDateTime> range = mockIsBetween(ZonedDateTime.of(of(2011, 1, 1, 2, 2),systemDefault()),
                ZonedDateTime.of(of(2012, 1, 1, 2, 2), systemDefault()));
        Validation<ZonedDateTime> validation = isZonedDateTimeBetween(range.getMin(), range.getMax());
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax())));
    }

    @Test
    public void testIsZonedDateTimeBetweenWithSupplier() throws Exception {
        Range<Supplier<ZonedDateTime>> range = mockIsBetween(
                () -> ZonedDateTime.of(of(2011, 1, 1, 2, 2), systemDefault()),
                () -> ZonedDateTime.of(of(2012, 1, 1, 2, 2), systemDefault()));
        Validation<Comparable<ZonedDateTime>> validation = isZonedDateTimeBetween(range.getMin(), range.getMax());
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax())));
    }

    private <T> Range<T> mockIsBetween(T minDate, T maxDate) throws Exception {
        prepareCommonsMock();
        mockCommonsMethod("isBetween", minDate, maxDate);
        return Range.of(minDate, maxDate);
    }

    private <T> Range<Supplier<T>> mockIsBetween(Supplier<T> minDate, Supplier<T> maxDate) throws Exception {
        prepareCommonsMock();
        mockCommonsMethod("isBetween", minDate, maxDate);
        return Range.of(minDate, maxDate);
    }

    private <V> void verifyResultAndCommonsInvocation(Validation<V> currentValidation, Runnable verifications) {
        assertThat((Object) currentValidation).isSameAs(expectedValidation);
        verifyCommonsInvocations(verifications);
    }

    private void verifyCommonsInvocations(Runnable verifications) {
        PowerMockito.verifyStatic(CommonValidations.class);
        verifications.run();
    }

    private <T> T mockCommonValidations(T expectedDate, String commonsMethodName)
            throws Exception {
        prepareCommonsMock();
        mockCommonsMethod(commonsMethodName, expectedDate);
        return expectedDate;
    }

    private void prepareCommonsMock() {
        PowerMockito.mockStatic(CommonValidations.class);
        expectedValidation = getStubValidation();
    }

    private void mockCommonsMethod(String commonsMethodName, Object...methodArgs) throws Exception {
        PowerMockito.when(CommonValidations.class, commonsMethodName, methodArgs).thenReturn(expectedValidation);
    }

    private <V> Validation<V> getStubValidation() {
        //noinspection unchecked
        return mock(Validation.class);
    }

    @Test
    public void testIsLocalDateFirstDayOfYear() {
        testHelper.testIsLocalDateFirstDayOfYear(DateValidations::isLocalDateFirstDayOfYear);
    }
    @Test
    public void testIsLocalDateTimeFirstDayOfYear() {
        testHelper.testIsLocalDateTimeFirstDayOfYear(DateValidations::isLocalDateTimeFirstDayOfYear);
    }

    @Test
    public void testIsZonedDateFirstDayOfYear() {
        testHelper.testIsZonedDateTimeFirstDayOfYear(DateValidations::isZonedDateTimeFirstDayOfYear);
    }

    @Test
    public void testIsLocalDateFirstDayOfMonth() {
        testHelper.testIsLocalDateFirstDayOfMonth(DateValidations::isLocalDateFirstDayOfMonth);
    }

    @Test
    public void testIsLocalDateTimeFirstDayOfMonth() {
        testHelper.testIsLocalDateTimeFirstDayOfMonth(DateValidations::isLocalDateTimeFirstDayOfMonth);
    }

    @Test
    public void testIsZonedDateTimeFirstDayOfMonth() {
        testHelper.testIsZonedDateTimeFirstDayOfMonth(DateValidations::isZonedDateTimeFirstDayOfMonth);
    }

    @Test
    public void testIsLocalDateTimeStartOfDay() {
        testHelper.testIsLocalDateTimeStartOfDay(DateValidations::isLocalDateTimeStartOfDay);
    }

    @Test
    public void testIsZonedDateTimeStartOfDay() {
        testHelper.testIsZonedDateTimeStartOfDay(DateValidations::isZonedDateTimeStartOfDay);
    }

    @Test
    public void testIsLocalDateLastDayOfMonth() {
        testHelper.testIsLocalDateLastDayOfMonth(DateValidations::isLocalDateLastDayOfMonth);
    }

    @Test
    public void testIsLocalDateTimeLastDayOfMonth() {
        testHelper.testIsLocalDateTimeLastDayOfMonth(DateValidations::isLocalDateTimeLastDayOfMonth);
    }

    @Test
    public void testIsZonedDateTimeLastDayOfMonth() {
        testHelper.testIsZonedDateTimeLastDayOfMonth(DateValidations::isZonedDateTimeLastDayOfMonth);
    }

}