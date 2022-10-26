package crud_Three.crud_Three.Repo;

import crud_Three.crud_Three.Model.ListModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepo extends JpaRepository<ListModel, Long> {
}
