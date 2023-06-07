package kpfu.itis.config;

import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MinioConfig {

    @Value("${minio.access.key}")
    private String accessKey;

    @Value("${minio.access.secret}")
    private String accessSecret;

    @Value("${minio.url}")
    private String url;


    @SneakyThrows
    @Bean
    @Primary
    public MinioClient minioClient() {
        MinioClient minio = new MinioClient.Builder()
                .credentials(accessKey, accessSecret)
                .endpoint(url)
                .build();
        return minio;
    }
}
