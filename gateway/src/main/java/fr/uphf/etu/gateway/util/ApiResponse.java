package fr.uphf.etu.gateway.util;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Map")
public class ApiResponse {
    @XmlElement
    private Date timestamp;

    @XmlElement
    private int status;

    @XmlElement
    private String error;

    @XmlElement
    private String message;

    @XmlElement
    private String path;
}
