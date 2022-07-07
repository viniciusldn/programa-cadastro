package com.trybe.acc.java.programacadastro;

import static io.restassured.RestAssured.given;

import java.awt.print.Printable;

import com.trybe.acc.java.programacadastro.bean.DadosCadastro;
import com.trybe.acc.java.programacadastro.bean.DadosRetornoCadastro;
import com.trybe.acc.java.programacadastro.service.CadastroService;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.wildfly.common.format.Printf;

import io.restassured.http.ContentType;

@QuarkusTest
public class CadastroResourceTest {

  @Test
  @DisplayName("1 - Cadastra um cliente com sucesso.")
  public void testCadastroSucesso() {
    DadosCadastro data = new DadosCadastro();
    data.setNome("abc da silva");
    given().body(data).contentType(ContentType.JSON).post("/cadastro").then()
        .statusCode(200);
  }

  @Test
  @DisplayName("2 - Tenta cadastrar um cliente sem nome e recebe dados inválidos.")
  public void testCadastroSemNome() {
    DadosCadastro data = new DadosCadastro();
    data.setNome(null);
    given().body(data).contentType(ContentType.JSON).post("/cadastro").then()
        .statusCode(400);
  }

  @Test
  @DisplayName("3 - Tenta cadastrar um cliente com nome vazio e recebe internal server error.")
  public void testCadastroNomeVazio() {
    DadosCadastro data = new DadosCadastro();
    data.setNome("");
    given().body(data).contentType(ContentType.JSON).post("/cadastro").then()
        .statusCode(500);

  }

  @Test
  @DisplayName("4 - Tenta obter um cadastro com id inválido e recebe bad request.")
  public void testObterSemId() {
    given().when().get("/cadastro/a").then().statusCode(400);
  }

  @Test
  @DisplayName("5 - Tenta obter um cadastro com id que não está cadastrado e recebe bad request.")
  public void testObterIdInexistente() {
    given().when().get("/cadastro/99999").then().statusCode(400);
  }

  @Test
  @DisplayName("6 - Tenta obter um cadastro protegido e recebe status 401.")
  public void testObterIdProtegido() {
    DadosCadastro data = new DadosCadastro();
    data.setNome("abc ribeiro");
    data.setProtegido(true);
    DadosRetornoCadastro newData = given().contentType(ContentType.JSON)
        .body(data).post("/cadastro").as(DadosRetornoCadastro.class);
    String path = "/cadastro/" + newData.getIdCadastro();
    given().when().get(path).then().statusCode(401);

  }

  @Test
  @DisplayName("7 - Obtém um cadastro com sucesso.")
  public void testObterSucesso() {
    DadosCadastro data = new DadosCadastro();
    data.setNome("abc de souza");
    DadosRetornoCadastro newData = given().contentType(ContentType.JSON)
        .body(data).post("/cadastro").as(DadosRetornoCadastro.class);
    String path = "/cadastro/" + newData.getIdCadastro();
    given().when().get(path).then().statusCode(200);
  }

}
