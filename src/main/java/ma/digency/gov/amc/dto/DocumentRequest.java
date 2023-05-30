package ma.digency.gov.amc.dto;

import ma.digency.gov.amc.repository.entity.Demand;

import javax.persistence.*;

public class DocumentRequest {

    private String type;

    private String nature;

    private String name;

    private Long size;

    private String refObject;

    private String refParent;

    private byte[] data;

}
