package org.meks.validation.validations.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meks.validation.AbstractValidationsTest;
import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescription;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DateValidationsTest extends AbstractValidationsTest {

    @Mock
    private CoreDateValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return DateValidations.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    @Test
    public void testThatIsDateAfterDelegatesToCoreValidations() {
        LocalDateTime dateBefore = LocalDateTime.of(2010, 5, 1, 10, 5, 10);
        when(messageResolver.getIsDateAfterMessage(dateBefore)).thenReturn(expectedMessage);
        Validation<LocalDateTime> expectedValidation = mockValidation();
        when(coreValidations.isDateAfter(same(dateBefore), any(ErrorDescription.class))).thenReturn(expectedValidation);

        Validation<LocalDateTime> validation = DateValidations.isDateAfter(dateBefore);

        assertThat(validation).isSameAs(expectedValidation);
        ArgumentCaptor<ErrorDescription> errorDescriptionArgCaptor = getErrorDescCaptor();
        verify(coreValidations).isDateAfter(same(dateBefore), errorDescriptionArgCaptor.capture());
        assertErrorDescMessageOnly(errorDescriptionArgCaptor);
    }

}