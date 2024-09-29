package br.com.projeto.starchile.backend_starchile.domain.client;

import br.com.projeto.starchile.backend_starchile.domain.employee.Employee;
import br.com.projeto.starchile.backend_starchile.domain.tour.Tours;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address_client")
    private String addressClient;
    @Column(name = "hotel_address")
    private String hotelAddress;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "person_quantity")
    private Integer personQuantity;
    @Column(name = "final_price")
    private Double finalPrice;
    @Column(name = "tour_date")
    private LocalDate tourDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_Id", referencedColumnName = "id")
    private Tours tourId;


    public Client(InsertClientDTO clientDTO){
        this.name = clientDTO.name().toUpperCase();
        this.phone = clientDTO.phone();
        this.addressClient = clientDTO.addressClient();
        this.hotelAddress = clientDTO.hotelAddress();
        this.hotelName = clientDTO.hotelName();
        this.tourDate = clientDTO.tourDate();
        this.personQuantity = clientDTO.personQuantity();
    }
}
