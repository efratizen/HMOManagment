package com.javatpoint.controller;

import com.javatpoint.dto.HmoMemberDto;
import com.javatpoint.model.CoronaDetails;
import com.javatpoint.model.HmoMember;
import com.javatpoint.service.HmoMemberRepository;
import com.javatpoint.service.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.javatpoint.model.HmoMember;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hmoMember")
@CrossOrigin
public class HmoMemberController {
    private HmoMemberRepository hmoMemberRepository;
    private MapStructMapper mapper;
    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\images\\";

    @Autowired
    public HmoMemberController(HmoMemberRepository hmoMemberRepository, MapStructMapper mapper) {
        this.hmoMemberRepository = hmoMemberRepository;
        this.mapper = mapper;
    }

    //for printing the kind of validtion
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    //Upload a new HMO member + his image
    //(Corona details will be uploaded with a special request for uploading corona details)
    @PostMapping("/uploadHMOmember")
    public ResponseEntity<HmoMember> uploadHMOmember(@RequestPart("image") MultipartFile file, @Valid @RequestPart("hmoMember") HmoMember member) {
        try {
            // Return 400 BAD_REQUEST if the HmoMember ID already exists
            if (hmoMemberRepository.existsById(member.getId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String fileName = file.getOriginalFilename();
            String filePath = UPLOAD_DIRECTORY + fileName;

            // Write the file to the specified path
            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());

            // Update the image path of the hmo member
            member.setImage(filePath);

            // Save the hmo member to the database
            HmoMember newHMOmember = hmoMemberRepository.save(member);

            return new ResponseEntity<>(newHMOmember, HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get all hmo members + their images(convert to dto)
    //(get All HMO member details including the Corona details)
    @GetMapping("/getAllHmoMebers")
    public ResponseEntity<List<HmoMemberDto>> getAllHmoMebersDto() {
        try {
            List<HmoMember> hmoMembers = new ArrayList<>();
            hmoMemberRepository.findAll().forEach((e -> hmoMembers.add(e)));
            return new ResponseEntity<>(mapper.hmoMembersToDto(hmoMembers), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get a hmo member by his id + his image(convert to dto)
    @GetMapping("/getById/{id}")
    public ResponseEntity<HmoMemberDto> getHmoMemberDtoById(@PathVariable long id) throws IOException {
        HmoMember u = hmoMemberRepository.findById(id).orElse(null);
        if (u != null)
            return new ResponseEntity<>(mapper.hmoMemberToDto(u), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //get a hmo member by his phone + his image(convert to dto)
    @GetMapping("/getByPhone/{phone}")
    public ResponseEntity<HmoMemberDto> getByPhone(@PathVariable String phone) throws IOException {
        List<HmoMember> hmoMembers = new ArrayList<>();

        HmoMember u = hmoMemberRepository.findByPhone(phone);
        if (u != null)
            return new ResponseEntity<>(mapper.hmoMemberToDto(u), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //get all israelies hmo members by their "+972" prefix + their images(convert to dto)
    @GetMapping("/getAllIsraelies")
    public ResponseEntity<List<HmoMemberDto>> getAllIsraelies() {
        try {
            List<HmoMember> hmoMembers = hmoMemberRepository.findAllByMobilePhoneContains("+972");
            List<HmoMemberDto> hmoMemberDtos = mapper.hmoMembersToDto(hmoMembers);
            return new ResponseEntity<>(hmoMemberDtos, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}