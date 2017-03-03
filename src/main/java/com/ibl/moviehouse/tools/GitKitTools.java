package com.ibl.moviehouse.tools;

import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitClientException;
import com.google.identitytoolkit.GitkitUser;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Filip on 18.11.2016.
 */
public class GitKitTools {

    public Logger logger = Logger.getLogger("GitKitTools");

    public GitkitUser getGitKitUser(HttpServletRequest request) {

        logger.log(Level.INFO, ">>> getting gitKit user");
        GitkitUser user = null;
        try {
            GitkitClient gitkitClient = GitkitClient.newBuilder()
                    .setGoogleClientId("50123732426-hc2sdgf6l934e6lejpokesc9sj8nu9s2.apps.googleusercontent.com")
                    .setProjectId("moviehouse-148209")
                    .setServiceAccountEmail("")
                    .setCookieName("gtoken")
                    .setWidgetUrl("http://localhost:8080/gitkit")
                    .setKeyStream(new ClassPathResource("moviehouse-1f535961fa87.p12").getInputStream()).build();

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
