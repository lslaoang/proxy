package org.sb.proxy.service;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.sb.proxy.util.RequestUtil.getUsernameFromRequest;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class AuthorizationService {

    private static final String GRAPH_ENDPOINT = "https://graph.microsoft.com/v1.0/";
//
//    @Value("${app.group}")
//    List<String> allowedGroups;

    private final String allowedGroup = "67875247-77c7-4867-9ef5-9b6775db91f9";

    @Autowired
    WebClient webClient;

    public void authorize() throws URISyntaxException {
        String username = getUsernameFromRequest();

//        String groupEncoded = allowedGroups.get(0).replace(" ", "%20");
        String groupEncoded = allowedGroup.replace(" ", "%20");
        String memberOfEndpoint = GRAPH_ENDPOINT
                + "users/" + username
                + "/memberOf/microsoft.graph.group?$count=true"
                + "&$filter=id%20eq%20'"
                + groupEncoded + "'";

        URI uri = new URI(memberOfEndpoint);

        String graphResponse = webClient
                .get()
                .uri(uri)
                .attributes(clientRegistrationId("azure"))
                .header("ConsistencyLevel", "eventual")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("This is the response: " + graphResponse);


//        JSONObject jsonObject = new JSONObject(Integer.parseInt(graphResponse));
//        Integer groupCount = (Integer) jsonObject.get("@odata.count");
//        if(groupCount < 1) {
//            System.out.println(groupCount + " is not a valid group");
//        }
    }

}
