package task.service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoUpload;
import com.vk.api.sdk.objects.photos.responses.WallUploadResponse;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Service
public class VkService {

    @Value("${vk.api.client_id}")
    private Integer clientId;
    @Value("${vk.api.client_secret}")
    private String clientSecret;
    @Value("${vk.api.group_id}")
    private Integer groupId;
    @Value("${vk.api.redirect_uri}")
    private String redirectUri;

    private VkApiClient vk;

    @PostConstruct
    private void initialize() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
    }

    public void postImage(File file, String code) throws ClientException, ApiException {
        UserActor actor = getUserActor(code);

        PhotoUpload serverResponse = vk.photos().
                getWallUploadServer(actor)
                .execute();
        WallUploadResponse uploadResponse = vk.upload()
                .photoWall(serverResponse.getUploadUrl(), file)
                .execute();
        List<Photo> photoList = vk.photos()
                .saveWallPhoto(actor, uploadResponse.getPhoto())
                .server(uploadResponse.getServer())
                .hash(uploadResponse.getHash())
                .execute();

        Photo photo = photoList.get(0);
        String attachId = "photo" + photo.getOwnerId() + "_" + photo.getId();
        PostResponse postResponse = vk.wall().post(actor)
                .ownerId(-groupId)
                .fromGroup(true)
                .attachments(attachId)
                .execute();
    }

    private UserActor getUserActor(String code) {
        UserAuthResponse authResponse = null;
        try {
            authResponse = vk.oauth()
                        .userAuthorizationCodeFlow(clientId, clientSecret, redirectUri, code)
                        .execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        UserActor actor = new UserActor(authResponse.getUserId(),
                authResponse.getAccessToken());

        return actor;
    }

    public String buildGetCodeUrl() {
        String url = "https://oauth.vk.com/authorize" +
                "?client_id=" + clientId +
                "&display=page" +
                "&redirect_uri=" + redirectUri +
                "&group_ids=" + groupId +
                "&scope=397316&response_type=code&v=v=5.62";
        return url;
    }
}
