package ru.pcs.crowdfunding.client.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pcs.crowdfunding.client.api.AuthorizationServiceClient;
import ru.pcs.crowdfunding.client.dto.AuthSignInRequest;
import ru.pcs.crowdfunding.client.dto.AuthSignInResponse;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    private final AuthorizationServiceClient authorizationServiceClient;

    @Override
    public AuthSignInResponse signIn(AuthSignInRequest authSignInRequest) {
        return authorizationServiceClient.signIn(authSignInRequest);
    }
}
