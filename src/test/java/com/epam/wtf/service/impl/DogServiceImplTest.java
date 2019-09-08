package com.epam.wtf.service.impl;

import com.epam.wtf.model.Dog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DogServiceImplTest {

    private Dog dog = new Dog("Sharick", "desc", (short) 5, false);

    @Mock
    DogServiceImpl dogService;

    @Test
    public void testGetDogNames() {
        DogServiceImpl dogServiceSpy = spy(new DogServiceImpl());
        when(dogServiceSpy.getDogNames()).thenReturn(Arrays.asList("Sharick", "Tuzik"));
        Assert.assertEquals(2, dogServiceSpy.getDogNames().size());

        Assert.assertTrue(dogServiceSpy.getDogNames().contains("Sharick"));
        Assert.assertTrue(dogServiceSpy.getDogNames().contains("Tuzik"));
        Assert.assertFalse(dogServiceSpy.getDogNames().contains("Barsik"));
    }

    @Test
    public void testGetDogCounts() {
        when(dogService.getDogCounts()).thenReturn(2);
        when(dogService.getDogCounts("Sharick")).thenReturn(1);

        Assert.assertEquals(2, dogService.getDogCounts());
        Assert.assertEquals(1, dogService.getDogCounts("Sharick"));
    }

    @Test
    public void testAddDog() {
        Assert.assertFalse(dog.isVaccine());
        when(dogService.addDog("vaccine")).thenAnswer(answer);
        Assert.assertTrue(dogService.addDog("vaccine").isVaccine());
    }

    private Answer<Dog> answer = (invocation) -> {
        Object mock = invocation.getMock();

        Object[] args = invocation.getArguments();
        String vaccine = (String) args[0];
        if (!vaccine.isEmpty()) {
            dog.setVaccine(true);
        }
        return dog;
    };
}
