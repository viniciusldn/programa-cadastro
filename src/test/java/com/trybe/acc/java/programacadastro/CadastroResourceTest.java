package com.trybe.acc.java.programacadastro;

import static io.restassured.RestAssured.given;

import com.trybe.acc.java.programacadastro.bean.DadosCadastro;
import com.trybe.acc.java.programacadastro.bean.DadosRetornoCadastro;
import com.trybe.acc.java.programacadastro.service.CadastroService;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class CadastroResourceTest {

  @Test
  @DisplayName("1 - Cadastra um cliente com sucesso.")
  public void testCadastroSucesso() {
    DadosCadastro data = new DadosCadastro();
    data.setNome("abc da silva");
    RestAssured.given().body(data).contentType(ContentType.JSON)
        .post("/cadastro").then().statusCode(201);
  }

  @Test
  @DisplayName("2 - Tenta cadastrar um cliente sem nome e recebe dados inválidos.")
  public void testCadastroSemNome() {
    DadosCadastro data = new DadosCadastro();
    data.setNome(null);
    RestAssured.given().body(data).contentType(ContentType.JSON)
        .post("/cadastro").then().statusCode(400);
  }

  @Test
  @DisplayName("3 - Tenta cadastrar um cliente com nome vazio e recebe internal server error.")
  public void testCadastroNomeVazio() {
    DadosCadastro data = new DadosCadastro();
    data.setNome("");
    RestAssured.given().body(data).contentType(ContentType.JSON)
        .post("/cadastro").then().statusCode(500);

  }

  @Test
  @DisplayName("4 - Tenta obter um cadastro com id inválido e recebe bad request.")
  public void testObterSemId() {
    given().when().get("/restaurante/a").then().statusCode(400);
  }

  @Test
  @DisplayName("5 - Tenta obter um cadastro com id que não está cadastrado e recebe bad request.")
  public void testObterIdInexistente() {
    given().when().get("/restaurante/99999").then().statusCode(400);
  }

  @Test
  @DisplayName("6 - Tenta obter um cadastro protegido e recebe status 403.")
  public void testObterIdProtegido() {
    CadastroService cadastro = new CadastroService();
    DadosRetornoCadastro newData = cadastro.cadastrar("a", null, true);
    given().when().get("/restaurante/" + newData.getIdCadastro()).then()
        .statusCode(403);

  }

  @Test
  @DisplayName("7 - Obtém um cadastro com sucesso.")
  public void testObterSucesso() {
    CadastroService cadastro = new CadastroService();
    DadosRetornoCadastro newData = cadastro.cadastrar("a", null, false);
    given().when().get("/restaurante/" + newData.getIdCadastro()).then()
        .statusCode(200);
  }

}
