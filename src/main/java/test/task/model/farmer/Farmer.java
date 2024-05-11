package test.task.model.farmer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import test.task.model.District;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farmer")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organisation_name")
    private String organisationName;

    @Column(name = "legal_form")
    @Enumerated(value = EnumType.STRING)
    private LegalForm legalForm;

    @Column(name = "taxpayer_identification_number_inn")
    private Long INN;

    @Column(name = "tax_registration_reason_code_kpp")
    private Long KPP;

    @Column(name = "primary_state_registration_number_ogrn")
    private Long OGRN;

    @ManyToOne
    @JoinColumn(name = "district_registered_at_id")
    private District districtRegisteredAt;

    @ManyToMany
    @JoinTable(
            name = "farmer_district",
            joinColumns = @JoinColumn(name = "farmer_id"),
            inverseJoinColumns = @JoinColumn(name = "district_id")
    )
    private List<District> cropFieldDistricts;


    @Column(name = "is_archived", nullable = false)
    private boolean isArchived;

    @CreationTimestamp
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    private LocalDateTime startDate;
    @Transient
    private LocalDateTime endDate;
}
