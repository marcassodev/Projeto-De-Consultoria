/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultoriamaisvida.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Paulo
 * Criando classe para validar se um cliente.
 */
public class ValidarCliente {
    
     /**
     * Verifica se o cliente é maior de idade.
     * @param dataNascimento em formato dd/MM/yyyy
     * @return true se tiver 18 anos ou mais
     */
    public static boolean isMaiorDeIdade(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nascimento = LocalDate.parse(dataNascimento, formatter);
        LocalDate hoje = LocalDate.now();
        return Period.between(nascimento, hoje).getYears() >= 18;
    }
    
}
