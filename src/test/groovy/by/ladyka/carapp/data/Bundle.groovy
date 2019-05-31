package by.ladyka.carapp.data

import by.ladyka.carapp.repository.BaseRepository


class Bundle<EntityType> implements Deletable {

    BaseRepository<EntityType> repository
    EntityType entity
    List<Deletable> relatedEntities = []

    Bundle(BaseRepository<EntityType> repository) {
        this.repository = repository
    }

    void append(Closure closure) {
        relatedEntities.add(0, new Deletable() {
            @Override
            def delete() {
                closure.call()
            }
        })
    }

    def <T> T appendAndGet(Bundle<T> bundle) {
        relatedEntities.add(0, bundle)
        bundle.entity
    }

    @Override
    def delete() {
        repository.delete(entity.id)
        relatedEntities.each {
            it.delete()
        }
    }
}
