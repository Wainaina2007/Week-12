package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		
		testDemo = new TestDemo();
	}

	@ParameterizedTest
    @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
        if (!expectException) {
            assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
        } else {
            assertThatThrownBy(() -> testDemo.addPositive(a, b))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                Arguments.arguments(2, 3, 5, false),
                Arguments.arguments(0, 3, 0, true),
                Arguments.arguments(-1, 2, 1, true),
                Arguments.arguments(5, 5, 10, false)
        );
	}



    @Test
    void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
    assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
    assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
    
}

    @Test
    void assertThatPairsOfPositiveNumbersAreMultipliedCorrectly() {
        assertThat(testDemo.multiplyPositive(3, 4)).isEqualTo(12);
        assertThat(testDemo.multiplyPositive(10, 10)).isEqualTo(100);
    } 
    
    @Test
    void assertThatNumberSquaredIsCorrect() {
        TestDemo mockDemo = spy(testDemo);
        doReturn(5).when(mockDemo).getRandomInt();
        int fiveSquared = mockDemo.randomNumberSquared();
        assertThat(fiveSquared).isEqualTo(25);
    }
}
