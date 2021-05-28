package org.example;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.example.dataproviders.repositories.CustomerRepository;
import org.example.dataproviders.repositories.OrderRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionOperations;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@MockBean(JavaMailSender.class)
public abstract class BaseITTest {

  @LocalServerPort
  protected Integer port;
  @Autowired
  protected OrderRepository orderRepository;
  @Autowired
  protected CustomerRepository customerRepository;
  @Autowired
  protected TransactionOperations tx;

  @Before
  public final void setUpEnv() {
    RestAssured.port = port;
  }

}
