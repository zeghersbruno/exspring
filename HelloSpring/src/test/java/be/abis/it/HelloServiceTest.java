package be.abis.it;

import static org.junit.Assert.assertEquals;

import be.abis.service.AbisHelloService;
import be.abis.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @Before
    public void setUp() {
        helloService = new AbisHelloService();
    }

    @Test
    public void checkWhetherPersonIsBruno() {
        assertEquals("Bruno", helloService.findperson(1).getFirstName());
    }
}
