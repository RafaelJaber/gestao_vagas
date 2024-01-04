package br.com.rafaeljaber.gestao_vagas.exceptions;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException() {
        super("Profile not found.");
    }
}
