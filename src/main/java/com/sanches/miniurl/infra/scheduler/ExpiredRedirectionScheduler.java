package com.sanches.miniurl.infra.scheduler;

import com.sanches.miniurl.domain.repository.RedirectionRepository;
import com.sanches.miniurl.domain.service.RedirectionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ExpiredRedirectionScheduler {
    private static final Log log = LogFactory.getLog(ExpiredRedirectionScheduler.class);
    private final RedirectionService redirectionService;

    public ExpiredRedirectionScheduler(RedirectionRepository redirectionRepository) {
        this.redirectionService = new RedirectionService(redirectionRepository);
    }

    @Scheduled(fixedDelay = 1L, timeUnit = TimeUnit.HOURS)
    public void scheduleExpiredRedirection() {
        log.info("Starting expired redirection scheduler");
        try {
            redirectionService.removeExpiredRedirections();
        } catch (Exception e) {
            log.error("Error while removing expired redirections", e);
        }
        log.info("Finished expired redirection scheduler");
    }
}
