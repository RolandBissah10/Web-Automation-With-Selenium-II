package org.example.listener;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AllureLogListener implements TestLifecycleListener {

    private static final Logger logger = Logger.getLogger("AllureTestLogger");
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            FileHandler fileHandler = new FileHandler("xyz-bank-tests.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);

            logger.info("╔══════════════════════════════════════════╗");
            logger.info("  XYZ BANK - TEST SESSION STARTED          ");
            logger.info("  " + LocalDateTime.now().format(FORMATTER));
            logger.info("╚══════════════════════════════════════════╝");

        } catch (IOException e) {
            System.err.println("Failed to initialize file logger: " + e.getMessage());
        }
    }

    @Override
    public void beforeTestSchedule(TestResult result) {
        logger.info("---------------------------------------------------");
        logger.info("TEST SCHEDULED : " + result.getName());
    }

    @Override
    public void beforeTestStart(TestResult result) {
        logger.info("TEST STARTED   : " + result.getName());
        logger.info("Time           : " + LocalDateTime.now().format(FORMATTER));
    }

    @Override
    public void afterTestStop(TestResult result) {
        String status = result.getStatus() != null ? result.getStatus().name() : "UNKNOWN";
        String icon   = getStatusIcon(status);

        logger.info("TEST FINISHED  : " + result.getName());
        logger.info("Status         : " + icon + " " + status);
        logger.info("Time           : " + LocalDateTime.now().format(FORMATTER));

        if (("FAILED".equals(status) || "BROKEN".equals(status))
                && result.getStatusDetails() != null) {
            logger.warning("Failure Message: " + result.getStatusDetails().getMessage());
        }

        logger.info("---------------------------------------------------\n");
    }

    private String getStatusIcon(String status) {
        switch (status) {
            case "PASSED":  return "✅";
            case "FAILED":  return "❌";
            case "BROKEN":  return "🔴";
            case "SKIPPED": return "⏭";
            default:        return "❓";
        }
    }
}