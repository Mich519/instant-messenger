package mj.project.networking.data;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.Serializable;

@Getter
@Setter
public class NetworkPropertiesStorage implements Serializable {

    @Inject
    public NetworkPropertiesStorage() {
    }

    private String myNickName = "John";
    private int myPort = 8080;
    private String targetIp = "127.0.0.1";
    private int targetPort = 8080;
}
