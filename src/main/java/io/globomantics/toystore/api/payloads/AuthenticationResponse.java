package io.globomantics.toystore.api.payloads;

public class AuthenticationResponse {

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    private final String token;

    public String getToken() {
        return this.token;
    }
}
