package com.kushmyruk.ioc;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ContextTest {

    @Test(expected = NoSuchBeanException.class)
    public void getBeanWithEmptyContext() throws Exception {
        Context context = new ApplicationContext();
        context.getBean("abc");
    }

    @Test
    public void getBeanDefinitionNamesWithEmptyContext() throws Exception {
        Context context = new ApplicationContext();

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithOneBeanDefinition() throws Exception {
        String beanName = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanName,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {beanName};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithEmptyBeanDefinition() throws Exception {
        Map<String, Map<String, Object>> beanDescriptions = Collections.emptyMap();
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithSeveralBeanDefinition() throws Exception {
        String beanNameFirst = "FirstBean";
        String beanNameSecond = "SecondBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanNameFirst,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
            put(beanNameSecond,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {beanNameFirst, beanNameSecond};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanWithOneBeanDefinitionIsNotNull() throws Exception {
        String beanName = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanName,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        Object bean = context.getBean(beanName);

        assertNotNull(bean);
    }

    @Test
    public void getBeanWithOneBeanDefinition() throws Exception {
        String beanName = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanName,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest bean = (ProxyTest) context.getBean(beanName);

        assertNotNull(bean);
    }

    @Test
    public void getBeanIsSingletonByDefault() throws Exception {
        String beanName1 = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanName1,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest bean1 = (ProxyTest) context.getBean(beanName1);
        ProxyTest bean2 = (ProxyTest) context.getBean(beanName1);

        assertSame(bean1, bean2);
    }

    @Test
    public void getBeanIsPrototype() throws Exception {
        String beanName1 = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanName1,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                        put("isPrototype", true);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest bean1 = (ProxyTest) context.getBean(beanName1);
        ProxyTest bean2 = (ProxyTest) context.getBean(beanName1);

        assertNotSame(bean1, bean2);
    }

    @Test
    public void getBeanAreDifferentWithDifferentNames() throws Exception {
        String beanName1 = "FirstBean";
        String beanName2 = "SecondBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanName1,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
            put(beanName2,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest bean1 = (ProxyTest) context.getBean(beanName1);
        ProxyTest bean2 = (ProxyTest) context.getBean(beanName2);

        assertNotSame(bean1, bean2);
    }

    @Test
    public void getBeanCallInitMessage() throws Exception {
        String beanNameFirst = "testBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanNameFirst,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest testBean = (ProxyTest) context.getBean(beanNameFirst);

        assertEquals("initialized", TestBean.initValue);
    }

    @Test
    public void getBeanInitializedByPostConstruct() throws Exception {
        String beanNameFirst = "testBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanNameFirst,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest testBean = (ProxyTest) context.getBean(beanNameFirst);

        assertEquals("initializedByPostConstruct", TestBean.postConstructValue);
    }

    @Test
    public void getBeanInitializedMethodInCorrectOrder() throws Exception {
        String beanNameFirst = "testBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanNameFirst,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest testBean = (ProxyTest) context.getBean(beanNameFirst);
        assertEquals("init2", TestBean.initValueOrdered);
    }

    @Test
    public void testBeanAnnotatedByBenchMark() throws Exception {
        String beanNameFirst = "testBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions = new HashMap<String, Map<String, Object>>() {{
            put(beanNameFirst,
                    new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
        }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        ProxyTest testBean = (ProxyTest) context.getBean(beanNameFirst);
        assertEquals("redoc", testBean.methodToBenchMark("coder"));
    }

    public static class TestBean implements ProxyTest {
        private static String initValue;
        private static String postConstructValue;
        private static String initValueOrdered;

        @BenchMark
        public String methodToBenchMark(String str) {
            return String.valueOf(new StringBuilder(str).reverse());
        }

        public void init() {
            initValue = "initialized";
            initValueOrdered = "init2";
        }

        @MyPostConstruct
        public void create() {
            postConstructValue = "initializedByPostConstruct";
            initValueOrdered = "init1";
        }
    }

    interface ProxyTest {
        String methodToBenchMark(String str);
    }

    public static class TestBeanWithConstructor implements ProxyTest {
        private final TestBean proxyTest;

        public TestBeanWithConstructor(TestBean proxyTest) {
            this.proxyTest = proxyTest;
        }

        @Override
        public String methodToBenchMark(String str) {
            return null;
        }
    }

}