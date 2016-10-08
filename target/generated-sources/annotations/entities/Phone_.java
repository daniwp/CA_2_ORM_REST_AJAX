package entities;

import entities.InfoEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-07T09:23:36")
@StaticMetamodel(Phone.class)
public class Phone_ { 

    public static volatile SingularAttribute<Phone, String> number;
    public static volatile SingularAttribute<Phone, String> description;
    public static volatile SingularAttribute<Phone, Long> id;
    public static volatile SingularAttribute<Phone, InfoEntity> entity;

}