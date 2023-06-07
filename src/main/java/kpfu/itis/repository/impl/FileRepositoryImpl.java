package kpfu.itis.repository.impl;

import kpfu.itis.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
@Transactional
public class FileRepositoryImpl implements FileRepository {

    private final EntityManager entityManager;


    @Override
    public String getFileName() {
        BigInteger num = (BigInteger) entityManager.createNativeQuery("select * from file_num").getSingleResult();
        entityManager.createNativeQuery("update file_num set count = count + 1").executeUpdate();
        return String.valueOf(num);
    }
}
