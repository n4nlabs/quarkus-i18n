package io.github.n4nlabs.quarkus.i18n;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.Config;

@ApplicationScoped
public class MessageSourceProducer {

    @Produces
    public MessageSource messageSource() {
        Config config = ConfigProvider.getConfig();
        String folder = config.getOptionalValue("n4nlabs.i18n.folder", String.class).orElse("i18n");
        String basename = config.getOptionalValue("n4nlabs.i18n.basename", String.class).orElse("messages");

        System.out.println(">> MessageSourceProducer folder=" + folder + " basename=" + basename);
        return new DefaultMessageSource(folder, basename);
    }

}
