package com.test.test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TruncateDatabaseService truncateDatabaseService;

    @Before
    public void setup() {
        MockMvc mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @After
    public void treadDown(){
        truncateDatabaseService.truncate();
    }

    @Test
    @Sql("/sqlfixtures/insert_user_test-data.sql")
    public void should_can_get_user_detail_by_user_1() {
        MockMvcRequestSpecification requestSpecification = RestAssuredMockMvc
                .given()
                .header("Accept", ContentType.JSON.withCharset("UTF-8"))
                .header("Content-Type", ContentType.JSON.withCharset("UTF-8"));

        requestSpecification
                .when()
                .get("/users/1")
                .then()
                .body("username",equalTo("dabai"));

    }

    @Test
    @Sql("/sqlfixtures/insert_user_test-data.sql")
    public void should_can_get_user_detail_by_user_2() {
        MockMvcRequestSpecification requestSpecification = RestAssuredMockMvc
                .given()
                .header("Accept", ContentType.JSON.withCharset("UTF-8"))
                .header("Content-Type", ContentType.JSON.withCharset("UTF-8"));

        requestSpecification
                .when()
                .get("/users/2")
                .then()
                .body("username",equalTo("xiaobai"));

    }

}
