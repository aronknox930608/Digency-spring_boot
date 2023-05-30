package ma.digency.gov.amc.configuration;

public interface SecurityConstants {

    long EXPIRATION_TIME = 10 * 24 * 3600 * 1000;
    String SECRET = "%p0%k53_4Vc%A#";
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    long EXPIRATION_TIME_OTP_CODE = 5 * 60 * 1000;

}
