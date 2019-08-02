package net.mreunionlabs.tapgradle.generate.util

import org.apache.commons.codec.binary.Base64

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException

/**
 * Create a hmac pass phrase. You can use any random string for the pass phrase.
 * This class provide more convinient and security in generating such string
 *
 * @author abangkis
 */
class HMACGenerator {

    constructor() {}

    constructor(key: String, salt: String) {
        try {
            println("HMAC Key: " + computeHmac(key, salt))
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    /**
     * Computes RFC 2104-compliant HMAC signature.
     *
     * param data
     * The data to be signed.
     * @param key
     * The signing key.
     * @return
     * The Base64-encoded RFC 2104-compliant HMAC signature.
     * @throws
     * java.security.SignatureException when signature generation fails
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

    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class, IllegalStateException::class, UnsupportedEncodingException::class)
    fun computeHmac(baseString: String, key: String): String {

        val mac = Mac.getInstance(HMAC_SHA1_ALGORITHM)
        val secret = SecretKeySpec(key.toByteArray(), mac.algorithm)
        mac.init(secret)
        val digest = mac.doFinal(baseString.toByteArray())
        return Base64.encodeBase64String(digest)
    }

    companion object {

        private val HMAC_SHA1_ALGORITHM = "HmacSHA1"
    }
}
