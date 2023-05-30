package ma.digency.gov.amc.utils.exception;

import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new MinistryOfCultureBusinessException(
                    MinistryOfCultureMessage.AMC_INVALID_PARAMETER);
        } else {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }

}
