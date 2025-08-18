package hu.zlaval.springcourse.user;

import hu.zlaval.springcourse.configuration.exceptions.NotFoundException;
import hu.zlaval.springcourse.user.request.GetUsersParam;
import hu.zlaval.springcourse.user.request.UserPatchRequest;
import hu.zlaval.springcourse.user.request.UserRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AtomicLong sequence = new AtomicLong(1);
    private final Map<Long, UserEntity> users = new ConcurrentHashMap<>();


    @GetMapping
    public Collection<UserEntity> users(
            //@RequestParam(name = "email", required = false) String email
            GetUsersParam param
    ) {
        var records = users.values();
        if (param.getEmail() != null) {
            records = records.stream()
                    .filter(r -> r.getEmail().contains(param.getEmail()))
                    .toList();
        }

        return records;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getUser(
            @PathVariable Long id
    ) {
        var user = users.get(id);
        if (user == null) {
            throw new NotFoundException("User not found by id " + id);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(
            @RequestBody @Valid
            UserRequest request
    ) {
        var id = sequence.incrementAndGet();
        var entity = UserEntity.builder()
                .id(id)
                .email(request.getEmail())
                .password(hasPassword(request.getPassword()))
                .build();
        users.put(id, entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(
            @PathVariable(value = "id", required = true) Long id,
            @RequestBody UserRequest request
    ) {
        var user = users.get(id);
        user.setPassword(hasPassword(request.getPassword()));
        user.setEmail(hasPassword(request.getEmail()));
        return user;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    ) {
        users.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<UserEntity> updateEmail(
            @PathVariable Long id,
            @RequestBody UserPatchRequest request
    ) {

        var user = users.get(id);
        user.setEmail(request.email());
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadProfilePicture(
            @PathVariable Long id,
            @RequestPart("image") MultipartFile file
    ) {
        var user = users.get(id);
        logger.info("{}'s profile picture name: {}, size: {}", user.getEmail(), file.getName(), file.getSize());
        return ResponseEntity.ok("http://fileurl.com");
    }

    private String hasPassword(String plainTextPW) {
        return plainTextPW;
    }

}
