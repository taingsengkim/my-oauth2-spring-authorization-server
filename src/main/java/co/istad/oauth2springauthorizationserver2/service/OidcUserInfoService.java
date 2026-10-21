
package co.istad.oauth2springauthorizationserver2.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

@Service
public class OidcUserInfoService {

    private final UserInfoRepository userInfoRepository =
            new UserInfoRepository();

    public OidcUserInfo loadUser(String username) {
        Map<String, Object> claims =
                userInfoRepository.findByUsername(username);

        if (claims == null) {
            // Safe fallback for a username not in the example repository.
            claims = OidcUserInfo.builder()
                    .subject(username)
                    .preferredUsername(username)
                    .build()
                    .getClaims();
        }

        return new OidcUserInfo(claims);
    }

    static class UserInfoRepository {

        private final Map<String, Map<String, Object>> userInfo =
                new HashMap<>();

        public UserInfoRepository() {
            userInfo.put("user", createUser("user"));
            userInfo.put("user1", createUser("user1"));
            userInfo.put("user2", createUser("user2"));
        }

        public Map<String, Object> findByUsername(String username) {
            return userInfo.get(username);
        }

        private static Map<String, Object> createUser(String username) {
            return OidcUserInfo.builder()
                    .subject(username)
                    .name("First Last")
                    .givenName("First")
                    .familyName("Last")
                    .preferredUsername(username)
                    .profile("https://example.com/" + username)
                    .picture("https://example.com/" + username + ".jpg")
                    .email(username + "@example.com")
                    .emailVerified(true)
                    .locale("en-US")
                    .build()
                    .getClaims();
        }
    }
}
