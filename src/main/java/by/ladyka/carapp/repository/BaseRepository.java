package by.ladyka.carapp.repository;

import by.ladyka.carapp.ecxeption.ApiException;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    String ENTITY_NOT_FOUND_TEMPLATE = "Not found. Entity: %s. Id: %s";

    Optional<T> findById(Long id);

    default void existsByIdThrow(Long id) {
        if (!existsById(id)) {
            String entityName = getEntityName();
            throw new ApiException(ENTITY_NOT_FOUND_TEMPLATE, entityName, id);
        }
    }

    default T findByIdThrow(Long id) {
        return findById(id).orElseThrow(() -> {
            String entityName = getEntityName();
            return new ApiException(ENTITY_NOT_FOUND_TEMPLATE, entityName, id);
        });
    }

    default String getEntityName() {
        Class<?> entityClass = GenericTypeResolver.resolveTypeArgument(getClass(), BaseRepository.class);
        return entityClass == null ? "entity" : entityClass.getSimpleName();
    }
}
