package com.sjtubus.controller;

import com.sjtubus.model.response.MyAccessTokenResponse;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.*;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Allen
 * @date 2018/7/12 9:18
 */

@Controller
@RequestMapping(value = "/auth")
public class OAuthController {
    private String BASE_URL = "https://jaccount.sjtu.edu.cn/oauth2/";
    private String REDIRECT_URL = "http://localhost:8080/auth/redirect";
    private String CLIENT_ID = "3FWoFzRrMlxeU750XO5W";
    @RequestMapping(value = "/jaccount",method = RequestMethod.GET)
    public void jaccountOauth(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException,IOException{
        OAuthClientRequest oauthRequest = OAuthClientRequest
                .authorizationLocation(BASE_URL+"authorize")
                .setClientId(CLIENT_ID)
                .setResponseType("code")
                .setRedirectURI(REDIRECT_URL)
                .buildQueryMessage();
        response.sendRedirect(oauthRequest.getLocationUri());
    }

    @RequestMapping(value = "/redirect")
    @ResponseBody
    public String getCodeAndAccessToken(HttpServletRequest request, HttpServletResponse response)
            throws OAuthProblemException,OAuthSystemException{
        OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
        String code = oar.getCode();
        OAuthClientRequest oauthRequest = OAuthClientRequest
                .tokenLocation(BASE_URL+"token")
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(CLIENT_ID)
                .setClientSecret("92806BB5F16420B50113BC1F928C8516641C1F754CEF31D5")
                .setRedirectURI(REDIRECT_URL)
                .setCode(code)
                .buildQueryMessage();

        //create OAuth client that uses custom http client under the hood
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        MyAccessTokenResponse oAuthResponse = oAuthClient.accessToken(oauthRequest, MyAccessTokenResponse.class);
        String accessToken = oAuthResponse.getAccessToken();
        long expiresIn = oAuthResponse.getExpiresIn();

        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest("https://api.sjtu.edu.cn/v1/me/profile")
                .setAccessToken(accessToken).buildQueryMessage();
        OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
        return resourceResponse.getBody();
    }
}
