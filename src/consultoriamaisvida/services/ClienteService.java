/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultoriamaisvida.services;

import consultoriamaisvida.model.Cliente;

/**
 *
 * @author Paulo
 * Criando classe de servico de um cliente.
 */ 

public class ClienteService {
    
    public static void validar(Cliente cliente) {
        if (!validaNome(cliente.getNome())) {
            throw new IllegalArgumentException("Nome inválido");
        }
        if (!validaCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }
        if (!validaRg(cliente.getRg())) {
            throw new IllegalArgumentException("Rg inválido");
        }
        if (cliente.getData() == null) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }
        if (!validaTelefone(cliente.getTelefone())) {
            throw new IllegalArgumentException("Telefone inválido");
        }
    }

    private static boolean validaNome(String nome) {
        // Obrigatório, máximo 55 caracteres, apenas letras e espaços
        return nome != null && nome.length() <= 55 && nome.matches("[A-Za-zÀ-ÿ ]+");
    }

    private static boolean validaCpf(String cpf) {
        // Obrigatório, exatamente 11 dígitos numéricos
        return cpf != null && cpf.matches("\\d{11}");
    }
    
    private static boolean validaRg(String rg) {
        // Obrigatório, com 10 dígitos numéricos
        return rg != null && rg.matches("\\d{10}");
    }


    private static boolean validaTelefone(String telefone) {
        // Obrigatório, formato (xx)xxxx-xxxx e máximo 15 caracteres
        return telefone != null && telefone.length() <= 15 && telefone.matches("\\(\\d{2}\\)\\d{4}-\\d{4}");
    }
    
}
