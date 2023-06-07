package kpfu.itis.service;

import kpfu.itis.dto.request.TitleRegistrationRequest;

public interface MinioService {


    String saveImage(TitleRegistrationRequest request);

    byte[] getImage(String url);
}
