package ci.trabrouss.authservice.Utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class Utils {
    public static String generateLogId() {
//        String logId = LoggingAspect.getCurrentLogId();
        String logId = null;
        if (logId == null) {
            logId = UUID.randomUUID().toString();
        }
        return logId;
    }



    public static UriComponentsBuilder getUriComponentsBuilder(HttpServletRequest servletRequest) {
        return UriComponentsBuilder
                .fromHttpUrl(servletRequest.getRequestURL().toString())
                .query(servletRequest.getQueryString());
    }

    public static String getRequestMappingPath(RequestMapping annotation) {
        if (annotation != null && annotation.value().length > 0) {
            return annotation.value()[0].replace("/", "");
        }
        return "";
    }

    public static void logStart(Logger log, String requestMappingPath, String functionName, String logId) {
        log.info("[START][{}][{}][{}]", requestMappingPath, functionName, logId);
    }

    public static void logEnd(Logger log, String requestMappingPath, String functionName, String logId) {
        log.info("[END][{}][{}][{}]", requestMappingPath, functionName, logId);
    }


    public static String getRequestMappingPath(Class<?> clazz, RequestMapping methodMapping) {
        StringBuilder path = new StringBuilder();
        RequestMapping classMapping = clazz.getAnnotation(RequestMapping.class);

        if (classMapping != null && classMapping.value().length > 0) {
            path.append(classMapping.value()[0]);
        }

        if (methodMapping != null && methodMapping.value().length > 0) {
            path.append(methodMapping.value()[0]);
        }

        return path.toString();
    }

    public static String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                +"jklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    public static String toSnakeCase(String input) {
        // Remplacer les espaces et les caractères spéciaux par des underscores
        String normalized = input.replaceAll("[^a-zA-Z0-9]+", "_");

        // Convertir les majuscules en minuscules et ajouter des underscores
        StringBuilder snakeCaseString = new StringBuilder();
        for (int i = 0; i < normalized.length(); i++) {
            char c = normalized.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0 && normalized.charAt(i - 1) != '_') {
                    snakeCaseString.append("_");
                }
                snakeCaseString.append(Character.toLowerCase(c));
            } else {
                snakeCaseString.append(c);
            }
        }

        // Supprimer les underscores en début et fin de la chaîne
        return snakeCaseString.toString().replaceAll("^_|_$", "");
    }

    /**
     * Remove accents from given string
     * @param input
     * @return String
     */
    public static String removeAccents(String input) {
        return input.replaceAll("[ÀÁÂÃÄÅ]", "A")
                .replaceAll("[àáâãäå]", "a")
                .replaceAll("[ÈÉÊË]", "E")
                .replaceAll("[èéêë]", "e")
                .replaceAll("[ÌÍÎÏ]", "I")
                .replaceAll("[ìíîï]", "i")
                .replaceAll("[ÒÓÔÕÖ]", "O")
                .replaceAll("[òóôõö]", "o")
                .replaceAll("[ÙÚÛÜ]", "U")
                .replaceAll("[ùúûü]", "u")
                .replaceAll("[ç]", "c")
                .replaceAll("[Ÿ]", "Y")
                .replaceAll("[ÿ]", "y");
    }

    /**
     * Remove accents from given string
     * @param input
     * @return String
     */
    public static String removeAccentsAndSpace(String input) {
        return removeAccents(input)
                .replaceAll("\\s", "_")
                .toLowerCase();
    }

    public static Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String getDayNameInFrench(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", Locale.FRENCH);
        return date.format(formatter);
    }

    public static String toFrenchDate(LocalDateTime dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("EEEE, dd MMMM yyyy 'à' HH'h'mm", Locale.FRENCH);

        return dateTime.format(formatter);
    }
}
