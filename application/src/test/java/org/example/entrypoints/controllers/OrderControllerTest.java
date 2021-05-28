package org.example.entrypoints.controllers;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import org.example.BaseITTest;
import org.example.entities.Order;
import org.example.entities.enums.OrderStatus;
import org.example.usecases.dto.OrderCreateDto;
import org.example.usecases.dto.OrderGetDto;
import org.junit.Test;

public class OrderControllerTest extends BaseITTest {

  @Test
  public void shouldSaveOrderToDB_whenPostOrder() {
    OrderCreateDto body = new OrderCreateDto().setName("name");

    OrderGetDto result = given().body(body).contentType(JSON)
        .when().post("/orders")
        .then().statusCode(200).extract().as(OrderGetDto.class);

    assertThat(result).extracting(OrderGetDto::getId).isNotNull();
    tx.execute(s -> {
      assertThat(orderRepository.getOne(result.getId()))
          .extracting(Order::getName, Order::getStatus, Order::getCustomer)
          .containsExactly(body.getName(), OrderStatus.CREATED, null);
      return null;
    });

  }
}