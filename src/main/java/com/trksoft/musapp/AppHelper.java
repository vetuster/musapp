package com.trksoft.musapp;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class AppHelper {

    private static final String MAVEN_BUILD_TS_PATTERN =
        "yyyy-MM-dd'T'HH:mm:ss'Z'";
    
    private static final ZoneId MAVEN_BUILD_TS_ZONE = ZoneId.of("GMT");
    
    private static final String APP_BUILS_TS_PATTERN = "dd/MM/yyyy HH:mm:ss O";
    
    private static final ZoneId APP_BUILD_TS_ZONE = ZoneId.of("Europe/Madrid");
    
    @Value("${build.timestamp}")
    private String mavenBuildTsStr;
    
    @Value("${project.version}")
    private String projectVersion;
    
    private String buildTsStr;
    
    
    public String getStartAppMsg() {
        return getAppMsg("START");
    }
    
    
    public String getEndAppMsg() {
        return getAppMsg(" END ");
    }
    
    
    private String getAppMsg(String event) {
        var sb = new StringBuilder();
        sb.append("\n\n");
        sb.append("*".repeat(80));
        sb.append("\n");
        sb.append("***   MusApp v");
        sb.append(projectVersion);
        sb.append("    >>>  ");
        sb.append(event);
        sb.append("  <<<    build: ");
        sb.append(buildTsStr);
        sb.append("   ***");
        sb.append("\n");
        sb.append("*".repeat(80));
        sb.append("\n");
        return sb.toString();
    }
    
    
    @PostConstruct
    private void setBuildTsStr() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(MAVEN_BUILD_TS_PATTERN);
            LocalDateTime localDateTime = LocalDateTime.parse(mavenBuildTsStr,
                formatter);

            ZonedDateTime zonedDateTime = localDateTime
                .atZone(MAVEN_BUILD_TS_ZONE)
                .withZoneSameInstant(APP_BUILD_TS_ZONE);

            formatter = DateTimeFormatter.ofPattern(APP_BUILS_TS_PATTERN);

            buildTsStr = zonedDateTime.format(formatter);
        } catch (DateTimeException dtex) {
        	buildTsStr = mavenBuildTsStr;
        }
    }
}
