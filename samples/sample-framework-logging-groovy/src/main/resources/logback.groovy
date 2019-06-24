import ch.qos.logback.contrib.jackson.JacksonJsonFormatter
import ch.qos.logback.contrib.json.classic.JsonLayout

/**
 * Groovy makes it possible to define constants, import from other files, etc...
 */

final TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"

appender("APPLICATION", ConsoleAppender) {
    layout(JsonLayout) {
        jsonFormatter(JacksonJsonFormatter) {
            prettyPrint = false
        }
        appendLineSeparator = true
        includeContextName = false
        timestampFormat = TIMESTAMP_FORMAT
    }
}
/**
 * Non-functional logging (communication, performance, etc)
 */
appender("NFL", ConsoleAppender) {
    layout(JsonLayout) {
        jsonFormatter(JacksonJsonFormatter) {
            prettyPrint = false
        }
        appendLineSeparator = true
        includeContextName = false
        includeMessage = false
        includeException = false
        includeLevel = false
        includeThreadName = false
        timestampFormat = TIMESTAMP_FORMAT
    }
}

logger("COMMUNICATION", DEBUG, ["NFL"], false)
logger("PERFORMANCE", DEBUG, ["NFL"], false)
logger("org.springplayground", DEBUG, ["APPLICATION"], false)
root(INFO, ["APPLICATION"])
