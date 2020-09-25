package com.inventory.product.config;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {
	
	Logger logger = LoggerFactory.getLogger(CacheConfiguration.class);
	
	public static final String CACHE_VALUES = "price";

	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(CACHE_VALUES);
		return cacheManager;
	}

	@CacheEvict(allEntries = true, value = {CACHE_VALUES})
	@Scheduled(fixedDelay = 30 * 60 * 1000)
	public void reportCacheEvict() {
		logger.info("Flushing Cache " + LocalDateTime.now());
	}
}
