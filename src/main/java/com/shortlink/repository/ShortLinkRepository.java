package com.shortlink.repository;

import com.shortlink.model.ShortLink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.net.URL;
import java.util.Optional;

@Repository
public interface ShortLinkRepository extends MongoRepository<ShortLink, String> {
    Optional<ShortLink> findByHash(String hash);
    Optional<ShortLink> findByUrl(URL url);
}
