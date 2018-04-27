package org.meks.validation;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.meks.validation.result.ErrorDescription;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractCoreValidationsTest {

    protected final String exptectedMessage = "my expected error message";

    @Mock
    protected ErrorDescription errorDescription;

    @Before
    public void mockErrorDescription() {
        Mockito.doReturn(exptectedMessage).when(errorDescription).getErrorMessage();
    }
}
