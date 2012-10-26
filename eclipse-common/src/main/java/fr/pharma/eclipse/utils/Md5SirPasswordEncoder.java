package fr.pharma.eclipse.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Classe pour encoder le mot de passe avec le salt.<br>
 * Classe personnalisée pour prendre en compte le système de salt "maison" SIR.
 
 * @version $Revision$ $Date$
 */
public class Md5SirPasswordEncoder
    extends Md5PasswordEncoder
{

    /**
     * {@inheritDoc}
     */
    @Override
    public String encodePassword(final String rawPass,
                                 final Object salt)
    {
        final String saltedPass = rawPass
                                  + "__"
                                  + salt;

        final MessageDigest messageDigest = this.getMessageDigest();

        byte[] digest;

        try
        {
            digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
        }
        catch (final UnsupportedEncodingException e)
        {
            throw new IllegalStateException("UTF-8 not supported!");
        }

        if (this.getEncodeHashAsBase64())
        {
            return new String(Base64.encodeBase64(digest));
        }
        else
        {
            return new String(Hex.encodeHex(digest));
        }
    }
}
