package com.epam.wtf.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DogUtilTest {

    @Mock
    DogUtil dogUtil;

    @Test(expected = IllegalArgumentException.class)
    public void testIsVaccine() {
        when(dogUtil.isVaccine("Sharick")).thenReturn(true);
        when(dogUtil.isVaccine("Tuzik")).thenReturn(false);
        when(dogUtil.isVaccine("Barsik")).thenThrow(new IllegalArgumentException("WE DON'T VACCINE CATS!"));

        Assert.assertTrue(dogUtil.isVaccine("Sharick"));
        Assert.assertFalse(dogUtil.isVaccine("Tuzik"));
        Assert.assertTrue(dogUtil.isVaccine("Barsik"));
    }

    @Test
    public void testGenerateName() {
        when(dogUtil.generateName()).thenReturn("Sharick").thenReturn("Tuzik").thenReturn("Barbosss");
        Assert.assertEquals("Sharick", dogUtil.generateName());
        Assert.assertEquals("Tuzik", dogUtil.generateName());
        Assert.assertEquals("Barbosss", dogUtil.generateName());

        verify(dogUtil, times(3)).generateName();
    }
}
