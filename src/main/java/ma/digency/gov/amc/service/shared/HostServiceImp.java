package ma.digency.gov.amc.service.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@RequiredArgsConstructor
public class HostServiceImp implements HostService {
    private final Environment  environment;

    private String port;
    private String hostname;

    private String getPort() {
        if (port == null) port = environment.getProperty("local.server.port");
        return port;
    }

    private String getHostname() throws UnknownHostException {
        if (hostname == null) hostname = InetAddress.getLocalHost().getHostAddress();
        return hostname;
    }

    @Override
    public String getHostName() {
        String portFinal = getPort();
        try {
            return "https://" + getHostname() + (portFinal!=null? (":" +portFinal):"");
        }catch (Exception e)
        {
            return "";
        }
    }
}
