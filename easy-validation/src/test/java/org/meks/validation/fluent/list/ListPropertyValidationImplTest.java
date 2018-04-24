package org.meks.validation.fluent.list;

import org.junit.Test;
import org.meks.validation.fluent.Validation;
import org.meks.validation.fluent.result.ValidationResult;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class ListPropertyValidationImplTest {

    @Test
    public void test1() {
    }

    @Test
    public void givenPropertyGetterWhenTestGetsExpectedList() {
        List<Point> points = Arrays.asList(new Point(1, 1), new Point(2, 3));

        @SuppressWarnings("unchecked")
        Function<Point, Double> propertyGetter = Mockito.mock(Function.class);
        doReturn(points.get(0).getY()).when(propertyGetter).apply(points.get(0));
        doReturn(points.get(1).getY()).when(propertyGetter).apply(points.get(1));

        @SuppressWarnings("unchecked")
        Validation<List<Double>> validation = Mockito.mock(Validation.class);
        doReturn(ValidationResult.ok()).when(validation).test(() -> points.stream().map(propertyGetter).collect(Collectors.toList()));

        ListPropertyValidationImpl.onProperty(propertyGetter, validation).test(() -> points);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<Supplier<List<Double>>> argumentCaptor = ArgumentCaptor.forClass(Supplier.class);
        verify(validation).test(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().get()).containsExactly(1.0, 3.0);
        verify(propertyGetter).apply(points.get(0));
        verify(propertyGetter).apply(points.get(1));
    }
}