package org.abc.openlaw.repository;

import org.abc.openlaw.domain.LawVersion;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by scamisay on 05/02/16.
 */
public interface LawVersionRepository extends MongoRepository<LawVersion, String> {
}
