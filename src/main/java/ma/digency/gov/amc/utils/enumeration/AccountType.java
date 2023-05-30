package ma.digency.gov.amc.utils.enumeration;

public enum AccountType {
    COMPANY("public_company"),
    PERSON("individual"),
    COOPERATIVE("cooperative");

    private final String label;

    private AccountType(String label)
    {
        this.label=label;
    }

    public String getLabel()
    {
        return label;
    }
}
