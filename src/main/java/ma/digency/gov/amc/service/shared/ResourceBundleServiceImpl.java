package ma.digency.gov.amc.service.shared;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ResourceBundleServiceImpl implements ResourceBundleService {

    @Override
    public String getMessage(String key) {
        return resourceBundle().getString(key);
    }

    private ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("resource", getLocale());
    }

    @Override
    public Locale getLocale() {
        return new Locale("fr");
    }


}
