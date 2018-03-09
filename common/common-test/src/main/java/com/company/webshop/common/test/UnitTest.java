package com.company.webshop.common.test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.rules.ExpectedException.none;

@RunWith(MockitoJUnitRunner.class)
public class UnitTest {

    @Rule
    public ExpectedException expectedException = none();

}
