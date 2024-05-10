package test.task.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import test.task.model.farmer.Farmer;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomFarmerRepositoryIml implements CustomFarmerRepository {

    private final FarmerRepository farmerRepository;

    @Override
    public List<Farmer> findByExampleWithDateRange(Example<Farmer> example) {
        Farmer farmerExample = example.getProbe();
        LocalDateTime startDate = farmerExample.getStartDate();
        LocalDateTime endDate = farmerExample.getEndDate();

        Specification<Farmer> dataRangeSpec = ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get("registeredAt"), startDate, endDate)
        );

        Specification<Farmer> combSpec = Specification.where(toSpecification(example).and(dataRangeSpec));
        return farmerRepository.findAll(combSpec);
    }

    public <S> Specification<S> toSpecification(Example<S> example) {
        return (root, query, criteriaBuilder) ->
                QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, example);
    }
}
