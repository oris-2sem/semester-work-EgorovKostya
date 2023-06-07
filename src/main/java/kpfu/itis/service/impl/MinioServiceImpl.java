package kpfu.itis.service.impl;

import io.minio.*;
import kpfu.itis.dto.request.TitleRegistrationRequest;
import kpfu.itis.exception.validation.IllegalFileFormatException;
import kpfu.itis.repository.FileRepository;
import kpfu.itis.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    private final FileRepository fileRepository;

    @Value("${minio.bucket.name}")
    private String bucket;

    private static final List<String> extensions = List.of("jpg", "png", "gif", "jpeg", "svg");

    @Override
    public String saveImage(TitleRegistrationRequest request) {
        String name = fileRepository.getFileName();
        String[] split = request.getFile().getOriginalFilename().split("\\.");
        try {
            if (!extensions.contains(split[split.length - 1])) {
                throw new IllegalFileFormatException("Неподходящий формат файла");
            }
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(name + "." + split[split.length - 1])
                    .stream(request.getFile().getInputStream(), request.getFile().getSize(), -1)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return name + "." +split[split.length - 1];
    }

    @Override
    public byte[] getImage(String url) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(url)
                        .build())){
            return IOUtils.toByteArray(stream);
        } catch (Exception e ) {
            throw new RuntimeException();
        }
    }
}
