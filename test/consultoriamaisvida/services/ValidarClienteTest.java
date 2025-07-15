/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package consultoriamaisvida.services;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paulo
 * Criando classe para testar se um cliente tem mais de 18 anos ou não.
 */

public class ValidarClienteTest {
    
    @Test
    public void clienteMaiorDeIdade() {
        String data = "15/07/2000"; // mais de 18 anos
        assertTrue(ValidarCliente.isMaiorDeIdade(data));
    }

    @Test
    public void clienteMenorDeIdade() {
        String data = "15/07/2012"; // menos de 18 anos
        assertFalse(ValidarCliente.isMaiorDeIdade(data));
    }

    @Test
    public void clienteCom18AnosExatos() {
        LocalDate hoje = LocalDate.now();
        String data = hoje.minusYears(18).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertTrue(ValidarCliente.isMaiorDeIdade(data));
    }
    
}
