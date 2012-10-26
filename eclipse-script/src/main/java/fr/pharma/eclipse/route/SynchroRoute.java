package fr.pharma.eclipse.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

/**
 * Route camel en charge de gérer la synchronisation avec le fichier d'export sigrec.
 
 * @version $Revision$ $Date$
 */
public class SynchroRoute
    extends RouteBuilder
{

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure()
    {
        final DataFormat dataFormat = new JaxbDataFormat("fr.pharma.eclipse.domain.model.sigrec");
        this.from("direct:start")

        .unmarshal(dataFormat) // transformation en grappe d'objet.
                .beanRef("resetProcessor",
                         "process")
                // synchro des promoteurs dans les tables sigrec.
                .beanRef("promoteurProcessor",
                         "process")
                // synchro final des essais dans final les tables sigrec.
                .beanRef("essaiProcessor",
                         "process");
    }
}
