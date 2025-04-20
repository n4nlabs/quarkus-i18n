package io.github.n4nlabs.quarkus.i18n;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MessageSourceProducer {

    @ConfigProperty(name = "n4nlabs.quarkus-i18n.folder", defaultValue = "i18n")
    String folder;

    @ConfigProperty(name = "n4nlabs.quarkus-i18n.basename", defaultValue = "messages")
    String basename;

    @Produces
    public MessageSource messageSource() {
        return new DefaultMessageSource(folder, basename);
    }

}
