package ${packageName}.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;


@Configuration("threadPoolExecutorConfiguration")
public class ThreadPoolConfiguration {

    private static final int processors = Runtime.getRuntime().availableProcessors();

    @Bean(name = "asyncTaskExecutor")
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setCorePoolSize((processors * 4));
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setMaxPoolSize((processors * 16));
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setAwaitTerminationSeconds(30);
        taskExecutor.setThreadNamePrefix("UserThread");
//        taskExecutor.setThreadFactory(r -> new Thread(r));
//        taskExecutor.setRejectedExecutionHandler((r, e) -> new RejectedExecutionException(""));
        return taskExecutor;
    }

    @Bean(name = "completionService")
    public CompletionService completionService() {
        ExecutorCompletionService service = new ExecutorCompletionService(taskExecutor());
        return service;
    }

}