package org.abc.openlaw.repository;

import org.abc.openlaw.domain.Law;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by scamisay on 08/02/16.
 */
public interface LawRepository extends MongoRepository<Law, String> {
}
