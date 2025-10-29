package ci.trabrouss.authservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class User { // extends BaseEntity

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String password;

    private String firstname;
    private String lastname;

    private String role;
    private String token;
    private LocalDateTime tokenExpiredAt;
    private String refreshToken;

    public Boolean isExpired(){
        return LocalDateTime.now().isAfter(this.tokenExpiredAt);
    }

    public void cleanResetToken(){
        this.tokenExpiredAt = null;
        this.token = null;
    }
}
