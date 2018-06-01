package at.meks.validation.validations.date;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractErrorCodeValidationsTest;
import at.meks.validation.validations.common.CommonValidationsWithErrorCode;
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
import java.util.Random;
import java.util.function.Supplier;

import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isBetween;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isGreaterThan;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isLessThan;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isLocalDateAfter;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isLocalDateBefore;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isLocalDateBetween;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isLocalDateTimeAfter;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isLocalDateTimeBefore;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isLocalDateTimeBetween;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isZonedDateTimeAfter;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isZonedDateTimeBefore;
import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isZonedDateTimeBetween;
import static java.time.LocalDateTime.of;
import static java.time.ZoneId.systemDefault;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.same;


@PrepareForTest(CommonValidationsWithErrorCode.class)
public class DateValidationsWithErrorCodeTest extends AbstractErrorCodeValidationsTest<LocalDateTime> {

    @Mock
    private CoreDateValidations coreValidations;

    private Validation<Comparable<?>> expectedValidation;
    private final String expectedErrorCode = "utErrCode" + new Random().nextInt();


    @Override
    protected Class<?> getTestedClass() {
        return DateValidationsWithErrorCode.class;
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
        Constructor<DateValidationsWithErrorCode> constructor = DateValidationsWithErrorCode.class
                .getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testIsLocalDateAfter() throws Exception {
        LocalDate expectedDate = mockIsGreaterThan(LocalDate.of(2011, 1, 1));
        Validation<LocalDate> validation = isLocalDateAfter(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate), same(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateAfterWithSupplier() throws Exception {
        Supplier<LocalDate> expectedSupplier = mockIsGreaterThan(() -> LocalDate.of(2011, 1, 1));
        Validation<Comparable<LocalDate>> validation = isLocalDateAfter(expectedSupplier, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedSupplier), eq(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateTimeAfter() throws Exception {
        LocalDateTime expectedDate = mockIsGreaterThan(of(2011, 1, 1, 2, 2));
        Validation<LocalDateTime> validation = isLocalDateTimeAfter(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate), same(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateTimeAfterWithSupplier() throws Exception {
        Supplier<LocalDateTime> expectedSupplier = mockIsGreaterThan(() -> of(2011, 1, 1, 5, 1));
        Validation<Comparable<LocalDateTime>> validation = isLocalDateTimeAfter(expectedSupplier,
                expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedSupplier), eq(expectedErrorCode)));
    }

    @Test
    public void testIsZonedDateTimeAfter() throws Exception {
        ZonedDateTime expectedDate = mockIsGreaterThan(ZonedDateTime.of(of(2011, 1, 1, 2, 2), systemDefault()));
        Validation<ZonedDateTime> validation = isZonedDateTimeAfter(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate), eq(expectedErrorCode)));
    }

    @Test
    public void testIsZonedDateTimeAfterWithSupplier() throws Exception {
        Supplier<ZonedDateTime> expectedDate = mockIsGreaterThan(() -> ZonedDateTime.of(of(2011, 1, 1, 2, 2),
                systemDefault()));
        Validation<Comparable<ZonedDateTime>> validation = isZonedDateTimeAfter(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isGreaterThan(same(expectedDate), eq(expectedErrorCode)));
    }

    private <T> T mockIsGreaterThan(T expectedDate) throws Exception {
        return mockCommonValidations(expectedDate, "isGreaterThan");
    }

    @Test
    public void testIsLocalDateBefore() throws Exception {
        LocalDate expectedDate = mockIsLessThan(LocalDate.of(2011, 1, 1));
        Validation<LocalDate> validation = isLocalDateBefore(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate), eq(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateBeforeWithSupplier() throws Exception {
        Supplier<LocalDate> expectedSupplier = mockIsLessThan(() -> LocalDate.of(2011, 1, 1));
        Validation<Comparable<LocalDate>> validation = isLocalDateBefore(expectedSupplier, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedSupplier), eq(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateTimeBefore() throws Exception {
        LocalDateTime expectedDate = mockIsLessThan(of(2011, 1, 1, 2, 2));
        Validation<LocalDateTime> validation = isLocalDateTimeBefore(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate), eq(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateTimeBeforeWithSupplier() throws Exception {
        Supplier<LocalDateTime> expectedSupplier = mockIsLessThan(() -> of(2011, 1, 1, 5, 1));
        Validation<Comparable<LocalDateTime>> validation = isLocalDateTimeBefore(expectedSupplier, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedSupplier), eq(expectedErrorCode)));
    }

    @Test
    public void testIsZonedDateTimeBefore() throws Exception {
        ZonedDateTime expectedDate = mockIsLessThan(ZonedDateTime.of(of(2011, 1, 1, 2, 2), systemDefault()));
        Validation<ZonedDateTime> validation = isZonedDateTimeBefore(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate), eq(expectedErrorCode)));
    }

    @Test
    public void testIsZonedDateTimeBeforeWithSupplier() throws Exception {
        Supplier<ZonedDateTime> expectedDate = mockIsLessThan(() -> ZonedDateTime.of(of(2011, 1, 1, 2, 2),
                systemDefault()));
        Validation<Comparable<ZonedDateTime>> validation = isZonedDateTimeBefore(expectedDate, expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isLessThan(same(expectedDate), eq(expectedErrorCode)));
    }

    private <T> T mockIsLessThan(T expectedDate) throws Exception {
        return mockCommonValidations(expectedDate, "isLessThan");
    }

    @Test
    public void testIsLocalDateBetween() throws Exception {
        Range<LocalDate> dateRange = mockIsBetween(LocalDate.of(2011, 1, 1), LocalDate.of(2012, 1, 1));
        Validation<LocalDate> validation = isLocalDateBetween(dateRange.getMin(), dateRange.getMax(),
                expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isBetween(dateRange.getMin(), dateRange.getMax(),
                expectedErrorCode));
    }

    @Test
    public void testIsLocalDateBetweenWithSupplier() throws Exception {
        Range<Supplier<LocalDate>> range = mockIsBetween(() -> LocalDate.of(2011, 1, 1), () -> LocalDate.of(2011, 1, 1));
        Validation<Comparable<LocalDate>> validation = isLocalDateBetween(range.getMin(), range.getMax(),
                expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax()),
                eq(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateTimeBetween() throws Exception {
        Range<LocalDateTime> range = mockIsBetween(LocalDateTime.of(2011, 1, 1, 2, 2),
                LocalDateTime.of(2012, 1, 1, 2, 2));
        Validation<LocalDateTime> validation = isLocalDateTimeBetween(range.getMin(), range.getMax(),
                expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax()),
                eq(expectedErrorCode)));
    }

    @Test
    public void testIsLocalDateTimeBetweenWithSupplier() throws Exception {
        Range<Supplier<LocalDateTime>> range = mockIsBetween(() -> of(2011, 1, 1, 5, 1),
                () -> of(2012, 1, 1, 2, 2));
        Validation<Comparable<LocalDateTime>> validation = isLocalDateTimeBetween(range.getMin(), range.getMax
                (), expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax()), eq
                (expectedErrorCode)));
    }

    @Test
    public void testIsZonedDateTimeBetween() throws Exception {
        Range<ZonedDateTime> range = mockIsBetween(ZonedDateTime.of(of(2011, 1, 1, 2, 2),systemDefault()),
                ZonedDateTime.of(of(2012, 1, 1, 2, 2), systemDefault()));
        Validation<ZonedDateTime> validation = isZonedDateTimeBetween(range.getMin(), range.getMax(),
                expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax()), eq
                (expectedErrorCode)));
    }

    @Test
    public void testIsZonedDateTimeBetweenWithSupplier() throws Exception {
        Range<Supplier<ZonedDateTime>> range = mockIsBetween(
                () -> ZonedDateTime.of(of(2011, 1, 1, 2, 2), systemDefault()),
                () -> ZonedDateTime.of(of(2012, 1, 1, 2, 2), systemDefault()));
        Validation<Comparable<ZonedDateTime>> validation = isZonedDateTimeBetween(range.getMin(), range.getMax(),
                expectedErrorCode);
        verifyResultAndCommonsInvocation(validation, () -> isBetween(same(range.getMin()), same(range.getMax()), eq
                (expectedErrorCode)));
    }

    private <T> Range<T> mockIsBetween(T minDate, T maxDate) throws Exception {
        prepareCommonsMock();
        mockCommonsMethod("isBetween", minDate, maxDate, expectedErrorCode);
        return Range.of(minDate, maxDate);
    }

    private <V> void verifyResultAndCommonsInvocation(Validation<V> currentValidation, Runnable verifications) {
        assertThat((Object) currentValidation).isSameAs(expectedValidation);
        verifyCommonsInvocations(verifications);
    }

    private void verifyCommonsInvocations(Runnable verifications) {
        PowerMockito.verifyStatic(CommonValidationsWithErrorCode.class);
        verifications.run();
    }

    private <T> T mockCommonValidations(T expectedDate, String commonsMethodName)
            throws Exception {
        prepareCommonsMock();
        mockCommonsMethod(commonsMethodName, expectedDate, expectedErrorCode);
        return expectedDate;
    }

    private void prepareCommonsMock() {
        PowerMockito.mockStatic(CommonValidationsWithErrorCode.class);
        expectedValidation = getStubValidation();
    }

    private void mockCommonsMethod(String commonsMethodName, Object...methodArgs) throws Exception {
        PowerMockito.when(CommonValidationsWithErrorCode.class, commonsMethodName, methodArgs).thenReturn(expectedValidation);
    }

    private <V> Validation<V> getStubValidation() {
        //noinspection unchecked
        return mock(Validation.class);
    }

    @Test
    public void testIsLocalDateFirstDayOfYear() {
        testHelper.testIsLocalDateFirstDayOfYear(
                () -> DateValidationsWithErrorCode.isLocalDateFirstDayOfYear(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateTimeFirstDayOfYear() {
        testHelper.testIsLocalDateTimeFirstDayOfYear(
                () -> DateValidationsWithErrorCode.isLocalDateTimeFirstDayOfYear(EXPECTED_CODE));
    }

    @Test
    public void testIsZonedDateFirstDayOfYear() {
        testHelper.testIsZonedDateTimeFirstDayOfYear(
                () -> DateValidationsWithErrorCode.isZonedDateTimeFirstDayOfYear(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateLastDayOfYear() {
        testHelper.testIsLocalDateLastDayOfYear(
                () -> DateValidationsWithErrorCode.isLocalDateLastDayOfYear(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateTimeLastDayOfYear() {
        testHelper.testIsLocalDateTimeLastDayOfYear(
                () -> DateValidationsWithErrorCode.isLocalDateTimeLastDayOfYear(EXPECTED_CODE));
    }

    @Test
    public void testIsZonedDateLastDayOfYear() {
        testHelper.testIsZonedDateTimeLastDayOfYear(
                () -> DateValidationsWithErrorCode.isZonedDateTimeLastDayOfYear(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateFirstDayOfMonth() {
        testHelper.testIsLocalDateFirstDayOfMonth(
                () -> DateValidationsWithErrorCode.isLocalDateFirstDayOfMonth(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateTimeFirstDayOfMonth() {
        testHelper.testIsLocalDateTimeFirstDayOfMonth(
                () -> DateValidationsWithErrorCode.isLocalDateTimeFirstDayOfMonth(EXPECTED_CODE));
    }

    @Test
    public void testIsZonedDateTimeFirstDayOfMonth() {
        testHelper.testIsZonedDateTimeFirstDayOfMonth(
                () -> DateValidationsWithErrorCode.isZonedDateTimeFirstDayOfMonth(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateTimeStartOfDay() {
        testHelper.testIsLocalDateTimeStartOfDay(
                () -> DateValidationsWithErrorCode.isLocalDateTimeStartOfDay(EXPECTED_CODE));
    }

    @Test
    public void testIsZonedDateTimeStartOfDay() {
        testHelper.testIsZonedDateTimeStartOfDay(
                () -> DateValidationsWithErrorCode.isZonedDateTimeStartOfDay(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateLastDayOfMonth() {
        testHelper.testIsLocalDateLastDayOfMonth(
                () -> DateValidationsWithErrorCode.isLocalDateLastDayOfMonth(EXPECTED_CODE));
    }

    @Test
    public void testIsLocalDateTimeLastDayOfMonth() {
        testHelper.testIsLocalDateTimeLastDayOfMonth(
                () -> DateValidationsWithErrorCode.isLocalDateTimeLastDayOfMonth(EXPECTED_CODE));
    }

    @Test
    public void testIsZonedDateTimeLastDayOfMonth() {
        testHelper.testIsZonedDateTimeLastDayOfMonth(
                () -> DateValidationsWithErrorCode.isZonedDateTimeLastDayOfMonth(EXPECTED_CODE));
    }

}