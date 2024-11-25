package pages.helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        URL videoUrl = getVideoUrl();
        return generateVideoHtml(videoUrl);
    }

    private static String generateVideoHtml(URL videoUrl) {
        if (videoUrl == null) {
            return "<html><body><p>Video URL is not available.</p></body></html>";
        }

        // Используем StringBuilder для повышения производительности при конкатенации строк
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><body>")
                .append("<video width='100%' height='100%' controls autoplay>")
                .append("<source src='").append(videoUrl.toString()).append("' type='video/mp4'>")
                .append("</video></body></html>");

        return htmlBuilder.toString();
    }

    public static URL getVideoUrl() {
        String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId() + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            // Логируем ошибку и возвращаем null
            System.err.println("Invalid video URL: " + videoUrl);
            return null;
        }
    }
}
