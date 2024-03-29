package com.example.openschooltask3.service;

import com.example.openschooltask3.dto.OrderDTO;
import com.example.openschooltask3.dto.UserDTO;
import com.example.openschooltask3.entity.User;
import com.example.openschooltask3.model.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserServiceIntegrationTest {

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    void whenCreateUserThenReturnUser() {
        List<User> users = new ArrayList<>();

        User user = userService.create(new UserDTO("testName", "testEmail"));
        assertEquals("testName", user.getName());
        assertEquals("testEmail", user.getEmail());

        users.add(user);

        User user2 = userService.create(new UserDTO("testName2", "testEmail2"));
        assertEquals("testName2", user2.getName());
        assertEquals("testEmail2", user2.getEmail());

        users.add(user2);

        User user3 = userService.create(new UserDTO("testName3", "testEmail3"));
        assertEquals("testName3", user3.getName());
        assertEquals("testEmail3", user3.getEmail());

        users.add(user3);

    }

    @Test
    @Order(2)
    void  whenUpdateUserThenReturnUser() {
        User user = userService.update(User.builder()
                .id(1L)
                .name("updatedName")
                .email("updatedEmail")
                .build());

        assertNotNull(user);
        assertEquals("updatedName", user.getName());
        assertEquals("updatedEmail", user.getEmail());
    }

    @Test
    @Order(3)
    void whenGetUserThenReturnUser() {
        assertThat(userService.get(1L)).isPresent();

    }

    @Test
    @Order(4)
    void whenGetAllUsersThenReturnUsers() {
        List<User> users = userService.getAll();

        assertNotNull(users);
        assertThat(users.size() > 0);

    }

    @Test
    @Order(5)
    void whenDeleteUserThenNotGetUser() {
        userService.delete(1L);

        assertThat(userService.get(1L)).isNotPresent();
    }

    @Test
    @Order(6)
    void whenGetUserNoIdThenNotPresent() {
        assertThat(userService.get(100L)).isNotPresent();

    }

    @Test
    @Order(7)
    void whenGetUserNoIdThenThrowEx() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/user/100")
                .then()
                .statusCode(404);

    }

    @Test
    @Order(8)
    @Transactional
    void whenCreateOrderThen() {
        orderService.create(new OrderDTO("orderTest", Status.OPEN, 6L));
        List<com.example.openschooltask3.entity.Order> orders = orderService.getAllByUser(6L);

        assertEquals("orderTest", orders.get(0).getDescription());

    }

    @Test
    @Order(9)
    void whenGetAllOrdersThenReturnOrders() {
        List<com.example.openschooltask3.entity.Order> orders = orderService.getAll();

        assertNotNull(orders);
        assertThat(orders.size() > 0);

    }

    @Test
    @Order(10)
    void whenGetAllByUserOrdersThenReturnOrders() {
        List<com.example.openschooltask3.entity.Order> orders = orderService.getAllByUser(2L);

        assertNotNull(orders);
        assertThat(orders.size() > 0);

    }

    @Test
    @Order(11)
    void whenUpdateOrderThenUpdated() {
        orderService.update(6L, new OrderDTO("updateOrderTest", Status.CLOSE, 2L));

        assertEquals("updateOrderTest", orderService.get(6L).get().getDescription());

    }

    @Test
    @Order(12)
    void whenGetOrderByIdThenReturnOrder() {
        com.example.openschooltask3.entity.Order order = orderService.get(6L).get();

        assertEquals("updateOrderTest", order.getDescription());

    }

    @Test
    @Order(12)
    void whenDeleteOrderThenReturnOrderIsEmpty() {
        orderService.delete(6L);

        assertThat(orderService.get(6L)).isEmpty();

    }

    @Test
    @Order(13)
    void whenGetOrderNoIdThenThrowEx() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/order/100")
                .then()
                .statusCode(404);

    }
}