package springbackend.model;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents user's file for his service description (photo or video).
 */

@Entity
@Table(name = "user's_files")
public class UserFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String typeOfFile;

    @Column(name = "path_to_file")
    private String pathToFile;

    @Column(name = "service_id")
    private String serviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "id=" + id +
                ", typeOfFile='" + typeOfFile + '\'' +
                ", pathToFile='" + pathToFile + '\'' +
                '}';
    }
}
