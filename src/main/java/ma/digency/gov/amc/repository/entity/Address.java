package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Address implements Serializable {

        private static final long serialVersionUID = 1L;

        private String refAddress;

        private String province;

        private String postalCode;

        private String city;

        private String country;

        private String address;

        private String region;

}
