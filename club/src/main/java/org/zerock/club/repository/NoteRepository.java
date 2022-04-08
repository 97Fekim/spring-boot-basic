package org.zerock.club.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.club.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @EntityGraph(attributePaths = "writer", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select n from Note n where n.num = :num")
    Optional<Note> getWithWriter(Long num); // id로 노트 하나 가져오는데 writer도 함께 가져와랏

    @EntityGraph(attributePaths = {"writer"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select n from Note n where n.writer.email = :email")
    List<Note> getList(String email);       // 얘가 쓴 노트 다 가져와라

}
