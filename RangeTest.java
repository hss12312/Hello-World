package org.jfree.data;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RangeTest {

    private Range range;
    private double testValue;
    private boolean expectedContains;

    // 通用测试范围
    private static final Range TEST_RANGE = new Range(1.0, 5.0);

    // 参数化测试数据
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // 输入范围, 测试值, 预期结果 (contains)
                { new Range(1.0, 5.0), 3.0, true },   // 值在范围内
                { new Range(1.0, 5.0), 1.0, true },    // 值等于下限
                { new Range(1.0, 5.0), 5.0, true },    // 值等于上限
                { new Range(1.0, 5.0), 0.0, false },   // 值低于下限
                { new Range(1.0, 5.0), 6.0, false }    // 值高于上限
        });
    }

    // 参数化测试构造函数
    public RangeTest(Range range, double testValue, boolean expectedContains) {
        this.range = range;
        this.testValue = testValue;
        this.expectedContains = expectedContains;
    }

    @org.junit.Before
    public void setUp() throws Exception {
        range = new Range(-1.0, 1.0);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        range = null;
    }

    /* ============= intersects() 测试 ============= */
    @org.junit.Test
    public void testIntersects1() {
        assertTrue("部分重叠范围应返回true", TEST_RANGE.intersects(3.0, 6.0));
    }

    @org.junit.Test
    public void testIntersects2() {
        assertFalse("无重叠范围应返回false", TEST_RANGE.intersects(6.0, 8.0));
    }

    @org.junit.Test
    public void testIntersects3() {
        assertTrue("上边界重叠应返回true", TEST_RANGE.intersects(5.0, 7.0));
    }

    @org.junit.Test
    public void testIntersects4() {
        assertTrue("下边界重叠应返回true", TEST_RANGE.intersects(0.0, 1.0));
    }

    /* ============= getLength() 测试 ============= */
    @org.junit.Test
    public void testLength1() {
        Range range = new Range(1.0, 5.0);
        assertEquals("正常范围长度应为4.0", 4.0, range.getLength(), 0.0000001d);
    }

    @org.junit.Test
    public void testLength2() {
        Range range = new Range(-2.0, 2.0);
        assertEquals("包含负数的范围长度应为4.0", 4.0, range.getLength(), 0.0000001d);
    }

    @org.junit.Test
    public void testLength3() {
        Range range = new Range(0.0, 0.0);
        assertEquals("零长度范围应为0", 0.0, range.getLength(), 0.0000001d);
    }

    /* ============= contains() 测试 ============= */
    @org.junit.Test
    public void testContains1() {
        Range range = new Range(1.0, 5.0);
        assertTrue("值在范围内应返回true", range.contains(3.0));
    }

    @org.junit.Test
    public void testContains2() {
        Range range = new Range(1.0, 5.0);
        assertTrue("值等于下限应返回true", range.contains(1.0));
    }

    @org.junit.Test
    public void testContains3() {
        Range range = new Range(1.0, 5.0);
        assertTrue("值等于上限应返回true", range.contains(5.0));
    }

    @org.junit.Test
    public void testContains4() {
        Range range = new Range(1.0, 5.0);
        assertFalse("值低于下限应返回false", range.contains(0.0));
    }

    @org.junit.Test
    public void testContains5() {
        Range range = new Range(1.0, 5.0);
        assertFalse("值高于上限应返回false", range.contains(6.0));
    }
    
    @org.junit.Test
    public void testFailedExampleForDemonstration() {
        assertEquals(1.0, TEST_RANGE.getCentralValue(), 0.0000001d);
    }
}