package com.chen.xiansen.r2dbc.repository;

import com.chen.xiansen.r2dbc.entity.TAuthor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.Collection;

public interface AuthorRepository extends R2dbcRepository<TAuthor,Long> {
    Flux<TAuthor> findAllByIdIn(Collection<Long> id);
}
