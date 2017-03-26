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
        PropertiesHandler handler = PropertiesHandler.getInstance();
        GitkitUser user = null;
        try {
            GitkitClient gitkitClient = GitkitClient.newBuilder()
                    .setGoogleClientId(handler.getClientId())
                    .setProjectId(handler.getProjectId())
                    .setServiceAccountEmail(handler.getServiceAccountEmail())
                    .setCookieName(handler.getCookieName())
                    .setWidgetUrl(handler.getWidgetUrl())
                    .setKeyStream(new ClassPathResource(handler.getKeyFile()).getInputStream()).build();

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
