package fr.pharma.eclipse.factory.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Fabrique de flux I/O.
 
 * @version $Revision$ $Date$
 */
public class IOStreamsFactory
{
    /**
     * Fabrique de {@link FileInputStream} à partir d'un fichier.
     * @param file Fichier.
     * @return InputStream.
     * @throws FileNotFoundException Exception levée si le fichier n'est pas accessible.
     */
    public InputStream getInitializedInputStream(final File file)
        throws FileNotFoundException
    {
        return new FileInputStream(file);
    }

    /**
     * Fabrique de {@link ByteArrayOutputStream}.
     * @return Un nouvel objet ByteArrayOutputStream.
     */
    public ByteArrayOutputStream getInitializedByteArrayOutStream()
    {
        return new ByteArrayOutputStream();
    }

}
