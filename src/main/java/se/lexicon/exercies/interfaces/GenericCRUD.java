package se.lexicon.exercies.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericCRUD <T, ID>{
    T save (T t);
    Optional<T> findById( ID id);
    List<T> findAll(T t);
    void delete (ID id);

}
