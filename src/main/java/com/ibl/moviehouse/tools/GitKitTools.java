package com.ibl.moviehouse.tools;

import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitClientException;
import com.google.identitytoolkit.GitkitUser;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GitKitTools {

    public GitkitUser getGitKitUser(HttpServletRequest request) {

        GitkitUser user = null;
        try {
            GitkitClient gitkitClient = GitkitClient.newBuilder()
                    .setGoogleClientId("50123732426-86gl9b40apnm8nl2moft5tf7qqhg71sk.apps.googleusercontent.com")
                    .setProjectId("moviehouse-148209")
                    .setServiceAccountEmail("")
                    .setCookieName("gtoken")
                    .setWidgetUrl("https://moviehouse-148209.appspot.com/gitkit")
                    .setKeyStream(new ClassPathResource("moviehouse-e9df73d185a0.p12").getInputStream()).build();

            //GitkitClient gitkitClient = GitkitClient.createFromJson("resources/gitkit-server-config.json");
            user = gitkitClient.validateTokenInRequest(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (GitkitClientException e) {
            e.printStackTrace();
        }
        return user;
    }
}
