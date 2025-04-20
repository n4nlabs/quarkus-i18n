# quarkus-i18n

A **lightweight** Quarkus extension for internationalization, providing:

- A CDI `MessageSource` to load UTF-8 encoded resource bundles from a configurable folder and basename.
- Automatic injection via MicroProfile Config properties.
- Optional Bean Validation interpolator (separate module: `quarkus-i18n-validation`).

---

## Features

- **Configurable** resource folder and basename via `application.properties`:

  ```properties
  n4nlabs.i18n.folder=i18n          # default: i18n
  n4nlabs.i18n.basename=messages    # default: messages
  ```

- **UTF-8** support out of the box (`UTF8Control`).
- **CDI Producer** for `MessageSource`:

  ```java
  @Inject
  MessageSource msg;
  String greeting = msg.getMessage("greeting", new Object[]{"World"}, locale);
  ```

---

## Installation

Add the repository and dependency to your Quarkus application _before_ publishing to Maven Central:

```xml
<repositories>
  <repository>
    <id>github-quarkus-i18n</id>
    <url>https://maven.pkg.github.com/n4nlabs/quarkus-i18n</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>io.github.n4nlabs</groupId>
    <artifactId>quarkus-i18n</artifactId>
    <version>0.0.1</version>
  </dependency>
</dependencies>
```

After publication to a public repository, the `<repositories>` block is no longer needed.

---

## Configuration

In your `src/main/resources/application.properties`:

```properties
# resource folder (under resources/)
n4nlabs.i18n.folder=i18n

# resource basename (file names without .properties)
n4nlabs.i18n.basename=messages
```

Create your bundles:

```
src/main/resources/i18n/messages.properties
src/main/resources/i18n/messages_pt_BR.properties
```

---

## Usage Example

### REST endpoint

```java
@Path("/i18n")
@ApplicationScoped
public class I18nTestController {

    @Inject
    MessageSource msg;

    @GET
    @Path("/greeting")
    @Produces(MediaType.TEXT_PLAIN)
    public String greeting(@Context HttpHeaders headers) {
        Locale locale = headers.getLanguage() != null
                        ? headers.getLanguage()
                        : Locale.getDefault();
        return msg.getMessage("greeting", new Object[]{"Quarkus"}, locale);
    }
}
```

**Properties files**:
```properties
# default (messages.properties)
greeting=Hello, {0}!

# Brazilian Portuguese (messages_pt_BR.properties)
greeting=Olá, {0}!
```

---

## Bean Validation Integration

We also provide Bean Validation message interpolation in the separate module **quarkus-i18n-validation**.  
See: https://github.com/n4nlabs/quarkus-i18n-validation

---

## License

Licensed under the Apache 2.0 License. See [LICENSE](LICENSE).
