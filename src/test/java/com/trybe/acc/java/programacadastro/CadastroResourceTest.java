package com.trybe.acc.java.programacadastro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CadastroResourceTest {

  @Test
  @DisplayName("1 - Cadastra um cliente com sucesso.")
  public void testCadastroSucesso() {
    Assertions.fail("não implementado");
  }

  @Test
  @DisplayName("2 - Tenta cadastrar um cliente sem nome e recebe dados inválidos.")
  public void testCadastroSemNome() {
    Assertions.fail("não implementado");
  }

  @Test
  @DisplayName("3 - Tenta cadastrar um cliente com nome vazio e recebe internal server error.")
  public void testCadastroNomeVazio() {
    Assertions.fail("não implementado");

  }

  @Test
  @DisplayName("4 - Tenta obter um cadastro com id inválido e recebe bad request.")
  public void testObterSemId() {
    Assertions.fail("não implementado");
  }

  @Test
  @DisplayName("5 - Tenta obter um cadastro com id que não está cadastrado e recebe bad request.")
  public void testObterIdInexistente() {
    Assertions.fail("não implementado");
  }

  @Test
  @DisplayName("6 - Tenta obter um cadastro protegido e recebe status 403.")
  public void testObterIdProtegido() {
    Assertions.fail("não implementado");

  }

  @Test
  @DisplayName("7 - Obtém um cadastro com sucesso.")
  public void testObterSucesso() {
    Assertions.fail("não implementado");
  }

}
