package hello.SpringCore.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingleTonBean.class);

        SingleTonBean singletonBean1 = ac.getBean(SingleTonBean.class);
        SingleTonBean singletonBean2 = ac.getBean(SingleTonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingleTonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingleTonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingleTonBean.destroy");
        }

    }
}
