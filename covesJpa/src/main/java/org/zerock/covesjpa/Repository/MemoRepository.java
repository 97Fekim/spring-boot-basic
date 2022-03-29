package org.zerock.covesjpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.covesjpa.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
