package net.mreunionlabs.tapgradle.generate.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Create a hmac pass phrase. You can use any random string for the pass phrase. 
 * This class provide more convinient and security in generating such string 
 * 
 * @author abangkis
 *
 */
public class HMACGenerator {

	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    
	public HMACGenerator(String key, String salt) {
		try {
			System.out.println("HMAC Key: " + computeHmac(key, salt));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}
	
    /**
     * Computes RFC 2104-compliant HMAC signature.
     * 
     * param data
     *     The data to be signed.
     * @param key
     *     The signing key.
     * @return
     *     The Base64-encoded RFC 2104-compliant HMAC signature.
     * @throws
     *     java.security.SignatureException when signature generation fails
     */
//    public static String calculateRFC2104HMAC(String data, String key)
//        throws java.security.SignatureException
//    {
//        String result;
//        try {
//            // get an hmac_sha1 key from the raw key bytes
//            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), 
//                                                         HMAC_SHA1_ALGORITHM);
//            
//            // get an hmac_sha1 Mac instance and initialize with the signing key
//            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
//            mac.init(signingKey);
//            
//            // compute the hmac on input data bytes
//            byte[] rawHmac = mac.doFinal(data.getBytes());
//            
//            // base64-encode the hmac
//            result = Encoding.EncodeBase64(rawHmac);
//        } 
//        catch (Exception e) {
//            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
//        }
//        return result;
//    }
    
    public String computeHmac(String baseString, String key) 
    	throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, 
    	UnsupportedEncodingException {
    	
    	    Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
    	    SecretKeySpec secret = new SecretKeySpec(key.getBytes(), mac.getAlgorithm());
    	    mac.init(secret);
    	    byte[] digest = mac.doFinal(baseString.getBytes());
    	    return Base64.encodeBase64String(digest);
    }
}
