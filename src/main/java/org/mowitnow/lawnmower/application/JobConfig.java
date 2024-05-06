package org.mowitnow.lawnmower.application;

import lombok.RequiredArgsConstructor;
import org.mowitnow.lawnmower.domain.entity.Lawn;
import org.mowitnow.lawnmower.domain.entity.Mower;
import org.mowitnow.lawnmower.domain.servicee.MowItNowService;
import org.mowitnow.lawnmower.infrastructuree.InputAdapter;
import org.mowitnow.lawnmower.infrastructuree.OutputAdapter;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class JobConfig {
    @Value("${management.static.constants.job_name}")
    private String jobName;
    @Value("${management.static.constants.step_name}")
    private String stepName;
    @Value("${management.static.constants.file.input-directory}")
    private String inputDirectoryPath;
    @Value("${management.static.constants.file.output-directory}")
    private String outputDirectoryPath;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobRegistry jobRegistry;

    @Bean
    public Job mowItNowJob(Step mowItNowStep) {
        try {
            return jobRegistry.getJob(jobName);
        } catch (NoSuchJobException e) {
            return jobBuilderFactory.get(jobName)
                    .flow(mowItNowStep)
                    .end()
                    .build();
        }
    }

    @Bean
    public Step mowItNowStep(ItemProcessor<Lawn, List<Mower>> processor) {
        return stepBuilderFactory.get(stepName)
                .<Lawn, List<Mower>>chunk(1)
                .reader(() -> {
                    try {
                        return InputAdapter.readInputFile(inputDirectoryPath);
                    } catch (IOException e) {
                        throw e;
                    }
                })
                .processor(processor)
                .writer(mowers -> {
                    try {
                        OutputAdapter.writeOutputFile(outputDirectoryPath, mowers.get(0));
                    } catch (IOException e) {
                        throw e;
                    }
                })
                .build();
    }

    @Bean
    public ItemProcessor<Lawn, List<Mower>> processor() {
        return MowItNowService::processInstructions;
    }
}
