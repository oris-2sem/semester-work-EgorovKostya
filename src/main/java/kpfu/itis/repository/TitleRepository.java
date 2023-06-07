package kpfu.itis.repository;

import kpfu.itis.model.TitleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, UUID> {

    @Query("select s from TitleEntity s where lower(s.name) like lower(concat('%', :name, '%'))" +
            " or lower(s.alternativeName) like lower(concat('%', :name, '%'))")
    Page<TitleEntity> findByName(String name, Pageable pageable);


    @Query(nativeQuery = true, value = "select * from title where id in (select title_id from users_titles group by title_id order by count(*) desc limit 1)")
    TitleEntity findPopularTitle();
}
