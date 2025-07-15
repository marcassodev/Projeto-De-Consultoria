/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package consultoriamaisvida.services;

import consultoriamaisvida.model.Cliente;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Paulo
 * Classe para criação de testes automatizados para determinados campos de texto.
 */
public class ClienteServiceTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    //criando um paciente correto para exemplo.
    private Cliente clienteValido() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Silva");
        cliente.setCpf("12345678900");
        cliente.setRg("1234567890");
        cliente.setTelefone("(11)1234-5678");
        cliente.setData(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1990"));
        return cliente;
    }
    
     //agora, testar valores invalidos para os campos que devem ter seus valores validos, se der true, o teste correu bem como esperado
    @Test
    public void testPacienteValidoNaoLancaExcecao() throws Exception {
        Cliente cliente = clienteValido();
        // Não espera exceção, se lançar o teste falha
        ClienteService.validar(cliente);
    }
    // dá como errado, pois, se espera resultado invalido para o teste (ex. 123@! vai dar como certo)
    @Test
    public void testDeNomeInvalido() throws Exception {
        Cliente cliente = clienteValido();
        cliente.setNome("Pedro");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Nome inválido");

        ClienteService.validar(cliente);
    }
    // ocorre erro pois se espera um valor invalido, mas, inseri um valor valido
    @Test
    public void testDeCpfInvalido() throws Exception {
        Cliente cliente = clienteValido();
        cliente.setCpf("12345678900");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("CPF inválido");

        ClienteService.validar(cliente);
    }
    // dá como certo, pois, é inserido um valor invalido para o campo CPF
    @Test
    public void testDeTelefoneInvalido() throws Exception {
        Cliente cliente = clienteValido();
        cliente.setTelefone("11999999991312aaaaa");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Telefone inválido");

        ClienteService.validar(cliente);
    }
    
}
