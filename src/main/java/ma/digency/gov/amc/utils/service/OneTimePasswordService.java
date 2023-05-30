package ma.digency.gov.amc.utils.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OneTimePasswordService {

    private static final Integer EXPIRE_MINS = 1;

    private final LoadingCache<String, String> otpCache;

    public OneTimePasswordService() {
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "";
                    }
                });
    }

    public String generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, String.format("%06d", otp));
        return String.valueOf(otp);
    }

    public void checkGenerateOtp(String key, String codeOtp) {
        try {
            if (!otpCache.get(key).equals(codeOtp)) {
                throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_CODE_OTP);
            }
            clearOTP(key);
        } catch (Exception e) {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_CODE_OTP);
        }
    }

    protected void clearOTP(String key) {
        otpCache.invalidate(key);
    }

}
