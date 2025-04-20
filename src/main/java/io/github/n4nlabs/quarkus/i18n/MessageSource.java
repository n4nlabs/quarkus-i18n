package io.github.n4nlabs.quarkus.i18n;

import java.util.Locale;

public interface MessageSource {

    String getMessage(String code, Object[] args, Locale locale);

    String getMessage(String code, Locale locale);

}
