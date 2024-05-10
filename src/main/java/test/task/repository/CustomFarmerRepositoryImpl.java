package test.task.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.model.farmer.Farmer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public class CustomFarmerRepositoryImpl implements CustomFarmerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Farmer> findByExampleWithDateRange(Example<Farmer> example) {
        Farmer farmerExample = example.getProbe();
        LocalDateTime startDate = farmerExample.getStartDate();
        LocalDateTime endDate = farmerExample.getEndDate();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farmer> cq = cb.createQuery(Farmer.class);
        Root<Farmer> farmer = cq.from(Farmer.class);
        List<Predicate> predicates = new ArrayList<>();

        // Add the date range condition
        if (startDate != null && endDate != null) {
            predicates.add(cb.between(farmer.get("registeredAt"), startDate, endDate));
        }

        // Create an ExampleMatcher that ignores null properties
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Farmer> exampleWithMatcher = Example.of(farmerExample, matcher);

        // Generate predicates for the example
        Predicate examplePredicate = QueryByExamplePredicateBuilder.getPredicate(farmer, cb, exampleWithMatcher);
        if (examplePredicate != null) {
            predicates.add(examplePredicate);
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(cq).getResultList();
    }
}